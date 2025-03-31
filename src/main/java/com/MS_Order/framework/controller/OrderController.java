package com.MS_Order.framework.controller;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.usecases.OrderEntityUsecases;
import com.MS_Order.framework.domain.CustomerData;
import com.MS_Order.framework.domain.OrderEvent;
import com.MS_Order.framework.dto.MessageDTO;
import com.MS_Order.framework.dto.OrderDTO;
import com.MS_Order.framework.mapper.OrderItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ms/order")
public class OrderController {

    @Autowired
    private OrderEntityUsecases orderEntityUsecases;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody OrderDTO orderDTO, UriComponentsBuilder uriComponentsBuilder){
        OrderEntity orderEntity = new OrderEntity(orderDTO.customerId(), orderDTO.email(),orderItemMapper.toListOrderItemEntity(orderDTO.orderItemList()));
        String orderId = orderEntityUsecases.create(orderEntity);
        var uri = uriComponentsBuilder.path("/ms/order").buildAndExpand().toUri();
        kafkaTemplate.send("orders", new OrderEvent("Created order", orderId, new CustomerData(orderEntity.getCustomerId(), orderEntity.getEmail()), orderItemMapper.toListOrderItem(orderEntity.getItems()), orderEntity.getTotalAmount()));
        return ResponseEntity.created(uri).body(new MessageDTO("Order created sucessfully"));
    }

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam(value = "orderId") String orderId){
        return ResponseEntity.ok(orderEntityUsecases.findById(orderId));
    }
}
