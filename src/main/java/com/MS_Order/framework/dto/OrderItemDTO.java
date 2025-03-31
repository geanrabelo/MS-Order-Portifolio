package com.MS_Order.framework.dto;

import java.math.BigDecimal;

public record OrderItemDTO(int quantity, BigDecimal unitPrice) {
}
