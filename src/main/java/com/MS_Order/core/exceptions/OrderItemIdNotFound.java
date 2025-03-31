package com.MS_Order.core.exceptions;

public class OrderItemIdNotFound extends RuntimeException{

    public OrderItemIdNotFound(String message){
        super(message);
    }
}
