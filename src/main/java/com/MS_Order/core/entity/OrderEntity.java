package com.MS_Order.core.entity;

import com.MS_Order.core.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderEntity {

    public OrderEntity(String orderId, String customerId, String email, BigDecimal balance, int method,LocalDateTime orderDate, Status status, List<OrderItemEntity> itemEntityList, BigDecimal totalAmount){
        this.orderId = orderId;
        this.customerId = customerId;
        this.email = email;
        this.balance = balance;
        this.method = method;
        this.orderDate = LocalDateTime.now();
        this.status = Status.CREATED;
        this.items = itemEntityList;
        this.totalAmount = calculatePrice(itemEntityList);
    }

    public OrderEntity(String customerId, String email, BigDecimal balance, int method,List<OrderItemEntity> orderItemEntityList){
        this.customerId = customerId;
        this.email = email;
        this.balance = balance;
        this.method = method;
        this.items = orderItemEntityList;
        this.orderDate = LocalDateTime.now();
        this.status = Status.CREATED;
        this.totalAmount = calculatePrice(orderItemEntityList);
    }

    private String orderId;
    private String customerId;
    private BigDecimal balance;
    private int method;
    private String email;
    private LocalDateTime orderDate;
    private Status status;
    private List<OrderItemEntity> items;
    private BigDecimal totalAmount;

    private BigDecimal calculatePrice(List<OrderItemEntity> orderItemEntityList){
        return orderItemEntityList.stream().map(o -> BigDecimal.valueOf(o.getQuantity()).multiply(o.getUnitPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static class OrderEntityBuilder {

        private String orderId;
        private String customerId;
        private BigDecimal balance;
        private int method;
        private String email;
        private LocalDateTime orderDate;
        private Status status;
        private List<OrderItemEntity> items;
        private BigDecimal totalAmount;

        public OrderEntityBuilder builder(){
            return new OrderEntityBuilder();
        }

        public OrderEntityBuilder(){
        }

        public OrderEntityBuilder(String customerId, String email, BigDecimal balance, int method) {
            this.customerId = customerId;
            this.email = email;
            this.balance = balance;
            this.method = method;
        }

        public OrderEntityBuilder(BigDecimal totalAmount, List<OrderItemEntity> items, LocalDateTime orderDate, Status status, String email, int method, String customerId, BigDecimal balance, String orderId) {
            this.totalAmount = totalAmount;
            this.items = items;
            this.orderDate = orderDate;
            this.status = status;
            this.email = email;
            this.method = method;
            this.customerId = customerId;
            this.balance = balance;
            this.orderId = orderId;
        }

        public OrderEntityBuilder orderId(String orderId){
            this.orderId = orderId;
            return this;
        }

        public OrderEntityBuilder orderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }


        public OrderEntityBuilder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderEntityBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public OrderEntityBuilder method(int method) {
            this.method = method;
            return this;
        }

        public OrderEntityBuilder email(String email) {
            this.email = email;
            return this;
        }

        public OrderEntityBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public OrderEntityBuilder items(List<OrderItemEntity> items) {
            this.items = items;
            return this;
        }

        public OrderEntityBuilder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }


        public OrderEntity build(){
            return new OrderEntity(this.orderId, this.customerId, this.email, this.balance, this.method, this.orderDate, this.status, this.items, this.totalAmount);
        }
    }
}
