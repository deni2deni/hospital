package com.academy.exception;

public class ForbiddenProcedureException extends RuntimeException{
    public ForbiddenProcedureException(String message) {
        super(message);
    }
}
