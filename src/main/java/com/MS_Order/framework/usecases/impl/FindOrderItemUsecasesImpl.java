package com.MS_Order.framework.usecases.impl;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.usecases.FindOrderItemUsecases;
import org.springframework.stereotype.Service;

@Service
public class FindOrderItemUsecasesImpl implements FindOrderItemUsecases {

    private final OrderItemEntityUsecases orderItemEntityUsecases;

    public FindOrderItemUsecasesImpl(OrderItemEntityUsecases orderItemEntityUsecases){
        this.orderItemEntityUsecases = orderItemEntityUsecases;
    }
    @Override
    public OrderItemEntity findById(String productId) {
        return orderItemEntityUsecases.findById(productId);
    }
}
