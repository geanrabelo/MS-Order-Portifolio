package com.MS_Order.framework.controller;

import com.MS_Order.core.entity.OrderEntity;
import com.MS_Order.core.usecases.OrderEntityUsecases;
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
import java.util.Map;

@RestController
@RequestMapping("/ms/order")
public class OrderController {

    @Autowired
    private OrderEntityUsecases orderEntityUsecases;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody OrderDTO orderDTO, UriComponentsBuilder uriComponentsBuilder){
        OrderEntity orderEntity = new OrderEntity(orderDTO.customerId(), orderDTO.email(), orderDTO.balance(),orderItemMapper.toListOrderItemEntity(orderDTO.orderItemList()));
        String orderId = orderEntityUsecases.create(orderEntity);
        var uri = uriComponentsBuilder.path("/ms/order").buildAndExpand().toUri();
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("eventType", "ORDER_CREATED");
        eventData.put("orderId", orderId);
        eventData.put("customerId", orderEntity.getCustomerId());
        eventData.put("email", orderEntity.getEmail());
        eventData.put("balance", orderEntity.getBalance());
        eventData.put("totalValue", orderEntity.getTotalAmount());
        kafkaTemplate.send("orders", eventData);
        return ResponseEntity.created(uri).body(new MessageDTO("Order created sucessfully"));
    }

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam(value = "orderId") String orderId){
        return ResponseEntity.ok(orderEntityUsecases.findById(orderId));
    }
}
