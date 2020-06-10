package com.spring.springmvcproject.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.springmvcproject.exception.CustomException;
import com.spring.springmvcproject.modal.Employee;

@Component
public class EmpUtils {

	
	public List<Employee> ParseFile(final MultipartFile file) throws CustomException{
		final List<Employee> employee= new ArrayList<>();
		try {		
			try(final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){			
			
			  String line;
			  while((line= br.readLine())!=null) {
				  final String[] data  =line.split(",");
				  Employee emp= new Employee();
			  emp.setEmp_first_name(data[0]);
			  emp.setEmp_last_name(data[1]);
			  employee.add(emp); 
			  }
			 	return employee;
			}
						
		}catch (Exception e) {
			throw new CustomException("parsing failed",e);
		}
	}
}
