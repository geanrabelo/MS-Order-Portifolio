package com.MS_Order.framework.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class OrderEvent {

    public OrderEvent(String eventType, String orderId, CustomerData customerData, List<OrderItem> orderItemList, BigDecimal totalValue){
        this.uuid = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.orderId = orderId;
        this.customerData = customerData;
        this.orderItemList = orderItemList;
        this.totalValue = totalValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String eventType;

    private String orderId;

    private CustomerData customerData;

    private List<OrderItem> orderItemList;

    private BigDecimal totalValue;
}
