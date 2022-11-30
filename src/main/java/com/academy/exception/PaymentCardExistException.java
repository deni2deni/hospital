package com.academy.exception;

public class PaymentCardExistException extends RuntimeException{
    public PaymentCardExistException(String message) {
        super(message);
    }
}
