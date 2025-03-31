package com.MS_Order.framework.service;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.enums.EnumCode;
import com.MS_Order.core.exceptions.OrderIdNotFound;
import com.MS_Order.core.exceptions.OrderItemIdNotFound;
import com.MS_Order.core.usecases.OrderEntityUsecases;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.domain.Order;
import com.MS_Order.framework.domain.OrderItem;
import com.MS_Order.framework.mapper.OrderMapper;
import com.MS_Order.framework.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEntityUsecasesImpl implements OrderEntityUsecases {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemEntityUsecases orderItemEntityUsecases;

    @Override
    public String create(OrderEntity orderEntity) {
        Order order = orderMapper.toOrder(orderEntity);
        checkinList(order.getOrderItemList());
        return orderRepository.save(order).getOrderId();
    }
    private void checkinList(List<OrderItem> orderItemList){
        for(OrderItem orderItem : orderItemList){
            if(!orderItemEntityUsecases.existsById(orderItem.getProductId())){
                throw new OrderItemIdNotFound(EnumCode.ORDI0000.getMessage());
            }
        }
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
    public OrderEntity update(String uuid, OrderEntity newOrderEntity) {
        return null;
    }
}
