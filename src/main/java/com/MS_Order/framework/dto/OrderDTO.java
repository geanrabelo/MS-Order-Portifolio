package com.MS_Order.framework.dto;

import java.math.BigDecimal;
import java.util.List;
public record OrderDTO(String customerId, String email, BigDecimal balance, int method, List<String> productId) {
}
