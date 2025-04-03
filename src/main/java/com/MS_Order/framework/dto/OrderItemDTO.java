package com.MS_Order.framework.dto;

import java.math.BigDecimal;

public record OrderItemDTO(String name, int quantity, BigDecimal unitPrice) {
}
