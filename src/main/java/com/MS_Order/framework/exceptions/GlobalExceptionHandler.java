package com.MS_Order.framework.exceptions;

import com.MS_Order.core.exceptions.OrderIdNotFound;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.framework.exceptions.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderIdNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse OrderIdNotFoundHandler(OrderIdNotFound orderIdNotFound){
        return ErrorResponse.notFound(orderIdNotFound.getMessage());
    }

    @ExceptionHandler(OrderItemIdNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse OrderItemIdNotFoundHandler(OrderItemIdNotFound orderItemIdNotFound){
        return ErrorResponse.notFound(orderItemIdNotFound.getMessage());
    }
}
