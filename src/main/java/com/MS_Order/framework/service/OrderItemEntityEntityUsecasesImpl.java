package com.MS_Order.framework.service;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.enums.EnumCode;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.domain.OrderItem;
import com.MS_Order.framework.mapper.OrderItemMapper;
import com.MS_Order.framework.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemEntityEntityUsecasesImpl implements OrderItemEntityUsecases {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

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
    public OrderItemEntity update(String uuid, OrderItemEntity newOrderItemEntity) {
        return null;
    }
}
