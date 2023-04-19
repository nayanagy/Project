package com.xworkz.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.user.service.ApplicationService;

import lombok.extern.slf4j.Slf4j;

@EnableWebMvc
@RestController
@RequestMapping("/")
@Slf4j
public class AjaxController {

	@Autowired
	private ApplicationService applicationService;

	public AjaxController() {
		log.info("Created " + this.getClass().getSimpleName());
	}

	@GetMapping(value = "/email/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmail(@PathVariable String emailId) {
		log.info("Running onEmail in controller");
		Long dbEmail = this.applicationService.findByEmail(emailId);
		log.error(" " + dbEmail);
		if (dbEmail == 0) {
			log.error("Running in equals condition");
			return "";
		} else {
			return "Email id exist";
		}
	}

	@GetMapping(value = "/userId/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onUser(@PathVariable String user) {
		log.info("Running onUser in controller");
		Long dbUser = this.applicationService.findByUser(user);
		log.error(" " + dbUser);
		if (dbUser == 0) {
			log.info("Running in equals condition");
			return "";
		} else {
			return "UserID exist";
		}
	}

	@GetMapping(value = "/mobile/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onMobile(@PathVariable Long number) {
		log.info("Running onMobile in controller");
		Long dbNumber = this.applicationService.findByMobile(number);
		log.error(" " + dbNumber);
		if (dbNumber == 0) {
			log.error("Running in equals condition");
			return "";
		} else {
			return "Mobile Number exist";
		}
	}

//	@GetMapping(value = "/resetEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public String forResetPsswordEmail(@PathVariable String email) {
//		Long dbEmail = this.applicationService.findByEmail(email);
//		log.error(" "+dbEmail);
//
//		if (dbEmail == 0) {
//			log.error("Running in equals condition");
//			return "Please enter Existing email Id";
//		} else {
//			return "  ";
//		}
//	}
}
