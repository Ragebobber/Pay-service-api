package org.acquiring.service.rabbitmq.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${acquiring.rabbitmq.request.queue.name}")
    private String requestQueue;

    @Value("${acquiring.rabbitmq.request.exchange.name}")
    private String requestExchange;

    @Value("${acquiring.rabbitmq.request.routingKey.name}")
    private String requestRoutingKey;

    @Value("${acquiring.rabbitmq.response.queue.name}")
    private String responseQueue;

    @Value("${acquiring.rabbitmq.response.exchange.name}")
    private String responseExchange;

    @Value("${acquiring.rabbitmq.response.routingKey.name}")
    private String responseRoutingKey;

    @Bean
    public Queue reqQueue(){
        return new Queue(requestQueue);
    }

    @Bean
    public TopicExchange  reqExchange(){
        return new TopicExchange(requestExchange);
    }

    @Bean
    public Binding reqBinding(){
        return BindingBuilder.bind(reqQueue())
                .to(reqExchange())
                .with(requestRoutingKey);
    }
    @Bean
    public Queue resQueue(){
        return new Queue(responseQueue);
    }

    @Bean
    public TopicExchange  resExchange(){
        return new TopicExchange(responseExchange);
    }

    @Bean
    public Binding resBinding(){
        return BindingBuilder.bind(resQueue())
                .to(resExchange())
                .with(responseRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory,ObjectMapper objectMapper){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConverter(objectMapper));
        return rabbitTemplate;
    }
}
