package org.acquiring.service.acquirings.yookassa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.acquiring.service.entity.CurrencyEnum;

@Value
@Builder
public class YookassaAmount {
     @JsonProperty(value = "value")
     String value;

     @JsonProperty(value = "currency")
     CurrencyEnum currency;
}
