package com.spring.springmvcproject.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.springmvcproject.Dao.EmployeeDao;
import com.spring.springmvcproject.exception.CustomException;
import com.spring.springmvcproject.modal.Employee;



@Service
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;	
	
	
	public List<Employee> getEmployees(){
		return employeeDao.getEmployees();
	}


	public void saveEmployeeData(Employee empdatalist) {
		employeeDao.saveEmployeeData(empdatalist);
		
	}
		public List<Employee> getAllEmployees() {		
		return employeeDao.getAllEmployees();
	}
		public Employee saveEmployee(Employee emp) {			
			return employeeDao.saveEmployee(emp);
		}

		public Employee updateEmployee(int id, Employee emp) throws CustomException {			
			return employeeDao.updateEmployee(id,emp);
		}

		public String deleteEmployee(int id) throws CustomException {			
			return employeeDao.deleteEmployee(id);
		}
		
		public CompletableFuture<List<Employee>> saveEmployeesData(MultipartFile file) throws CustomException{
		return employeeDao.saveEmployeesData(file);
		}


		public List<Employee> getPincode(long pincode) throws CustomException {
			return employeeDao.getpincode(pincode);
		}


		public List<Employee> getEmpByCity(String city) throws CustomException {			
			return employeeDao.getEmpByCity(city);
		}


		
}
