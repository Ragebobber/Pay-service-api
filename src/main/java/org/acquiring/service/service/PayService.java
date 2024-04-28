package org.acquiring.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acquiring.service.acquirings.IAcquirings;
import org.acquiring.service.acquirings.yookassa.dto.YookassaNotification;
import org.acquiring.service.acquirings.yookassa.dto.YookassaPaymentObject;
import org.acquiring.service.acquirings.yookassa.dto.YookassaPaymentStatusEnum;
import org.acquiring.service.entity.Customer;
import org.acquiring.service.entity.Payment;
import org.acquiring.service.entity.PaymentStatusEnum;
import org.acquiring.service.rabbitmq.RabbitMqProducer;
import org.acquiring.service.rabbitmq.dto.RabbitMqRequest;
import org.acquiring.service.rabbitmq.dto.RabbitMqResponse;
import org.acquiring.service.repository.CustomerRepository;
import org.acquiring.service.repository.PaymentRepository;
import org.acquiring.service.rest.dto.SinglePayPageRequest;
import org.acquiring.service.rest.dto.SinglePayPageResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayService {

    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final ObjectMapper mapper;
    private final RabbitMqProducer rabbitMqProducer;

    public SinglePayPageResponse singlePayPageHandle(@NotNull SinglePayPageRequest request){
        log.info("Starting single page handler, request -> {}",request);
        IAcquirings adapter = request.getAdapter().payAdapter();

        Customer customer = customerRepository.findCustomerByExternalId(request.getExternalCustomerId())
                .orElseGet(() -> Customer.builder()
                        .externalId(request.getExternalCustomerId())
                        .build());

        customerRepository.save(customer);

        SinglePayPageResponse response = adapter.singlePayPageProcessing(request);

        Payment payment = Payment.builder()
                    .amountInCents(request.getAmount())
                    .customer(customer)
                    .currencyEnum(request.getCurrency())
                    .paymentStatus(PaymentStatusEnum.CREATED)
                    .bankId(response.getBankId())
                    .adapter(request.getAdapter())
                    .externalOrderId(request.getExternalOrderId())
                    .build();

        paymentRepository.save(payment);
        log.info("Success single page handler, response -> {}",response);
        return response;
    }

    public void callbackYookassaPayHandle(YookassaNotification request) throws JsonProcessingException {
        if(Objects.isNull(request.getObject())){
            log.warn("Yookassa callback: Request is incorrect: {}", request);
            return;
        }
        YookassaPaymentObject paymentObject = request.getObject();

        Optional<Payment> optionalPayment = paymentRepository.findPaymentByBankId(paymentObject.getId());

        if(optionalPayment.isEmpty())
            return;

        Payment payment = optionalPayment.get();

        if(PaymentStatusEnum.APPROVED.equals(payment.getPaymentStatus())){
            log.info("Yookassa callback: payment status is APPROVED: {}", request);
            return;
        }

        payment.setBankResponse(mapper.writeValueAsString(request));

        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(paymentObject.getAmount().getValue()));

        if(!YookassaPaymentStatusEnum.SUCCEEDED.equals(paymentObject.getStatus()) ||
                payment.getAmountInCents().compareTo(amount) != 0){
            log.warn("Yookassa callback: Amount or Status is incorrect: {}", request);
            payment.setPaymentStatus(PaymentStatusEnum.FAILED);

        }else{
            payment.setPaymentStatus(PaymentStatusEnum.APPROVED);
            log.info("Yookassa callback: success payment, id: {}", payment.getId());
        }
        paymentRepository.save(payment);
    }

    public void  singlePayPageRabbitMqHandle(RabbitMqRequest request){
        log.info("Starting new rabbitmq request handler request -> {}",request);
        try {
            switch (request.getType()){
                case ACQ_SINGLE_PAY -> {
                    SinglePayPageRequest singlePayPageRequest = mapper.convertValue(request.getData(),SinglePayPageRequest.class);
                    SinglePayPageResponse response = singlePayPageHandle(singlePayPageRequest);
                    log.info("Adapter pay response -> {}", response);
                    rabbitMqProducer.sendResponseMessage(
                            RabbitMqResponse.builder()
                                                                 .responseId(request.getRequestId())
                                                                 .type(request.getType())
                                                                 .timeStamp(Instant.now().toString())
                                                                 .data(response)
                                                                 .build()
                    );
                    log.info("Success request handler request -> {}, response -> {}",request,response);
                }
                default -> throw new UnsupportedOperationException("No request type for operation");
            }
        }catch (Exception exception){
            log.error("RabbitMq service handle error request -> {}, error -> ",request,exception);
            rabbitMqProducer.sendResponseMessage(
                    RabbitMqResponse.builder()
                            .responseId(request.getRequestId())
                            .type(request.getType())
                            .timeStamp(Instant.now().toString())
                            .data(exception)
                            .build()
            );
        }
    }
}
