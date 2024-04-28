package org.acquiring.service.acquirings.yookassa;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acquiring.service.acquirings.AcquiringsEnum;
import org.acquiring.service.acquirings.IAcquirings;
import org.acquiring.service.acquirings.yookassa.dto.YookassaAmount;
import org.acquiring.service.acquirings.yookassa.dto.YookassaConfirmation;
import org.acquiring.service.acquirings.yookassa.dto.YookassaMetadata;
import org.acquiring.service.acquirings.yookassa.dto.YookassaPayPageRequest;
import org.acquiring.service.acquirings.yookassa.dto.YookassaPayPageResponse;
import org.acquiring.service.exception.SinglePayPageException;
import org.acquiring.service.rest.dto.SinglePayPageRequest;
import org.acquiring.service.rest.dto.SinglePayPageResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class YookassaAcquiring implements IAcquirings {

    private static final AcquiringsEnum CODE = AcquiringsEnum.YOOKASSA;

    private final YookassaRestClient restClient;
    private final YookassaParams yookassaParams;
    private final ObjectMapper mapper;

    @Override
    public AcquiringsEnum getAdapterCode() {
        return CODE;
    }
    @Override
    public SinglePayPageResponse singlePayPageProcessing(SinglePayPageRequest request) {
        log.info("[customerId -> {}, orderId -> {}] Starting single pay processing",
                 request.getExternalCustomerId(), request.getExternalOrderId());

        YookassaPayPageRequest payPageRequest = YookassaPayPageRequest.builder()
                .yookassaAmount(
                        YookassaAmount.builder()
                                        .value(String.valueOf(request.getAmount()))
                                        .currency(request.getCurrency())
                                        .build()
                )
                .capture(true)
                .confirmation(
                        YookassaConfirmation.builder()
                                .returnUrl(request.getSuccessRedirectLink())
                                .type("redirect")
                                .build()
                )
                .metadata(YookassaMetadata.builder()
                                  .orderId(request.getExternalOrderId())
                                  .build())
                .description(request.getDescription())
                .build();

        try {
            YookassaPayPageResponse payPageResponse = restClient.postPayPage(getAuthHeader(), UUID.randomUUID().toString(), payPageRequest);

            if(Objects.isNull(payPageResponse) || Objects.isNull(payPageResponse.getConfirmation()) ||
                    !StringUtils.hasText(payPageResponse.getConfirmation().getRedirectUrl())){
                log.warn("[customerId -> {}, orderId -> {}] Error single pay processing, response: {}",
                         request.getExternalCustomerId(), request.getExternalOrderId(),payPageResponse);
                throw new SinglePayPageException("Incorrect Yookassa response");
            }

            log.info("[customerId -> {}, orderId -> {}] Success single pay processing",
                     request.getExternalCustomerId(), request.getExternalOrderId());

            return SinglePayPageResponse.builder()
                    .externalCustomerId(request.getExternalCustomerId())
                    .payPageRedirectLink(payPageResponse.getConfirmation().getRedirectUrl())
                    .bankResponse(mapper.writeValueAsString(payPageResponse))
                    .bankId(payPageResponse.getId())
                    .build();

        }catch (Exception exception){
            log.warn("[customerId -> {}, orderId -> {}] Error single pay processing",
                     request.getExternalCustomerId(), request.getExternalOrderId());
            throw new SinglePayPageException(exception);
        }
    }

    private String getAuthHeader() {
        String auth = yookassaParams.getShopId() + ":" + yookassaParams.getToken();
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    }
}
