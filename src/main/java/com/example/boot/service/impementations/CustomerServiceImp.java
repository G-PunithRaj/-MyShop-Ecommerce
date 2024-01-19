package com.example.boot.service.impementations;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.example.boot.dao.CustomerDao;
import com.example.boot.dto.Customer;
import com.example.boot.helper.AES;
import com.example.boot.helper.MailSendingHelper;
import com.example.boot.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	CustomerDao customerDao;
	@Autowired
	MailSendingHelper mailhelper;

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
			return "redirect:/send-otp/" + customer.getId();

		}
	}

	@Override
	public String sendOtp(int id, ModelMap map) {
		Customer customer = customerDao.findById(id);
		customer.setOtp(new Random().nextInt(100000, 999999));
		System.out.println(customer);
		customerDao.save(customer);
		mailhelper.sendOtp(customer);
		// Logic for Sending Otp
		map.put("id", id);
		map.put("SuccessMsg", "OTP Sent");
		return "verifyOtp";
	}

	public String verifyOtp(int id, int otp, ModelMap map) {
		Customer customer = customerDao.findById(id);
		if (customer.getOtp()==otp) {
			customer.setVerified(true);
			customerDao.save(customer);
			return "redirect:/login";
		} else {
			map.put("failMsg", "Invalid OTP, Retry!");
			map.put("id", id);
			return "verifyOtp";
		}
	}

	@Override
	public String resendOtp(int id, ModelMap map) {
		Customer customer= customerDao.findById(id);
		customer.setOtp(new Random().nextInt(100000,999999));
		customerDao.save(customer);
		mailhelper.resendOtp(customer);
		map.put("id", id);
		map.put("SuccessMsg","Enter new OTP");
		return "verifyOtp";
	}
}
