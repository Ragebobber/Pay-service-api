package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class YookassaRecipient {

    @JsonProperty(value = "account_id")
    String accountId;

    @JsonProperty(value = "gateway_id")
     String gatewayId;
}
