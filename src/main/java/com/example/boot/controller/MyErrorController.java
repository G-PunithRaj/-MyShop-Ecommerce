package com.example.boot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
	@RequestMapping("/error")
	public String handler(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println(status);
		int code=(Integer)status;
		if(code==404)
			return "404";
		else 
			return "error";
		
	}
//	@PostMapping("/error")
//	public String Handler(HttpServletRequest request) {
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		System.out.println(status);
//		int code=(Integer)status;
//		if(code==404)
//			return "404";
//		else 
//			return "error";
//		
//	}
}