package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class YookassaPayPageRequest {

     @JsonProperty(value = "amount")
     YookassaAmount yookassaAmount;

     @JsonProperty(value = "capture")
     Boolean capture;

     @JsonProperty(value = "confirmation")
     YookassaConfirmation confirmation;

     @JsonProperty(value = "description")
     String description;

     @JsonProperty(value = "metadata")
     YookassaMetadata metadata;
}
