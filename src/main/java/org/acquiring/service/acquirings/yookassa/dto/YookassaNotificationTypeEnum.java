package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum YookassaNotificationTypeEnum {
    NOTIFICATION("notification")
    ;
    private final String type;

    @JsonValue
    public String getType(){
        return type;
    }
}
