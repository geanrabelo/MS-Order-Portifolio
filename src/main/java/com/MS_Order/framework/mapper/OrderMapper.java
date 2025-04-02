package com.MS_Order.framework.mapper;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.framework.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {
    @Autowired
    private OrderItemMapper orderItemMapper;

    public OrderEntity toOrderEntity(Order order){
        return new OrderEntity(order.getOrderId(), order.getCustomerId(), order.getEmail(), order.getBalance(),order.getOrderDate(), order.getStatus(), orderItemMapper.toListOrderItemEntity(order.getOrderItemList()), order.getTotalAmount());
    }
    public Order toOrder(OrderEntity orderEntity){
        return new Order(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getEmail(), orderEntity.getBalance(),orderEntity.getOrderDate(), orderEntity.getStatus(), orderItemMapper.toListOrderItem(orderEntity.getItems()), orderEntity.getTotalAmount());
    }
}
