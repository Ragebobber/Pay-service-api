package org.acquiring.service.rabbitmq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class RabbitMqRequest implements IRabbitMqRequest{
    @JsonProperty("requestId")
    UUID requestId;
    @JsonProperty("messageType")
    RabbitMqMessageType type;
    @JsonProperty("messageTimeStamp")
    String timeStamp;
    @JsonProperty("data")
    Object data;
}
