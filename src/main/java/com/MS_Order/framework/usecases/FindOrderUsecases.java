package com.MS_Order.framework.usecases;

import com.MS_Order.core.entity.OrderEntity;

public interface FindOrderUsecases {
    OrderEntity findById(String orderId);
}
