package com.example.boot.helper;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.boot.dto.Customer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailSendingHelper {

	@Autowired
	JavaMailSender mailsender;

	public void sendOtp(Customer customer) {
		MimeMessage msg = mailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);

		try {
			helper.setFrom("punithcodecraft@gmail.com", "CodeCraft-MyShop");
			helper.setTo(customer.getGmail());
			helper.setSubject("OTP Verification");

			String gen = "";
			if (customer.getGender().equalsIgnoreCase("Male")) {
				gen = "Mr. ";
			} else {
				gen = "Ms. ";
			}
			String body ="<html><body>" 
			           + "<h1>Hello " + gen + customer.getName() + "</h1>" 
			           + "<h2>Your OTP is:"+ customer.getOtp() + "</h2>" 
					   + "<h3>Thank you Regards...</h3>" 
			           + "</body></html>";
			helper.setText(body, true);
			mailsender.send(msg);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}

	}

	public void resendOtp(Customer customer) {
		MimeMessage msg = mailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);

		try {
			helper.setFrom("punithcodecraft@gmail.com", "CodeCraft-MyShop");
			helper.setTo(customer.getGmail());
			helper.setSubject("OTP Verification");

			String gen = "";
			if (customer.getGender().equalsIgnoreCase("Male")) {
				gen = "Mr. ";
			} else {
				gen = "Ms. ";
			}
			String body ="<html><body>" 
			           + "<h1>Hello " + gen + customer.getName() + "</h1>" 
			           + "<h2>Your OTP is:"+ customer.getOtp() + "</h2>" 
					   + "<h3>Thank you Regards...</h3>" 
			           + "</body></html>";
			helper.setText(body, true);
			mailsender.send(msg);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
	}
}
