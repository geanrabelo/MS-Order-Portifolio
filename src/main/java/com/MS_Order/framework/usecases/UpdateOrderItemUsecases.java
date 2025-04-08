package com.MS_Order.framework.usecases;

import com.MS_Order.core.entity.OrderItemEntity;

public interface UpdateOrderItemUsecases {
    OrderItemEntity update(String uuid, OrderItemEntity newOrderItemEntity);
}
