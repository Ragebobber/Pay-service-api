package org.acquiring.service.rabbitmq.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RabbitMqMessageType {
    ACQ_SINGLE_PAY("ACQ_SINGLE_PAY");

    public final String type;

    @JsonValue
    public String getType(){
        return type;
    }
    @JsonCreator
    public static RabbitMqMessageType fromValue(String value) {
        for (RabbitMqMessageType messageType : RabbitMqMessageType.values()) {
            if (messageType.type.equals(value)) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("Unknown RabbitMqMessageType: " + value);
    }
}
