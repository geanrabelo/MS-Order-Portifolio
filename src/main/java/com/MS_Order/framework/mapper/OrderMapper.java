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
        return new OrderEntity.OrderEntityBuilder()
                .builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .email(order.getEmail())
                .balance(order.getBalance())
                .method(order.getMethod())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .items(orderItemMapper.toListOrderItemEntity(order.getOrderItemList()))
                .totalAmount(order.getTotalAmount())
                .build();
    }
    public Order toOrder(OrderEntity orderEntity){
        return Order
                .builder()
                .orderId(orderEntity.getOrderId())
                .customerId(orderEntity.getCustomerId())
                .email(orderEntity.getEmail())
                .balance(orderEntity.getBalance())
                .method(orderEntity.getMethod())
                .orderDate(orderEntity.getOrderDate())
                .status(orderEntity.getStatus())
                .orderItemList(orderItemMapper.toListOrderItem(orderEntity.getItems()))
                .totalAmount(orderEntity.getTotalAmount())
                .build();
    }
}
