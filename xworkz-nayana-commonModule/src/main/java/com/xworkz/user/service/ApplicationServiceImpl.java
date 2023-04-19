package com.xworkz.user.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xworkz.user.dto.ApplicationDTO;
import com.xworkz.user.entity.ApplicationEntity;
import com.xworkz.user.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ApplicationServiceImpl() {
		log.info("Created " + this.getClass().getSimpleName());
	}

	@Override
	public Set<ConstraintViolation<ApplicationDTO>> validate(ApplicationDTO dto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ApplicationDTO>> violations = validator.validate(dto);
		if (violations != null && !violations.isEmpty()) {
			log.error("Violations in dto " + dto);
			return violations;
		} else {
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));
			;
			log.info("Violations is not there in dto,can save");
			return Collections.emptySet();
		}
	}

	@Override
	public Long findByEmail(String email) {
		Long emailcount = this.applicationRepository.findByEmail(email);
		log.info("Find by Email");
		return emailcount;
	}

	@Override
	public Long findByMobile(Long mobile) {
		Long mobilecount = this.applicationRepository.findByMobile(mobile);
		log.info("Find by mobile");
		return mobilecount;
	}

	@Override
	public Long findByUser(String userId) {
		Long userCount = this.applicationRepository.findByUser(userId);
		log.info("Find by userId");
		return userCount;
	}

	@Override
	public List<ApplicationDTO> findAllService() {
		log.info("Running findAll");
		List<ApplicationEntity> entity = this.applicationRepository.findAll();
		List<ApplicationDTO> dtos = new ArrayList<ApplicationDTO>();
		for (ApplicationEntity applicationEntity : entity) {
			ApplicationDTO dto = new ApplicationDTO();
			dto.setUserId(applicationEntity.getUserId());
			dto.setEmail(applicationEntity.getEmail());
			dto.setMobile(applicationEntity.getMobile());
			dto.setPassword(applicationEntity.getPassword());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public boolean saveService(ApplicationDTO appDTO) {
		log.info("Running saveService");
		ApplicationEntity entity = new ApplicationEntity();
		entity.setCreatedDate(LocalDateTime.now());
		entity.setCreatedBy(appDTO.getUserId());
		entity.setResetPassword(false);
		BeanUtils.copyProperties(appDTO, entity);
		entity.setLoginTime(LocalTime.of(0, 0, 0));
		boolean saved = this.applicationRepository.save(entity);
		if (saved) {
			boolean sent = this.sendMail(appDTO.getEmail(), "Thanks for registration");
			log.info("Email sent -:" + sent);
		}
		return true;
	}

	@Override
	public Long checkDuplicates(String userId, String email, Long mobile) {
		Long count = this.applicationRepository.checkDuplicates(userId, email, mobile);
		ApplicationEntity entity = new ApplicationEntity();
		ApplicationDTO dto = new ApplicationDTO();
		dto.setUserId(entity.getUserId());
		dto.setEmail(entity.getEmail());
		dto.setMobile(entity.getMobile());

		return count;
	}

	@Override
	public ApplicationDTO findByUserAndPassword(String userId, String password) {
		log.info("Running findByUserAndPassword in service ");
		ApplicationEntity entity = this.applicationRepository.findBySignIn(userId);
		ApplicationDTO dto = new ApplicationDTO();
		BeanUtils.copyProperties(entity, dto);
		log.info("matching--" + passwordEncoder.matches(password, entity.getPassword()));
		log.info("Time matching--" + entity.getLoginTime().isBefore(LocalTime.now()));
		log.info("Present Time--" + LocalTime.now());
		log.info("Matching password " + passwordEncoder.matches(password, entity.getPassword()));
		log.info("Time " + LocalTime.now().isBefore(entity.getLoginTime()));
		if (entity.getLoginCount() >= 3) {
			log.info("Running logincount in condition ");
			return dto;
		}
		if (dto.getUserId().equals(userId) && passwordEncoder.matches(password, entity.getPassword())) {
			return dto;
		} else {
			this.applicationRepository.loginCount(userId, entity.getLoginCount() + 1);
			log.info("Count to login " + entity.getLoginCount() + 1);
			return null;
		}
	}

	@Override
	public ApplicationDTO reSetPassword(String email) {
		String reSetPassword = DefaultPasswordGenerator.generate(8);
		log.info("Reseted password :" + reSetPassword);
		ApplicationEntity entity = this.applicationRepository.reSetPassword(email);
		if (entity != null) {
			entity.setPassword(passwordEncoder.encode(reSetPassword));
			entity.setUpdatedBy("Owner");
			entity.setUpdatedDate(LocalDateTime.now());
			entity.setLoginCount(0);
			entity.setResetPassword(true);
			entity.setLoginTime(LocalTime.now().plusSeconds(120));
			boolean update = this.applicationRepository.update(entity);
			if (update) {
				sendMail(entity.getEmail(), "Your  reseted password is-> " + reSetPassword);
			}
			log.info("Updated---" + update);
			ApplicationDTO updatedDto = new ApplicationDTO();
			BeanUtils.copyProperties(entity, updatedDto);
			return updatedDto;
		}
		return ApplicationService.super.reSetPassword(email);
	}

	@Override
	public ApplicationDTO updatePassword(String userId, String password, String confirmPassword) {
		// ApplicationEntity entity = new ApplicationEntity();
		if (password.equals(confirmPassword)) {
//			entity.setUserId(userId);
//			entity.setPassword(passwordEncoder.encode(password));
//			entity.setResetPassword(false);
			boolean passwordUpdated = this.applicationRepository.updatePassword(userId,
					passwordEncoder.encode(password), false, LocalTime.of(0, 0, 0));
			log.info("passwordUpdated--" + passwordUpdated);

		}
		return ApplicationService.super.updatePassword(userId, password, confirmPassword);
	}

	@Override
	public boolean sendMail(String email, String text) {
		String reSetPassword = DefaultPasswordGenerator.generate(6);
		String portNumber = "587";// 485,587,25
		String hostName = "smtp.office365.com";
		String fromEmail = "nayanagy95@outlook.com";
		String password = "nayana@9535230920";
		String to = email;

		Properties prop = new Properties();
		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
// 			message.setSubject("Registration Completed");
			message.setText("Thanks for reset password and your password is" + reSetPassword);
			message.setText(text);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);
			log.info("mail sent sucessfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}

	public final static class DefaultPasswordGenerator {
		private static final String[] charCategories = new String[] { "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "0123456789",
				"abcdefghijklmnopqrstuvwxyz" };

		public static String generate(int length) {
			StringBuilder password = new StringBuilder(length);
			Random random = new Random(System.nanoTime());
			;

			for (int i = 0; i < length; i++) {
				String charCategory = charCategories[random.nextInt(charCategories.length)];
				int position = random.nextInt(charCategory.length());
				password.append(charCategory.charAt(position));
			}

			return new String(password);
		}

		String password = DefaultPasswordGenerator.generate(8);
	}
}
