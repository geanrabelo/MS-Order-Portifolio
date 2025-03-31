package com.MS_Order.framework.mapper;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.framework.domain.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {

    public OrderItemEntity toOrderItemEntity(OrderItem orderItem){
        return new OrderItemEntity(orderItem.getProductId(), orderItem.getQuantity(), orderItem.getUnitPrice());
    }

    public OrderItem toOrderItem(OrderItemEntity orderItemEntity){
        return new OrderItem(orderItemEntity.getProductId(), orderItemEntity.getQuantity(), orderItemEntity.getUnitPrice());
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
