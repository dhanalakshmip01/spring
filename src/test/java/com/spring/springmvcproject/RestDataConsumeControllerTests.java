package com.spring.springmvcproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.spring.springmvcproject.controller.RestDataConsumeController;
import com.spring.springmvcproject.modal.Employee;
import com.spring.springmvcproject.service.EmployeeService;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RestDataConsumeControllerTests {

	@InjectMocks
	RestDataConsumeController restDataConsumeController;
	
	@Mock
	EmployeeService empService;
	
	@Mock
	private RestTemplate restTemplate;
	
	
	@BeforeEach
	public void setUp() throws Exception {		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveEmployeeDataTest() {
		List<Employee> employee;
		{
			employee = new ArrayList<Employee>();			
			employee.add(new Employee(10,"dhana", "lak"));					
		}
		Employee emp = new Employee(10,"dhana", "lak");
		when(restTemplate.getForObject(anyString(),any())).thenReturn( employee.toArray());
		restDataConsumeController.saveEmployeeData();
		verify(empService, times(1)).saveEmployeeData(emp);
		
	}
	
	
	
}
