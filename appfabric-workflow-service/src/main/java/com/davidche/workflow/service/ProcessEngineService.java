package com.davidche.workflow.service;

import com.davidche.workflow.demo.entity.Order;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProcessEngineService {

	@Autowired
	private RuntimeService runtimeService;

	@Transactional(transactionManager = "camundaTransactionManager", propagation = Propagation.REQUIRED)
	public void startOrderProcess(String businessKey, Order order) {

		runtimeService.startProcessInstanceByKey("orderProcess",
				Variables.createVariables().putValue("orderId", order.getId()));
		if (order.getProcessFail()) {
			throw new RuntimeException("Process failed");
		}
	}
}
