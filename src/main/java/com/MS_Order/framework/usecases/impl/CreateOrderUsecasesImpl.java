package com.MS_Order.framework.usecases.impl;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.usecases.OrderEntityUsecases;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.dto.OrderDTO;
import com.MS_Order.framework.usecases.CreateOrderUsecases;
import com.MS_Order.framework.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUsecasesImpl implements CreateOrderUsecases {

    private final OrderEntityUsecases orderEntityUsecases;

    private final OrderItemEntityUsecases orderItemEntityUsecases;

    private final OrderItemMapper orderItemMapper;

    public CreateOrderUsecasesImpl(OrderEntityUsecases orderEntityUsecases, OrderItemEntityUsecases orderItemEntityUsecases, OrderItemMapper orderItemMapper){
        this.orderEntityUsecases = orderEntityUsecases;
        this.orderItemEntityUsecases = orderItemEntityUsecases;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public void createOrder(OrderDTO orderDTO) {
        List<OrderItemEntity> listOrderItemEntity = orderItemEntityUsecases.toList(orderDTO.productId());
        OrderEntity orderEntity = processedCreationOrderEntity(orderDTO, listOrderItemEntity);
        createOrderAndEventDataForKafka(orderEntity, listOrderItemEntity);
    }

    private OrderEntity processedCreationOrderEntity(OrderDTO orderDTO, List<OrderItemEntity> listOrderItemEntity){
        return new OrderEntity.OrderEntityBuilder()
                .customerId(orderDTO.customerId())
                .email(orderDTO.email())
                .balance(orderDTO.balance())
                .method(orderDTO.method())
                .items(listOrderItemEntity)
                .build();
    }

    private void createOrderAndEventDataForKafka(OrderEntity orderEntity, List<OrderItemEntity> orderItemEntityList){
        String orderId = orderEntityUsecases.create(orderEntity);
        orderItemEntityUsecases.putOrder(orderId, orderItemEntityList);
        orderEntityUsecases.createEventData(orderId, orderEntity);
    }
}
