package org.acquiring.service.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SinglePayPageResponse implements IAdapterResponse {

    @JsonProperty(value = "redirectLink")
    String payPageRedirectLink;

    @JsonProperty(value = "customerId")
    String externalCustomerId;

    @JsonIgnore
    String bankResponse;

    @JsonIgnore
    String bankId;
}
