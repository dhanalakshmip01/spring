package com.spring.springmvcproject.controller;


import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.spring.springmvcproject.exception.CustomException;
import com.spring.springmvcproject.modal.Employee;
import com.spring.springmvcproject.service.EmployeeService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;



@RestController
public class EmployeeController {

	   private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;
	
	  @GetMapping(value= "/employee" )
	  public List<Employee> getEmployees() {
		  List<Employee>  empdata=employeeService.getEmployees();
	  return empdata;
	  }
	  
	  
	  @GetMapping(value="/employeesdata")
		public List<Employee> getAllEmployees() {	
			return employeeService.getAllEmployees();		
		}
	  
	  @PostMapping(value="/saveemp")
	  public Employee saveEmployee(@RequestBody Employee emp) {
		  return  employeeService.saveEmployee(emp);
	  }
	  
	  @PutMapping(value ="/employee/{id}")	  
	  public Employee updateEmployee(@PathVariable int id,@RequestBody Employee emp) throws CustomException {
		return employeeService.updateEmployee(id,emp);
		  
	  }
	  
	  @DeleteMapping(value="/employee/{id}")
	  public String deleteEmployee(@PathVariable int id) throws CustomException {
		  return employeeService.deleteEmployee(id);
	  }
	  
	  @GetMapping("/employeedata/{pincode}")
	    public List<Employee> getEmployeeDataByPincode(@PathVariable("pincode")  long pincode) throws CustomException {
	        return employeeService.getPincode(pincode);
	  }
	  
	  @GetMapping("/employee/{city}")
	    public List<Employee> getEmpByCity(@PathVariable  String city) throws CustomException {
	        return employeeService.getEmpByCity(city);
	  }
	  
	 @SuppressWarnings("rawtypes")
	@PostMapping(value="/saveemployeedata",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces= "application/json")
	  public ResponseEntity saveEmployeesData(@RequestParam(value ="files") MultipartFile files) throws CustomException{
		  Arrays.asList(files).parallelStream().forEach(file->{
			
				try {
					employeeService.saveEmployeesData(file);
				} catch (CustomException e) {
					e.printStackTrace();
				}
				
		  }); 
		 	 
		  return  ResponseEntity.status(HttpStatus.CREATED).build();
			
	  }
	  
	  
	  
}
