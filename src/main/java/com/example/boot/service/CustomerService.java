package com.example.boot.service;

import org.springframework.validation.BindingResult;

import com.example.boot.dto.Customer;

public interface CustomerService {
	String save(Customer customer, BindingResult result);
}
