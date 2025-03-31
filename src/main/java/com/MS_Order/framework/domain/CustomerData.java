package com.MS_Order.framework.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomerData {

    private String customerId;

    private String email;

    public CustomerData(String customerId, String email){
        this.customerId = customerId;
        this.email = email;
    }
}
