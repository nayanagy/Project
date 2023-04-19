package com.xworkz.user.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "application_table")
@NamedQuery(name = "findAll", query = "select entity from ApplicationEntity entity")
@NamedQuery(name = "checkdupl", query = "Select count(entity) from ApplicationEntity entity "
		+ " where entity.userId=:userId OR entity.email=:userEmail OR entity.mobile=:userMobile ")
@NamedQuery(name = "userId", query = "select count(*) from  ApplicationEntity ent where ent.userId=:userBy")
@NamedQuery(name = "emailId", query = "select count(*) from  ApplicationEntity ent where ent.email=:emailBy")
@NamedQuery(name = "mobileId", query = "select count(*) from  ApplicationEntity ent where ent.mobile=:mobileBy")
@NamedQuery(name = "findBySignIn", query = "select entity from ApplicationEntity entity where entity.userId=:us")
@NamedQuery(name = "updateLoginCount", query = "update ApplicationEntity entity set entity.loginCount=:count where entity.userId=:user")
@NamedQuery(name = "resetEmail", query = "select entity from ApplicationEntity entity where entity.email=:email")
@NamedQuery(name = "updatePassword", query = "update ApplicationEntity entity set entity.password=:userPass , entity.resetPassword=:resetPass,entity.loginTime=:timer where entity.userId=:usr")
public class ApplicationEntity {
	@Id
	@Column(name = "a_id")
	private int id;
	@Column(name = "a_userId")
	private String userId;
	@Column(name = "a_email")
	private String email;
	@Column(name = "a_mobile")
	private Long mobile;
	@Column(name = "a_password")
	private String password;
	@Column(name = "a_createdBy")
	private String createdBy;
	@Column(name = "a_createdDate")
	private LocalDateTime createdDate;
	@Column(name = "a_updatedBy")
	private String updatedBy;
	@Column(name = "a_updatedDate")
	private LocalDateTime updatedDate;
	@Column(name = "a_loginCount")
	private int loginCount;
	@Column(name = "a_resetPassword")
	private boolean resetPassword;
	@Column(name = "a_otptimer")
	private LocalTime loginTime;
}
