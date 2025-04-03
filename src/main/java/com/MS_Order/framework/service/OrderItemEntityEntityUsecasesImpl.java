package com.MS_Order.framework.service;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.enums.EnumCode;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.domain.Order;
import com.MS_Order.framework.domain.OrderItem;
import com.MS_Order.framework.mapper.OrderItemMapper;
import com.MS_Order.framework.repository.OrderItemRepository;
import com.MS_Order.framework.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemEntityEntityUsecasesImpl implements OrderItemEntityUsecases {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
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
    public List<OrderItemEntity> toList(List<String> productIdList) {
        checkList(productIdList);
        return productIdList.stream().map(p -> findById(p)).toList();
    }

    @Override
    public void putOrder(String orderId, List<String> listProductId) {
        Order order = orderRepository.getReferenceById(orderId);
        for(String productId : listProductId){
            OrderItem orderItem = orderItemMapper.toOrderItem(findById(productId));
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public OrderItemEntity update(String uuid, OrderItemEntity newOrderItemEntity) {
        return null;
    }
}
