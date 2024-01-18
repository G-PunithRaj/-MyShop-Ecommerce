package com.example.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


	boolean existsByMobile(long mobile);

	boolean existsByGmail(String gmail);
  
}
