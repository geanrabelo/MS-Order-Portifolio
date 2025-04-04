package com.MS_Order.core.entity;

import com.MS_Order.framework.domain.Order;

import java.math.BigDecimal;

public class OrderItemEntity {

    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Order order;

    public OrderItemEntity(String name, Integer quantity, BigDecimal unitPrice){
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderItemEntity(String productId, String name, Integer quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order order) {
        this.order = order;
    }
}
