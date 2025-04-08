package com.MS_Order.core.entity;

import com.MS_Order.framework.domain.OrderItem;

import java.math.BigDecimal;

public class OrderItemEntity {

    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private OrderEntity orderEntity;

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
    public OrderItemEntity(String productId, String name, Integer quantity, BigDecimal unitPrice, OrderEntity orderEntity) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderEntity = orderEntity;
    }
    public OrderItemEntity(int quantity){
        this.quantity = quantity;
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

    public OrderEntity getOrderId() {
        return orderEntity;
    }

    public void setOrderId(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public static class OrderItemEntityBuilder {
        private String productId;
        private String name;
        private Integer quantity;
        private BigDecimal unitPrice;
        private OrderEntity orderEntity;

        public OrderItemEntityBuilder(){}

        public OrderItemEntityBuilder builder(){
            return new OrderItemEntityBuilder();
        }

        public OrderItemEntityBuilder productId(String productId){
            this.productId = productId;
            return this;
        }

        public OrderItemEntityBuilder orderItemEntity(OrderEntity orderEntity) {
            this.orderEntity = orderEntity;
            return this;
        }

        public OrderItemEntityBuilder unitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public OrderItemEntityBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItemEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OrderItemEntity build(){
            return new OrderItemEntity(this.productId, this.name, this.quantity, this.unitPrice, this.orderEntity);
        }
    }
}
