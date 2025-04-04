package com.MS_Order.framework.usecases.impl;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.dto.OrderItemDTO;
import com.MS_Order.framework.usecases.CreateOrderItemUsecases;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderItemUsecasesImpl implements CreateOrderItemUsecases {

    private final OrderItemEntityUsecases orderItemEntityUsecases;

    public CreateOrderItemUsecasesImpl(OrderItemEntityUsecases orderItemEntityUsecases){
        this.orderItemEntityUsecases = orderItemEntityUsecases;
    }

    @Override
    public void createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItemEntity orderItemEntity = new OrderItemEntity(orderItemDTO.name(), orderItemDTO.quantity(), orderItemDTO.unitPrice());
        orderItemEntityUsecases.create(orderItemEntity);
    }
}
