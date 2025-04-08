package com.MS_Order.framework.usecases.impl;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.usecases.UpdateOrderItemUsecases;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderItemUsecasesImpl implements UpdateOrderItemUsecases {

    private final OrderItemEntityUsecases orderItemEntityUsecases;

    public UpdateOrderItemUsecasesImpl(OrderItemEntityUsecases orderItemEntityUsecases){
        this.orderItemEntityUsecases = orderItemEntityUsecases;
    }

    @Override
    public OrderItemEntity update(String productId, OrderItemEntity newOrderItemEntity) {
        return orderItemEntityUsecases.update(productId, newOrderItemEntity);
    }
}
