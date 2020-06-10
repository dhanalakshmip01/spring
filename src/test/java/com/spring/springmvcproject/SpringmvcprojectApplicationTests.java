package com.spring.springmvcproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.spring.springmvcproject.Dao.EmployeeDao;
import com.spring.springmvcproject.exception.CustomException;
import com.spring.springmvcproject.modal.Employee;
import com.spring.springmvcproject.repository.EmployeeRepository;
import com.spring.springmvcproject.utils.EmpUtils;








@RunWith(SpringRunner.class)
@SpringBootTest

class SpringmvcprojectApplicationTests {
	
	

	@InjectMocks
	EmployeeDao employeeDao;

	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	EmpUtils emputils;
	
	@BeforeEach
	public void setUp() throws Exception {		
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void saveEmployeeTest() {
		Employee emp = new Employee(23, "abc", "def");
		employeeDao.saveEmployeeData(emp);
		verify(employeeRepository, times(1)).save(emp);
	}

	@Test
	public void getAllEmployeesTest() {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee(9, "kali", "charan"), new Employee(10, "dhana", "lak")).collect(Collectors.toList()));
		assertEquals(2, employeeDao.getAllEmployees().size());
	}

	@Test
	public void saveEmployeeDataTest() {
		Employee emp = new Employee(23, "abc", "def");
		when(employeeRepository.save(any())).thenReturn(emp);
		assertEquals(emp, employeeDao.saveEmployee(emp));
	}

	
	  @Test
	  public void updateEmployeeTest() throws CustomException { 	  
			Employee emp = new Employee(23, "abc", "def");
			Optional<Employee> empdata =Optional.of(new Employee(23, "abc", "def"));	
			when(employeeRepository.findById(anyInt())).thenReturn(empdata);
			when(employeeRepository.save(any())).thenReturn(emp);
			assertEquals(emp, employeeDao.updateEmployee(12,emp));  
	  
	  }
	  
	 @Test
	  public void deleteEmployeeTest() throws CustomException  {		  
		  String message ="employee record successfully deleted";			
			Optional<Employee> empdata =Optional.of(new Employee(23, "abc", "def"));	
			when(employeeRepository.findById(anyInt())).thenReturn(empdata);
			employeeRepository.delete(any());
			assertEquals(message, employeeDao.deleteEmployee(12));  
	  }
	 
	 @Test
	 public void getpincodeTest() throws CustomException {
		 when(employeeRepository.findEmployeeByPincode(anyLong())).thenReturn(Stream
				.of(new Employee(9, "kali", "charan"), new Employee(10, "dhana", "lak")).collect(Collectors.toList()));
		 assertEquals(2, employeeDao.getpincode(534316).size());
	 }
	 
	 @Test
	 public void getpincodeemptyTest() throws CustomException {
		 String message ="Not found employee with pincode 534316";		 
		 when(employeeRepository.findEmployeeByPincode(anyLong())).thenReturn(anyList());
		 CustomException thrown = assertThrows(
				 CustomException.class,
		           () -> employeeDao.getpincode(534316),
		           "Not found employee with pincode 534316"
		    );
		 assertTrue(thrown.getMessage().contains(message));    
			
	 }
	 
	 @Test
	public void getEmpByCityTest() throws CustomException {
		when(employeeRepository.findEmployeeByCity(anyString())).thenReturn(Stream
				.of(new Employee(9, "kali", "charan"), new Employee(10, "dhana", "lak")).collect(Collectors.toList()));
		 assertEquals(2, employeeDao.getEmpByCity("Bangalore").size());
	}
	 	 
	 @Test
	 public void getEmpByCityemptyTest() throws CustomException {
		 String message ="Not found employee with city bangalore";		 
		 when(employeeRepository.findEmployeeByCity(anyString())).thenReturn(anyList());
		 CustomException thrown = assertThrows(
				 CustomException.class,
		           () -> employeeDao.getEmpByCity("bangalore"),
		           "Not found employee with city bangalore"
		    );
		 assertTrue(thrown.getMessage().contains(message));    
			
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
		 when(employeeRepository.saveAll(any())).thenReturn(employee);
		 when (emputils.ParseFile(multipartFile)).thenReturn(employee);	
		 CompletableFuture<List<Employee>> cf= CompletableFuture.completedFuture(employee);
		 assertEquals(cf.isDone(),(employeeDao.saveEmployeesData(multipartFile)).isDone()); 
				 
	 }

}
