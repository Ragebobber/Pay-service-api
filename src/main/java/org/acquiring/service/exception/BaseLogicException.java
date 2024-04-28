package org.acquiring.service.exception;

import java.io.Serializable;

public class BaseLogicException extends RuntimeException implements Serializable {
    public BaseLogicException() {
    }

    public BaseLogicException(String message) {
        super(message);
    }

    public BaseLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseLogicException(Throwable cause) {
        super(cause);
    }

    public BaseLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
