package com.MS_Order.framework.repository;

import com.MS_Order.framework.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
