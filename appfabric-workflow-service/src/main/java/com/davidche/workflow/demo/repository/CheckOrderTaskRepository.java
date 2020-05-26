package com.davidche.workflow.demo.repository;

import com.davidche.workflow.demo.entity.CheckOrderTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOrderTaskRepository extends JpaRepository<CheckOrderTask, Long> {
}