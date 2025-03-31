package com.MS_Order.core.entity;

import java.math.BigDecimal;

public class OrderItemEntity {

    private String productId;
    private Integer quantity;
    private BigDecimal unitPrice;

    public OrderItemEntity(Integer quantity, BigDecimal unitPrice){
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderItemEntity(String productId, Integer quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
