package org.acquiring.service.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse {
    @JsonProperty(value = "status",required = true)
    ServiceStatusEmum status;

    @JsonProperty(value = "body",required = true)
    IAdapterResponse body;

    @JsonProperty(value = "error")
    IAdapterResponse error;
}
