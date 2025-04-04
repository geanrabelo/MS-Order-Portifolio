package com.MS_Order.framework.usecases.impl;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.usecases.OrderEntityUsecases;
import com.MS_Order.framework.usecases.FindOrderUsecases;
import org.springframework.stereotype.Service;

@Service
public class FindOrderUsecasesImpl implements FindOrderUsecases {

    private final OrderEntityUsecases orderEntityUsecases;

    public FindOrderUsecasesImpl(OrderEntityUsecases orderEntityUsecases){
        this.orderEntityUsecases = orderEntityUsecases;
    }

    @Override
    public OrderEntity findById(String orderId) {
        return orderEntityUsecases.findById(orderId);
    }
}
