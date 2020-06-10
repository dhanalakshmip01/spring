package com.spring.springmvcproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.springmvcproject.modal.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value= "select e FROM Employee e join e.address a WHERE a.pincode =:pincode")
    public List<Employee> findEmployeeByPincode(@Param("pincode") long pincode);

	
	  @Query(value="select e FROM Employee e join e.address a WHERE a.city =:city")
	    public List<Employee> findEmployeeByCity(@Param("city") String city);


}
