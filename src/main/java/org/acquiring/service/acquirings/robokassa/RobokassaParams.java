package org.acquiring.service.acquirings.robokassa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.acquiring.service.acquirings.AbstarctAcquiringsParams;
import org.acquiring.service.acquirings.AcquiringsEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class RobokassaParams extends AbstarctAcquiringsParams {
    RobokassaParams(){
        super(AcquiringsEnum.ROBOKASSA);
    }
}
