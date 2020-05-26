package com.davidche.workflow.demo.api;

import com.davidche.workflow.demo.entity.CheckOrderTask;
import com.davidche.workflow.demo.entity.Order;
import com.davidche.workflow.demo.repository.AddressRepository;
import com.davidche.workflow.demo.repository.CheckOrderTaskRepository;
import com.davidche.workflow.demo.repository.CustomerRepository;
import com.davidche.workflow.demo.repository.OrderRepository;
import com.davidche.workflow.service.ProcessEngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController("")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CheckOrderTaskRepository checkOrderTaskRepository;

	@Autowired
	ProcessEngineService engineService;

//	private static Logger LOG = Logger.getLogger(OrderController.class);

	@PostMapping("/order")
	@Transactional(transactionManager = "domainTransactionManager", propagation = Propagation.REQUIRED)
	public Long createOrder(@RequestBody() Order order) {
		log.info("Received new Order " + order.getOrderNumber() + " / " + order.getCustomer().getFirstName() + " "
				+ order.getCustomer().getLastName());

		addressRepository.save(order.getCustomer().getAddress());
		customerRepository.save(order.getCustomer());
		orderRepository.save(order);

		engineService.startOrderProcess(order.getOrderNumber(), order);

		if (order.getOrderFail()) {
			throw new RuntimeException("Order failed");
		}
		return order.getId();
	}

	@RequestMapping(value = "/checkOrderTask/{cotId}", method = RequestMethod.GET)
	@Transactional(transactionManager = "domainTransactionManager", propagation = Propagation.REQUIRED)
	public CheckOrderTask checkOrderTask(@PathVariable long cotId) {
		if (checkOrderTaskRepository.findById(cotId) == null) {
			throw new NoSuchElementException("No value present");
		}
		return checkOrderTaskRepository.findById(cotId).get();
//		return checkOrderTaskRepository.findById(cotId).orElseThrow();
	}
}