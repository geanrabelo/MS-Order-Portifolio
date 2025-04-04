package com.MS_Order.framework.controller;

import com.MS_Order.framework.dto.MessageDTO;
import com.MS_Order.framework.dto.OrderDTO;
import com.MS_Order.framework.usecases.CreateOrderUsecases;
import com.MS_Order.framework.usecases.FindOrderUsecases;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ms/order")
public class OrderController {

    private final CreateOrderUsecases createOrderUsecases;
    private final FindOrderUsecases findOrderUsecases;

    public OrderController(CreateOrderUsecases createOrderUsecases, FindOrderUsecases findOrderUsecases){
        this.createOrderUsecases = createOrderUsecases;
        this.findOrderUsecases = findOrderUsecases;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody OrderDTO orderDTO, UriComponentsBuilder uriComponentsBuilder){
        createOrderUsecases.createOrder(orderDTO);
        var uri = uriComponentsBuilder.path("/ms/order").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(new MessageDTO("Order created sucessfully"));
    }


    @GetMapping
    public ResponseEntity<?> findById(@RequestParam(value = "orderId") String orderId){
        return ResponseEntity.ok(findOrderUsecases.findById(orderId));
    }
}
