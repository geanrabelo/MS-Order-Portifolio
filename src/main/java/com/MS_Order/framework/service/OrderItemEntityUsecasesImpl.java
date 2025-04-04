package com.MS_Order.framework.service;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.enums.EnumCode;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.domain.Order;
import com.MS_Order.framework.domain.OrderItem;
import com.MS_Order.framework.mapper.OrderItemMapper;
import com.MS_Order.framework.repository.OrderItemRepository;
import com.MS_Order.framework.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemEntityUsecasesImpl implements OrderItemEntityUsecases {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void create(OrderItemEntity orderItemEntity) {
        OrderItem orderItem = orderItemMapper.toOrderItem(orderItemEntity);
        orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemEntity findById(String id) {
        if(existsById(id)){
            OrderItem orderItemDB = orderItemRepository.getReferenceById(id);
            return orderItemMapper.toOrderItemEntity(orderItemDB);
        }else{
            throw new OrderItemIdNotFound(EnumCode.ORDI0000.getMessage());
        }
    }

    @Override
    public boolean existsById(String uuid) {
        return orderItemRepository.existsById(uuid);
    }

    @Override
    public void checkList(List<String> productIdList) {
        for(String productId : productIdList){
            if(!existsById(productId)){
                throw new OrderItemIdNotFound(EnumCode.ORDI0000.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public List<OrderItemEntity> toList(List<String> productIdList) {
        checkList(productIdList);
        return productIdList.stream().map(p -> findById(p)).toList();
    }

    @Override
    @Transactional
    public void putOrder(String orderId, List<OrderItemEntity> listOrderItemEntity) {
        for(OrderItemEntity orderItemEntity : listOrderItemEntity){
            Order order = orderRepository.getReferenceById(orderId);
            orderItemEntity.setOrderId(order);
            OrderItem orderItem = orderItemMapper.toOrderItemWithOrder(orderItemEntity);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    @Transactional
    public OrderItemEntity update(String uuid, OrderItemEntity newOrderItemEntity) {
        return null;
    }
}
