package com.MS_Order.framework.mapper;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.enums.EnumCode;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.domain.Order;
import com.MS_Order.framework.domain.OrderItem;
import com.MS_Order.framework.dto.OrderItemID;
import com.MS_Order.framework.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {

    private final OrderRepository orderRepository;

    public OrderItemMapper(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public OrderItemEntity toOrderItemEntity(OrderItem orderItem){
        return new OrderItemEntity.OrderItemEntityBuilder()
                .builder()
                .productId(orderItem.getProductId())
                .name(orderItem.getName())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .build();
    }

    public OrderItem toOrderItem(OrderItemEntity orderItemEntity){
        return OrderItem.builder()
                .productId(orderItemEntity.getProductId())
                .name(orderItemEntity.getName())
                .quantity(orderItemEntity.getQuantity())
                .unitPrice(orderItemEntity.getUnitPrice())
                .build();
    }

    public OrderItem toOrderItemWithOrder(OrderItemEntity orderItemEntity){
        OrderEntity orderEntity = orderItemEntity.getOrderId();
        return OrderItem.builder()
                .productId(orderItemEntity.getProductId())
                .name(orderItemEntity.getName())
                .quantity(orderItemEntity.getQuantity())
                .unitPrice(orderItemEntity.getUnitPrice())
                .order((Order.builder()
                        .orderId(orderEntity.getOrderId())
                        .customerId(orderEntity.getCustomerId())
                        .email(orderEntity.getEmail())
                        .balance(orderEntity.getBalance())
                        .method(orderEntity.getMethod())
                        .orderDate(orderEntity.getOrderDate())
                        .status(orderEntity.getStatus())
                        .orderItemList(toListOrderItem(orderEntity.getItems()))
                        .totalAmount(orderEntity.getTotalAmount()
                        )
                        .build()))
                .build();
    }


    public List<OrderItemEntity> toListOrderItemEntity(List<OrderItem> orderItemList){
        List<OrderItemEntity> itemEntityList = new ArrayList<>();
        for(OrderItem orderItem : orderItemList){
            OrderItemEntity orderItemEntity = toOrderItemEntity(orderItem);
            itemEntityList.add(orderItemEntity);
        }
        return itemEntityList;
    }

    public List<OrderItem> toListOrderItem(List<OrderItemEntity> orderItemEntityList){
        List<OrderItem> orderItemList = new ArrayList<>();
        for(OrderItemEntity orderItemEntity : orderItemEntityList){
            OrderItem orderItem = toOrderItem(orderItemEntity);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }
}
