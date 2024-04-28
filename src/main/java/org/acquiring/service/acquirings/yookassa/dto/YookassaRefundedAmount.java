package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value

public class YookassaRefundedAmount {
    @JsonProperty(value = "value")
     String value;
    @JsonProperty(value = "currency")
     String currency;
}
