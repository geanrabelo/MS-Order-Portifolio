package com.MS_Order.core.usecases;

import com.MS_Order.core.entity.OrderItemEntity;

public interface OrderItemEntityUsecases {
    void create(OrderItemEntity orderItemEntity);
    OrderItemEntity findById(String id);
    boolean existsById(String uuid);
    OrderItemEntity update(String uuid, OrderItemEntity newOrderItemEntity);
}
