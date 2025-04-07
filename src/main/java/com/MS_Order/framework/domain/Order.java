package com.MS_Order.framework.domain;

import com.MS_Order.core.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "orderId")
@Builder(access = AccessLevel.PUBLIC)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    private String customerId;
    private String email;
    private BigDecimal balance;
    private int method;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;

    private BigDecimal totalAmount;
}
