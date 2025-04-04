package com.MS_Order.framework.service;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.enums.EnumCode;
import com.MS_Order.core.exceptions.OrderIdNotFound;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.core.usecases.OrderEntityUsecases;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.domain.Order;
import com.MS_Order.framework.domain.OrderItem;
import com.MS_Order.framework.dto.OrderDTO;
import com.MS_Order.framework.mapper.OrderMapper;
import com.MS_Order.framework.repository.OrderItemRepository;
import com.MS_Order.framework.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderEntityUsecasesImpl implements OrderEntityUsecases {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemEntityUsecases orderItemEntityUsecases;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @Transactional
    public String create(OrderEntity orderEntity) {
        Order order = orderMapper.toOrder(orderEntity);
        return orderRepository.save(order).getOrderId();
    }

    @Override
    public void createEventData(String orderId, OrderEntity orderEntity) {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("eventType", "ORDER_CREATED");
        eventData.put("orderId", orderId);
        eventData.put("customerId", orderEntity.getCustomerId());
        eventData.put("email", orderEntity.getEmail());
        eventData.put("balance", orderEntity.getBalance());
        eventData.put("method", orderEntity.getMethod());
        eventData.put("totalValue", orderEntity.getTotalAmount());
        kafkaTemplate.send("orders", eventData);
    }

    @Override
    public OrderEntity findById(String uuid) {
        if(existsById(uuid)){
            Order orderDB = orderRepository.getReferenceById(uuid);
            return orderMapper.toOrderEntity(orderDB);
        }else{
            throw new OrderIdNotFound(EnumCode.ORD0000.getMessage());
        }
    }

    @Override
    public boolean existsById(String uuid) {
        return orderRepository.existsById(uuid);
    }

    @Override
    @Transactional
    public OrderEntity update(String uuid, OrderEntity newOrderEntity) {
        return null;
    }
}
