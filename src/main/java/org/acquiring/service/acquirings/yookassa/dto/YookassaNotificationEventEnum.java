package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum YookassaNotificationEventEnum {
    PAYMENT_SUCCEEDED("payment.succeeded");

    private final String type;

    @JsonValue
    public String getType(){
        return type;
    }
}
