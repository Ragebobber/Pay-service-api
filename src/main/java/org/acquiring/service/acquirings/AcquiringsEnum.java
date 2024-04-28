package org.acquiring.service.acquirings;


import org.acquiring.service.acquirings.psbank.PsbankAcquiring;
import org.acquiring.service.acquirings.robokassa.RobokassaAcquiring;
import org.acquiring.service.acquirings.yookassa.YookassaAcquiring;
import org.acquiring.service.utils.SpringContext;


public enum AcquiringsEnum {

    YOOKASSA {
        @Override
       public YookassaAcquiring payAdapter() {
            return SpringContext.getBean(YookassaAcquiring.class);
        }
    },
    PSBANK{
        @Override
        public PsbankAcquiring payAdapter() {
           return  SpringContext.getBean(PsbankAcquiring.class);
        }
    },
    ROBOKASSA{
        @Override
        public RobokassaAcquiring payAdapter() {
            return  SpringContext.getBean(RobokassaAcquiring.class);
        }
    };

   public abstract IAcquirings payAdapter();
}
