package org.acquiring.service.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acquiring.service.rabbitmq.RabbitMqProducer;
import org.acquiring.service.rabbitmq.dto.RabbitMqRequest;
import org.acquiring.service.rest.dto.IAdapterResponse;
import org.acquiring.service.rest.dto.ServiceErrorResponse;
import org.acquiring.service.rest.dto.ServiceResponse;
import org.acquiring.service.rest.dto.ServiceStatusEmum;
import org.acquiring.service.rest.dto.SinglePayPageRequest;
import org.acquiring.service.service.PayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acquiring/api")
@RequiredArgsConstructor
@Slf4j
public class AcquiringRestController {

    private final PayService payService;
    private final RabbitMqProducer rabbitMqProducer;
    private final ObjectMapper mapper;

    @PostMapping("/v1/pay/single-pay")
    public ServiceResponse postSinglePayPage(@RequestBody SinglePayPageRequest request){
        log.info("Starting rest single page request -> {}",request);
        try {
            IAdapterResponse response = payService.singlePayPageHandle(request);

            log.info("Success rest single page request -> {}",request);
            return ServiceResponse.builder()
                    .status(ServiceStatusEmum.SUCCESS)
                    .body(response)
                    .build();

        }catch (Exception exception){
            log.error("Error rest single page request -> {}, error ->",request,exception);
            ServiceErrorResponse response = ServiceErrorResponse.builder()
                    .message(exception.getMessage())
                    .build();
            return ServiceResponse.builder()
                    .status(ServiceStatusEmum.ERROR)
                    .error(response)
                    .build();
        }
    }

    @PostMapping("/v1/debug/rabbit")
    @Deprecated
    public ResponseEntity<String> sendMessage(@RequestBody RabbitMqRequest request){
        rabbitMqProducer.sendDebugMessage(request);
        return ResponseEntity.ok().build();
    }
}
