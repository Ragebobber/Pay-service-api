package org.acquiring.service.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acquiring.service.rabbitmq.dto.RabbitMqRequest;
import org.acquiring.service.rabbitmq.dto.RabbitMqResponse;
import org.acquiring.service.service.PayService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConsumer {

    private final PayService payService;

    @RabbitListener(queues = {"${acquiring.rabbitmq.request.queue.name}"})
    public void consumeMessage(RabbitMqRequest request){
        log.info("Received message -> {}", request);
        payService.singlePayPageRabbitMqHandle(request);
    }

    @RabbitListener(queues = {"${acquiring.rabbitmq.response.queue.name}"})
    @Deprecated
    public void consumeDbgMessage(RabbitMqResponse response){
        log.info("Received dbg  message -> {}", response);
    }
}
