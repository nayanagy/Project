package com.xworkz.user.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.user.dto.ApplicationDto;
import com.xworkz.user.service.ApplicationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	public ApplicationController() {
		log.info("Running Controller");
	}

	@PostMapping("/signUp")
	public String onSignUp(ApplicationDto applicationDto, Model model) {
		log.info("Running onSignUp");
		Set<ConstraintViolation<ApplicationDto>> violation = this.applicationService.validate(applicationDto);
		log.info("Size :" + violation.size());
		violation.forEach(cv -> log.info(cv.getMessage()));
		if (violation.isEmpty()) {
			List<ApplicationDto> dto = this.applicationService.findAllService();
			for (ApplicationDto Dto : dto) {
				if (applicationDto.getUserId().equals(Dto.getUserId())) {
					model.addAttribute("userMsg", "UserID already exists");
					return "index";
				} else if (applicationDto.getEmail().equals(Dto.getEmail())) {
					model.addAttribute("emailMsg", "Email already exists");
					return "index";
				} else if (applicationDto.getMobile().equals(Dto.getMobile())) {
					model.addAttribute("mobileMsg", "MobileNo already exists");
					return "index";
				}
			}
			if (applicationDto.getPassword().equals(applicationDto.getConfirmPassword())) {
				boolean saved = this.applicationService.saveService(applicationDto);
				log.info("Saved :" + saved);
				model.addAttribute("success", "SignedUp Successfully");
				return "index";
			} else {
				model.addAttribute("passwordMsg", "Password does not match");
				return "index";
			}
		}

		model.addAttribute("violation", violation);
		return "index";

	}

	@PostMapping("/signIn")
	public String onSignIn(Model model, ApplicationDto applicationDto, @RequestParam String userId,
			@RequestParam String password) {
		log.info("Running on SignIn " + userId + password);
		List<ApplicationDto> dto1 = this.applicationService.findByUserAndPassword(userId, password);
		for (ApplicationDto Dto : dto1) {
			if (Dto != null) {
				log.info("UserId and password are match");
				model.addAttribute("userId", Dto.getUserId());
				return "SignInSuccess";
			}
		}
		model.addAttribute("message", "UserId or password are invalid");
		return "SignIn";
	}

}
