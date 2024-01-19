package com.example.boot.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "* Can Not Be Empty")
	@Size(min = 3, max = 10, message = "* Should Be Between 3 to 10 Char")
	private String name;
	@NotEmpty(message = "* Can Not Be Empty")
	@Email(message = "* Enter a Proper Email")
	private String gmail;
	@NotEmpty(message = "* Can Not Be Empty")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "* Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	private String password;
	@NotEmpty(message = "* Can Not Be Empty")
	private String gender;
	@NotNull(message = "* Can Not Be Empty")
	@DecimalMax(value = "9999999999", inclusive = true, message = "* Enter Proper Number")
	@DecimalMin(value = "6000000000", inclusive = true, message = "* Enter Proper Number")
	private Long mobile;
	@NotNull(message = "* Can Not Be Empty")
	@Past(message = "*Enter Proper Dob")
	private LocalDate dob;
	private String role;
	private int otp;
	boolean verified;
}
