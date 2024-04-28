package org.acquiring.service.acquirings.yookassa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.acquiring.service.acquirings.AbstarctAcquiringsParams;
import org.acquiring.service.acquirings.AcquiringsEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class YookassaParams extends AbstarctAcquiringsParams {
    public YookassaParams() {
        super(AcquiringsEnum.YOOKASSA);

    }

    private String shopId;
    private String token;
}
