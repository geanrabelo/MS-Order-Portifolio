package com.MS_Order.core.usecases;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.framework.dto.OrderDTO;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public interface OrderEntityUsecases {
    String create(OrderEntity orderEntity);
    OrderEntity findById(String uuid);
    boolean existsById(String uuid);
    void createEventData(String orderId, OrderEntity orderEntity);
    OrderEntity update(String uuid, OrderEntity newOrderEntity);
}
