package com.MS_Order.core.usecases;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.entity.OrderItemEntity;

import java.util.List;

public interface OrderItemEntityUsecases {
    void create(OrderItemEntity orderItemEntity);
    OrderItemEntity findById(String id);
    boolean existsById(String uuid);
    void checkList(List<String> productIdList);
    List<OrderItemEntity> toList(List<String> productIdList);
    void putOrder(String orderId, List<String> listProductId);
    OrderItemEntity update(String uuid, OrderItemEntity newOrderItemEntity);
}
