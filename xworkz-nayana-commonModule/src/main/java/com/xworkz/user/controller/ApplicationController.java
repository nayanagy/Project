package com.xworkz.user.controller;

import java.time.LocalTime;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.user.dto.ApplicationDTO;
import com.xworkz.user.service.ApplicationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	public ApplicationController() {
		log.info("Created " + this.getClass().getSimpleName());
	}

	@PostMapping("/signUp")
	public String onSignUp(Model model, ApplicationDTO dto, String userId, String email, Long mobile) {
		log.info("Running onSignUp");
		Long count = this.applicationService.checkDuplicates(userId, email, mobile);
		if (count > 0) {
			log.error("Duplicates values is thier");
			model.addAttribute("Msg", "UserID or Email or MobileNo already exists");
		} else {
			Set<ConstraintViolation<ApplicationDTO>> violation = this.applicationService.validate(dto);
			if (violation.isEmpty()) {
				log.info("No Violation in controller");
				boolean saved = this.applicationService.saveService(dto);
				log.info("saved " + saved);
				model.addAttribute("message", "Registration Sucessfull");
				return "SignUp";
			} else {
				model.addAttribute("errors", violation);
				model.addAttribute("message1", "Registration failed");

			}
		}
		return "SignUp";
	}
//		log.info("Running onApplication on post mapping" + dto);
//
//		List<ApplicationDTO> dto1 = this.applicationService.findAllService();
//		for (ApplicationDTO Dto : dto1) {
//			if (dto.getUserId().equals(Dto.getUserId())) {
//				model.addAttribute("userMsg", "UserID already exists");
//				return "SignUp";
//			} else if (dto.getEmail().equals(Dto.getEmail())) {
//				model.addAttribute("emailMsg", "Email already exists");
//				return "SignUp";
//			} else if (dto.getMobile().equals(Dto.getMobile())) {
//				model.addAttribute("mobileMsg", "MobileNo already exists");
//				return "SignUp";
//			}
//
//		}
//		Set<ConstraintViolation<ApplicationDTO>> violations = this.applicationService.validate(dto);
//		if (!violations.isEmpty()) {
//			log.info("violations in controller go to index page");
//			model.addAttribute("msg", violations);
//		}
//
//		if (dto.getPassword().equals(dto.getConfirmPassword())) {
//			boolean saved = this.applicationService.saveService(dto);
//			log.info("Saved :" + saved);
//			model.addAttribute("dto", dto);
//
//			model.addAttribute("success", "SignedUp Successfully");
//			return "SignUp";
//		} else {
//			model.addAttribute("passwordMsg", "Password does not match");
//			return "SignUp";
//		}
//
//}

	@PostMapping("/signIn")
	public String onSignIn(Model model, @RequestParam String userId, @RequestParam String password,
			HttpServletRequest request) {
		log.info("Running on SignIn " + userId + password);
		try {
			ApplicationDTO dto = this.applicationService.findByUserAndPassword(userId, password);
			log.info("Login count " + dto.getLoginCount());
			if (dto.getLoginCount() >= 3) {
				log.info("Account locked for sign in reset password");
				model.addAttribute("lockMsg", "Account locked for sign in reset password");
				return "SignIn";
			}
			if (dto != null) {

				if (dto.isResetPassword() == true) {
					if (!dto.getLoginTime().isAfter(LocalTime.now())) {
						log.info("Running in time verifying condition");
						model.addAttribute("msgs", "Time out please try again");
						return "SignIn";
					}
					model.addAttribute("userID", dto.getUserId());
					log.info("Running in reset condition");
					log.info("ResetPassword---" + dto.isResetPassword());
					log.info("Timer-----" + dto.getLoginTime().isBefore(LocalTime.now()));
					return "UpdatePassword";
				}
				System.currentTimeMillis();
				log.info("UserId and password are match");
				HttpSession httpSession = request.getSession(true);
				httpSession.setAttribute("userId", dto.getUserId());
				return "LoginSuccess";
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("UserID OR Password is not matching");
		model.addAttribute("message", "UserId or password are invalid");
		return "SignIn";
	}

	@PostMapping("/reset")
	public String reSetPassword(String email, Model model) {
		ApplicationDTO udto = this.applicationService.reSetPassword(email);
		if (udto.isResetPassword() == true) {
			log.info("Password reset sucessful plz login with in 2 min with otp");
			model.addAttribute("message1",
					"Password reset successful please login within 2 min otherwise password expired");
			return "ResetPassword";
		}
		return "ResetPassword";
	}

	@PostMapping("/updatePassword")
	public String upDatePassword(String userId, String password, String confirmPassword, Model model) {
		this.applicationService.updatePassword(userId, password, confirmPassword);
		model.addAttribute("message2", "Password Update successful");
		return "UpdatePassword";
	}
}
