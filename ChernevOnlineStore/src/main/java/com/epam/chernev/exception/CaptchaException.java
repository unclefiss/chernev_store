package com.epam.chernev.exception;

public class CaptchaException extends Exception {

    private static final long serialVersionUID = -904857853179467857L;

    public CaptchaException() {
    }

    public CaptchaException(String message) {
        super(message);
    }

}
