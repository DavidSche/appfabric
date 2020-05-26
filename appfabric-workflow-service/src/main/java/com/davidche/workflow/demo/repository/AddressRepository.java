package com.davidche.workflow.demo.repository;

import com.davidche.workflow.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}