package com.davidche.workflow.demo.repository;

import com.davidche.workflow.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}