package org.acquiring.service.rabbitmq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class RabbitMqResponse implements IRabbitMqResponse {
    @JsonProperty("responseId")
    UUID responseId;
    @JsonProperty("messageType")
    RabbitMqMessageType type;
    @JsonProperty("messageTimeStamp")
    String timeStamp;
    @JsonProperty("data")
    Object data;
}
