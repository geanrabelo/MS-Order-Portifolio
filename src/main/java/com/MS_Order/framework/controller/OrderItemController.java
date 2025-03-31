package com.MS_Order.framework.controller;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.dto.MessageDTO;
import com.MS_Order.framework.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ms/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemEntityUsecases orderItemEntityUsecases;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderItemDTO orderItemDTO, UriComponentsBuilder uriComponentsBuilder){
        OrderItemEntity orderItemEntity = new OrderItemEntity(orderItemDTO.quantity(), orderItemDTO.unitPrice());
        orderItemEntityUsecases.create(orderItemEntity);
        var uri = uriComponentsBuilder.path("/ms/orderItem").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new MessageDTO("OrderItem created sucessfully"));
    }

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam(value = "productId") String productId){
        return ResponseEntity.ok(orderItemEntityUsecases.findById(productId));
    }
}
