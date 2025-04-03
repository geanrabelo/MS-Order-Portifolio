package com.MS_Order.framework.dto;

import com.MS_Order.framework.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;
public record OrderDTO(String customerId, String email, BigDecimal balance, int method,List<OrderItemID> orderItemIDS) {
}
