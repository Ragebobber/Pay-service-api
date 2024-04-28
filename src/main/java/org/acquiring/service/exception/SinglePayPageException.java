package org.acquiring.service.exception;

public class SinglePayPageException extends BaseLogicException{
    public SinglePayPageException() {
    }

    public SinglePayPageException(String message) {
        super(message);
    }

    public SinglePayPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinglePayPageException(Throwable cause) {
        super(cause);
    }

    public SinglePayPageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
