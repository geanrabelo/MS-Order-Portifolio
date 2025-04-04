package com.MS_Order.framework.controller;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.usecases.OrderEntityUsecases;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.dto.MessageDTO;
import com.MS_Order.framework.dto.OrderDTO;
import com.MS_Order.framework.mapper.OrderItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ms/order")
public class OrderController {

    @Autowired
    private OrderEntityUsecases orderEntityUsecases;

    @Autowired
    private OrderItemEntityUsecases orderItemEntityUsecases;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody OrderDTO orderDTO, UriComponentsBuilder uriComponentsBuilder){
        List<OrderItemEntity> listOrderItemEntity = orderItemEntityUsecases.toList(orderDTO.productId());
        OrderEntity orderEntity = new OrderEntity(orderDTO.customerId(), orderDTO.email(), orderDTO.balance(), orderDTO.method(), listOrderItemEntity);
        String orderId = orderEntityUsecases.create(orderEntity);
        orderItemEntityUsecases.putOrder(orderId, listOrderItemEntity);
        orderEntityUsecases.createEventData(orderId, orderEntity);
        var uri = uriComponentsBuilder.path("/ms/order").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new MessageDTO("Order created sucessfully"));
    }
//    private void processedCreationOrderEntity(OrderDTO orderDTO){
//        List<OrderItemEntity> listOrderItemEntity = orderItemEntityUsecases.toList(orderDTO.productId());
//
//        OrderEntity orderEntity = new OrderEntity(orderDTO.customerId(), orderDTO.email(), orderDTO.balance(), orderDTO.method(), listOrderItemEntity);
//
//        String orderId = orderEntityUsecases.create(orderEntity);
//    }

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam(value = "orderId") String orderId){
        return ResponseEntity.ok(orderEntityUsecases.findById(orderId));
    }
}
