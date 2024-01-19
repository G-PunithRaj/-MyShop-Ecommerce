package com.example.boot.service;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.example.boot.dto.Customer;

public interface CustomerService {
	String save(Customer customer, BindingResult result);

	String sendOtp(int id, ModelMap map);

	String verifyOtp(int id, int otp, ModelMap map);

	String resendOtp(int id, ModelMap map);
}
