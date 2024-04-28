package org.acquiring.service.acquirings.psbank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.acquiring.service.acquirings.AbstarctAcquiringsParams;
import org.acquiring.service.acquirings.AcquiringsEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsbankParams extends AbstarctAcquiringsParams {

    PsbankParams(){
        super(AcquiringsEnum.PSBANK);
    }
}
