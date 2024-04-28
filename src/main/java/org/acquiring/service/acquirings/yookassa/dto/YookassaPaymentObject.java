package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.Date;

@Value
public class YookassaPaymentObject {
    @JsonProperty(value = "id")
     String id;

    @JsonProperty(value = "status")
    YookassaPaymentStatusEnum status;

    @JsonProperty(value = "amount")
     YookassaAmount amount;

    @JsonProperty(value = "income_amount")
    YookassaIncomeAmount incomeAmount;

    @JsonProperty(value = "description")
     String description;

    @JsonProperty(value = "recipient")
     YookassaRecipient recipient;

    @JsonProperty(value = "payment_method")
     YookassaPaymentMethod paymentMethod;

    @JsonProperty(value = "captured_at")
     Date capturedAt;

    @JsonProperty(value = "created_at")
     Date createdAt;

    @JsonProperty(value = "test")
     Boolean test;

    @JsonProperty(value = "refunded_amount")
     YookassaRefundedAmount refundedAmount;

    @JsonProperty(value = "paid")
     boolean paid;

    @JsonProperty(value = "refundable")
     boolean refundable;

    @JsonProperty(value = "metadata")
     YookassaMetadata metadata;
}
