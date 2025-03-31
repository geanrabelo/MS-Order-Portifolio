package com.MS_Order.core.usecases;

import com.MS_Order.core.entity.OrderEntity;

public interface OrderEntityUsecases {
    String create(OrderEntity orderEntity);
    OrderEntity findById(String uuid);
    boolean existsById(String uuid);
    OrderEntity update(String uuid, OrderEntity newOrderEntity);
}
