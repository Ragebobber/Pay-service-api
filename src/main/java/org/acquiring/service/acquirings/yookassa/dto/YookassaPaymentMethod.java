package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class YookassaPaymentMethod {
    @JsonProperty(value = "type")
    String type;

    @JsonProperty(value = "id")
    String id;

    @JsonProperty(value = "saved")
    Boolean saved;

    @JsonProperty(value = "title")
    String title;

    @JsonProperty(value = "account_number")
    String accountNumber;
}
