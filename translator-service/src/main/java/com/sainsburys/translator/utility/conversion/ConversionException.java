

package com.sainsburys.translator.utility.conversion;


public class ConversionException extends RuntimeException {

    public ConversionException() {
    }

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
        this.setStackTrace(cause.getStackTrace());
    }

    public ConversionException(Throwable cause) {
        super(cause);
        this.setStackTrace(cause.getStackTrace());
    }
}
