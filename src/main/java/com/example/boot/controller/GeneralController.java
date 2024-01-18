package com.example.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot.dto.Customer;
import com.example.boot.service.CustomerService;

import jakarta.validation.Valid;

@Controller
public class GeneralController {

	@Autowired
     Customer customer;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public String LoadHome() {
		return "Home";
	}

	@GetMapping("/signup")
	public String LoadSignup(ModelMap map) {
		map.put("customer", customer);
		return "Signup";
	}
	@GetMapping("/login")
	public String LoadLogin() {
		return "Login";
	}

	@PostMapping("/signup")
	public String signin(@Valid Customer customer, BindingResult result) {
		if (result.hasErrors()) {
			return "Signup";
		} else {
			System.out.println(customer);
			return customerService.save(customer, result);
		}

	}
}
