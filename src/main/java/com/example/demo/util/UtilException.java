package com.example.demo.util;

public class UtilException extends Exception
{
    private static final long serialVersionUID = -8846499120133178157L;

    public UtilException() {

    }

    public UtilException(final String message) {
        super(message);
    }

    public UtilException(final Throwable cause) {
        super(cause);
    }

    public UtilException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
