package com.MS_Order.framework.dto;

import com.MS_Order.core.enums.Status;
import com.MS_Order.framework.domain.OrderItem;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;
public record OrderDTO(String customerId, String email, List<OrderItem> orderItemList) {
}
