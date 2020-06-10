package com.spring.springmvcproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springmvcproject.modal.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
