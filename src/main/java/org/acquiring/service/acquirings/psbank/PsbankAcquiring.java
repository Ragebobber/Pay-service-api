package org.acquiring.service.acquirings.psbank;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acquiring.service.acquirings.AcquiringsEnum;
import org.acquiring.service.acquirings.IAcquirings;
import org.acquiring.service.rest.dto.SinglePayPageRequest;
import org.acquiring.service.rest.dto.SinglePayPageResponse;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class PsbankAcquiring implements IAcquirings {
    private static final AcquiringsEnum CODE = AcquiringsEnum.PSBANK;
    @Override
    public SinglePayPageResponse singlePayPageProcessing(SinglePayPageRequest request) {
        throw new UnsupportedOperationException("Not support method");
    }

    @Override
    public AcquiringsEnum getAdapterCode() {
        return CODE;
    }
}
