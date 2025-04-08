package com.MS_Order.framework.controller;

import com.MS_Order.core.entity.OrderItemEntity;
import com.MS_Order.core.usecases.OrderItemEntityUsecases;
import com.MS_Order.framework.dto.MessageDTO;
import com.MS_Order.framework.dto.OrderItemDTO;
import com.MS_Order.framework.dto.OrderItemUpdateDTO;
import com.MS_Order.framework.usecases.CreateOrderItemUsecases;
import com.MS_Order.framework.usecases.FindOrderItemUsecases;
import com.MS_Order.framework.usecases.UpdateOrderItemUsecases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ms/orderItem")
public class OrderItemController {

    private final CreateOrderItemUsecases createOrderItemUsecases;
    private final FindOrderItemUsecases findOrderItemUsecases;
    private final UpdateOrderItemUsecases updateOrderItemUsecases;

    public OrderItemController(CreateOrderItemUsecases createOrderItemUsecases, FindOrderItemUsecases findOrderItemUsecases, UpdateOrderItemUsecases updateOrderItemUsecases){
        this.createOrderItemUsecases = createOrderItemUsecases;
        this.findOrderItemUsecases = findOrderItemUsecases;
        this.updateOrderItemUsecases = updateOrderItemUsecases;
    }

    @PostMapping
    public ResponseEntity<MessageDTO> create(@RequestBody OrderItemDTO orderItemDTO, UriComponentsBuilder uriComponentsBuilder){
        createOrderItemUsecases.createOrderItem(orderItemDTO);
        var uri = uriComponentsBuilder.path("/ms/orderItem").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new MessageDTO("OrderItem created sucessfully"));
    }

    @GetMapping
    public ResponseEntity<OrderItemEntity> findById(@RequestParam(value = "productId") String productId){
        return ResponseEntity.ok(findOrderItemUsecases.findById(productId));
    }

    @PutMapping
    public ResponseEntity<OrderItemEntity> update(@RequestParam(value = "productId") String productId, @RequestBody OrderItemUpdateDTO orderItemUpdateDTO){
        OrderItemEntity orderItemEntity = updateOrderItemUsecases.update(productId, new OrderItemEntity(orderItemUpdateDTO.quantity()));
        return ResponseEntity.ok(orderItemEntity);
    }
}
