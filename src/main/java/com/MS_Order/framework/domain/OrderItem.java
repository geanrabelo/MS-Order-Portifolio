package com.MS_Order.framework.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_orderItem")
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "productId")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}
