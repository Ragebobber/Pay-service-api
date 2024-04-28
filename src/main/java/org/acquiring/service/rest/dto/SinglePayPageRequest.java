package org.acquiring.service.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.acquiring.service.acquirings.AcquiringsEnum;
import org.acquiring.service.entity.CurrencyEnum;

import java.math.BigDecimal;

@Value
public class SinglePayPageRequest {
    @JsonProperty(value = "adapter", required = true)
    AcquiringsEnum adapter;

    @JsonProperty(value = "customerId",required = true)
    String externalCustomerId;

    @JsonProperty(value = "amount",required = true)
    BigDecimal amount;

    @JsonProperty(value = "currency",required = true)
    CurrencyEnum currency;

    @JsonProperty(value = "successRedirectLink",required = true)
    String successRedirectLink;

    @JsonProperty(value = "orderId",required = true)
    String externalOrderId;

    @JsonProperty(value = "description",required = true)
    String description;
}
