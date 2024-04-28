package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class YookassaConfirmation {

    @JsonProperty(value = "type")
    String type;

    @JsonProperty(value = "return_url")
    String returnUrl;

    @JsonProperty(value = "confirmation_url")
    String redirectUrl;
}
