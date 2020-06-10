package com.spring.springmvcproject.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.springmvcproject.exception.CustomException;
import com.spring.springmvcproject.modal.Employee;
import com.spring.springmvcproject.repository.AddressRepository;
import com.spring.springmvcproject.repository.EmployeeRepository;
import com.spring.springmvcproject.utils.EmpUtils;



@Component
public class EmployeeDao {
	
	Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	AddressRepository 	addressRepository;
	
	@Autowired
	EmpUtils empUtils;
	
	private static List<Employee> employee;
	{
		employee = new ArrayList<Employee>();
		employee.add(new Employee(9,"kali","charan"));
		employee.add(new Employee(10,"dhana", "lak"));
				
	}
		public List<Employee> getEmployees() {
		return employee;
	}

	public void saveEmployeeData(Employee empdatalist) {
		employeeRepository.save(empdatalist);
		
	}
	@Cacheable(value="employeeCache")
	public List<Employee> getAllEmployees() {		
		return employeeRepository.findAll();
	}

	public Employee saveEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

	@CachePut(value="employeeCache")
	public Employee updateEmployee(int id, Employee emp) throws CustomException {
		return employeeRepository.findById(id).map(employee -> {
			employee.setEmp_first_name(emp.getEmp_first_name());
			employee.setEmp_last_name(emp.getEmp_last_name());
			return employeeRepository.save(employee);
		}).orElseThrow(() -> new CustomException("employee not found with id" +id ));			
	
	}

	@CacheEvict(value="employeeCache",allEntries = true,key = "#id")
	public String deleteEmployee(int id) throws CustomException {
		return employeeRepository.findById(id).map(employee ->{
			employeeRepository.delete(employee);
			return "employee record successfully deleted";
		}).orElseThrow(()-> new CustomException("employee not found with id" +id));
	}
	
	public List<Employee> getpincode(long pincode) throws CustomException {
		List<Employee> employees= employeeRepository.findEmployeeByPincode(pincode);
		 if(employees.isEmpty())
	            throw new CustomException("Not found employee with pincode " + pincode);		 
		 	        return employees;
	}
	
	public List<Employee> getEmpByCity(String city) throws CustomException {
	
		List<Employee> employees= employeeRepository.findEmployeeByCity(city);
		 if(employees.isEmpty())
	            throw new CustomException("Not found employee with city " + city);
	        return employees;
			}
	
	@Async
	public CompletableFuture<List<Employee>> saveEmployeesData(MultipartFile file) throws CustomException{
		List<Employee> employees= empUtils.ParseFile(file);
		employees = employeeRepository.saveAll(employees);
		return CompletableFuture.completedFuture(employees);
		
		}
	
	
	

	

	
	
}