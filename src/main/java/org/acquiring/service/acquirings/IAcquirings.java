package org.acquiring.service.acquirings;

import jakarta.validation.constraints.NotNull;
import org.acquiring.service.rest.dto.SinglePayPageRequest;
import org.acquiring.service.rest.dto.SinglePayPageResponse;

public interface IAcquirings {
    SinglePayPageResponse singlePayPageProcessing(@NotNull SinglePayPageRequest request);
    AcquiringsEnum getAdapterCode();
}
