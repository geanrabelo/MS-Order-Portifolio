package com.MS_Order.framework.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_orderItem")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "productId")
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;

    public OrderItem(String productId, String name, Integer quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderItem(String productId, String name, Integer quantity, BigDecimal unitPrice, Order order) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
