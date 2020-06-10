
package com.spring.springmvcproject.modal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "adrid")
	private int AddressId;

	@Column(name = "city")
	private String city;

	@Column(name = "pincode")
	private Long pincode;

	@Column(name = "phnnum")
	private Long phnnum;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id",referencedColumnName = "emp_id")
	@JsonIgnore
	private Employee employee;

	
}
