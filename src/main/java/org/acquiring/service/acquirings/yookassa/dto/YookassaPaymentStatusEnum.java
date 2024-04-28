package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum YookassaPaymentStatusEnum {
    PENDING("pending"),
    SUCCEEDED("succeeded");

    private final String type;

    @JsonValue
    public String getType(){
        return type;
    }
}
