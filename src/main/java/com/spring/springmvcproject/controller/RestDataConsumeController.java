package com.spring.springmvcproject.controller;


import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.springmvcproject.modal.Employee;
import com.spring.springmvcproject.service.EmployeeService;

@RestController
public class RestDataConsumeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@PostMapping(value = "/savedata")
	public void saveEmployeeData() {		
		Employee[] empdatalist = restTemplate.getForObject("http://localhost:8085/employee",Employee[].class);	
			Arrays.asList(empdatalist).parallelStream().forEach(data ->employeeService.saveEmployeeData(data));	
	}
	
	
	
	
}				