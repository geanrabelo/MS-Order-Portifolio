package com.MS_Order.framework.service;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.enums.EnumCode;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.domain.Order;
import com.MS_Order.framework.domain.OrderItem;
import com.MS_Order.framework.mapper.OrderItemMapper;
import com.MS_Order.framework.mapper.OrderMapper;
import com.MS_Order.framework.repository.OrderItemRepository;
import com.MS_Order.framework.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemEntityUsecasesImpl implements OrderItemEntityUsecases {

    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderItemEntityUsecasesImpl(OrderItemMapper orderItemMapper, OrderMapper orderMapper, OrderItemRepository orderItemRepository, OrderRepository orderRepository){
        this.orderItemMapper = orderItemMapper;
        this.orderMapper = orderMapper;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }


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
            OrderEntity orderEntity = orderMapper.toOrderEntity(orderRepository.getReferenceById(orderId));
            orderItemEntity.setOrderId(orderEntity);
            OrderItem orderItem = orderItemMapper.toOrderItemWithOrder(orderItemEntity);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    @Transactional
    public OrderItemEntity update(String uuid, OrderItemEntity newOrderItemEntity) {
        if(existsById(uuid)){
            if(checkinUpdate(newOrderItemEntity)){
                OrderItem orderItem = orderItemRepository.getReferenceById(uuid);
                orderItem.setQuantity(newOrderItemEntity.getQuantity());
                orderItemRepository.save(orderItem);
                return orderItemMapper.toOrderItemEntity(orderItem);
            }else{
                return null;
            }
        }
        throw new OrderItemIdNotFound(EnumCode.ORDI0000.getMessage());
    }
    private boolean checkinUpdate(OrderItemEntity newOrderItemEntity){
        return newOrderItemEntity.getQuantity() != 0;
    }
}
