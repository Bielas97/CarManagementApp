package com.exception;

public class MyException extends RuntimeException {
    private final String exceptionMsg;

    public MyException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return exceptionMsg;
    }
}
