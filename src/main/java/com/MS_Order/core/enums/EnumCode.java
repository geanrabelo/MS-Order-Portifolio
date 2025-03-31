package com.MS_Order.core.enums;

public enum EnumCode {
    ORD0000("Order ID not found in database", "ORD-0000"),
    ORDI0000("OrderItem ID not found in database", "ORDI-0000");

    private String message;
    private String code;
    EnumCode(String message, String code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
