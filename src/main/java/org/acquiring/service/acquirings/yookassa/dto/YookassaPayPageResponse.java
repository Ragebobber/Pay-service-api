package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class YookassaPayPageResponse {

    @JsonProperty(value = "id")
     String id;

    @JsonProperty(value = "status")
     YookassaPaymentStatusEnum status;

    @JsonProperty(value = "paid")
     Boolean paid;

    @JsonProperty(value = "amount")
     YookassaAmount amount;

    @JsonProperty(value = "confirmation")
     YookassaConfirmation confirmation;

    @JsonProperty(value = "createdAt")
     Date createdAt;

    @JsonProperty(value = "description")
     String description;

    @JsonProperty(value = "metadata")
    YookassaMetadata metadata;

    @JsonProperty(value = "recipient")
    YookassaRecipient recipient;

    @JsonProperty(value = "refundable")
    Boolean refundable;

    @JsonProperty(value = "test")
    Boolean test;
}
