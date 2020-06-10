package com.spring.springmvcproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import com.spring.springmvcproject.controller.EmployeeController;
import com.spring.springmvcproject.exception.CustomException;
import com.spring.springmvcproject.modal.Employee;
import com.spring.springmvcproject.service.EmployeeService;
import static org.mockito.ArgumentMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest

class EmployeeControllerTests {
	
	@InjectMocks
	EmployeeController empcontroller;
	
	@Mock
	EmployeeService empService;
	
	@BeforeEach
	public void setUp() throws Exception {		
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void getEmployeesTest() {
		when(empService.getEmployees()).thenReturn(Stream
				.of(new Employee(9, "kali", "charan"), new Employee(10, "dhana", "lak")).collect(Collectors.toList()));
		assertEquals(2, empcontroller.getEmployees().size());
	}

	
	@Test
	public void getAllEmployeesTest() {
		when(empService.getAllEmployees()).thenReturn(Stream
				.of(new Employee(9, "kali", "charan"), new Employee(10, "dhana", "lak")).collect(Collectors.toList()));
		assertEquals(2, empcontroller.getAllEmployees().size());

	}
	
	@Test
	public void saveEmployeeTest() {
		Employee emp = new Employee(23, "abc", "def");
		empcontroller.saveEmployee(emp);
		verify(empService, times(1)).saveEmployee(emp);
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee emp = new Employee(23, "abc", "def");		
		try {
			when(empService.updateEmployee(anyInt(),any())).thenReturn(emp);
			assertEquals(emp, empcontroller.updateEmployee(23,emp));
		} catch (CustomException e) {			
			e.printStackTrace();
		} 
	}
	
	@Test
	public void deleteEmployeeTest() {
		 String message ="employee record successfully deleted";	
		 try {
			when(empService.deleteEmployee(anyInt())).thenReturn(message);
			assertEquals(message, empcontroller.deleteEmployee(23));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	@Test
	public void getEmployeeDataByPincodeTests() {
		try {
			when(empService.getPincode(anyLong())).thenReturn(Stream
					.of(new Employee(9, "kali", "charan"), new Employee(10, "dhana", "lak")).collect(Collectors.toList()));
			 assertEquals(2, empcontroller.getEmployeeDataByPincode(534316).size());
		} catch (CustomException e) {			
			e.printStackTrace();
		}
	}
	@Test
	public void getEmpByCityTests() {
		try {
			when(empService.getEmpByCity(anyString())).thenReturn(Stream
					.of(new Employee(9, "kali", "charan"), new Employee(10, "dhana", "lak")).collect(Collectors.toList()));
			 assertEquals(2, empcontroller.getEmpByCity("Bangalore").size());
		} catch (CustomException e) {			
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveEmployeesDataTest() throws CustomException {
		List<Employee> employee;
		{
			employee = new ArrayList<Employee>();			
			employee.add(new Employee(10,"dhana", "lak"));					
		}
		MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
			      "text/plain", "dhana,lak".getBytes());
		CompletableFuture<List<Employee>> cf= CompletableFuture.completedFuture(employee);
		
		when(empService.saveEmployeesData(any())).thenReturn(cf);
		assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), empcontroller.saveEmployeesData(multipartFile));
	}
}
