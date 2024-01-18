package com.example.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot.dto.Customer;
import com.example.boot.repository.CustomerRepository;
@Repository
public class CustomerDao {

	@Autowired
	CustomerRepository repository;

	public Customer save(Customer customer) {
		return repository.save(customer);
	}
	
	public boolean checkGmailDuplicate(String gmail) {
		return repository.existsByGmail(gmail);
	}

	public boolean checkMobileDuplicate(long mobile) {
		return repository.existsByMobile(mobile);
	}
}
