package com.josemeurer.DeliveryKing.services.exceptions;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
