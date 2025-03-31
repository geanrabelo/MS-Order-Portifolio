package com.MS_Order.core.exceptions;

public class OrderIdNotFound extends RuntimeException{

    public OrderIdNotFound(String message){
        super(message);
    }
}
