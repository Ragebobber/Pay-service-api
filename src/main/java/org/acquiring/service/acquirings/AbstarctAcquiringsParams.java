package org.acquiring.service.acquirings;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class AbstarctAcquiringsParams {
    @NotNull
    private AcquiringsEnum acquiringType;
    public AbstarctAcquiringsParams(AcquiringsEnum type) {
        acquiringType = type;
    }
}
