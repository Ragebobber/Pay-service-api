package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class YookassaNotification {
    @JsonProperty(value = "type")
     YookassaNotificationTypeEnum type;

    @JsonProperty(value = "event")
     YookassaNotificationEventEnum event;

    @JsonProperty(value = "object")
    YookassaPaymentObject object;
}
