package com.davidche.workflow.demo.repository;

import com.davidche.workflow.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}