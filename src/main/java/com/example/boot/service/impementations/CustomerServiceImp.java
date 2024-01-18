package com.example.boot.service.impementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.boot.dao.CustomerDao;
import com.example.boot.dto.Customer;
import com.example.boot.helper.AES;
import com.example.boot.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	public String save(Customer customer, BindingResult result) {
		if (customerDao.checkGmailDuplicate(customer.getGmail()))
			result.rejectValue("gmail", "error.gmail", "* Gmail Already Exist");
		if (customerDao.checkMobileDuplicate(customer.getMobile()))
			result.rejectValue("mobile", "error.mobile", "* Mobile Number Already Exist");

		if (result.hasErrors()) {
			return "Signup";
		} else {
			customer.setPassword(AES.encrypt(customer.getPassword(), "123"));
			customer.setRole("User");
			customerDao.save(customer);
			return "Login";

		}
	}
}
