package org.acquiring.service.rest.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ServiceErrorResponse implements IAdapterResponse{
    String message;
}
