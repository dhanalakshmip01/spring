package com.spring.springmvcproject.modal;




import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name="employee")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull	
	private Integer emp_id;
	@Column(name="emp_first_name")
	@NonNull
	private String emp_first_name;
	@Column(name="emp_last_name")
	@NonNull
	private String emp_last_name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "emp_id")
	  private List<Address> address;	 
	
	
	
}
