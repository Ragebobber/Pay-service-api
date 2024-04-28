package org.acquiring.service.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acquiring.service.rabbitmq.dto.RabbitMqRequest;
import org.acquiring.service.rabbitmq.dto.RabbitMqResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqProducer {

    @Value("${acquiring.rabbitmq.request.exchange.name}")
    private String requestExchange;

    @Value("${acquiring.rabbitmq.request.routingKey.name}")
    private String requestRoutingKey;

    @Value("${acquiring.rabbitmq.response.exchange.name}")
    private String responseExchange;

    @Value("${acquiring.rabbitmq.response.routingKey.name}")
    private String responseRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendDebugMessage(RabbitMqRequest request){
        log.info("Message dbg sent -> {}", request);
        rabbitTemplate.convertAndSend(requestExchange,requestRoutingKey,request);
    }

    public void sendResponseMessage(RabbitMqResponse response){
        log.info("Message sent -> {}", response);
        rabbitTemplate.convertAndSend(responseExchange,responseRoutingKey,response);
    }


}
