package com.xworkz.user.dto;

import java.time.LocalTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ApplicationDTO {

	private int id;
	@NotBlank(message = "UserId cannot be null")
	@Size(min = 4, max = 20, message = "UserId should be more than 4 or less than 20 characters")
	private String userId;
	@NotBlank(message = "Email cannot be null")
	@Size(min = 5, max = 30, message = "Email should be more than 5 or less than 30 characters")
	@Email
	private String email;
	@NotNull(message = "Mobile cannot be null or blank")
	@Min(value = 1, message = "Mobile cannot be less than zero")
	private Long mobile;
	@NotBlank(message = "Password cannot be null")
	@Size(min = 3, max = 10, message = "Password should be more than 3 or less than 10 characters")
	private String password;
	@NotBlank(message = "Name cannot be null")
	@Size(min = 3, max = 10, message = "ConfirmPassword should be more than 3 or less than 10 characters")
	private String confirmPassword;
	private int loginCount;
	private boolean resetPassword;
	private LocalTime loginTime;
}
