package com.MS_Order.framework.usecases;

import com.MS_Order.core.entity.OrderItemEntity;

public interface FindOrderItemUsecases {
    OrderItemEntity findById(String productId);
}
