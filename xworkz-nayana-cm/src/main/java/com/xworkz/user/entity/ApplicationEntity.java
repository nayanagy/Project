package com.xworkz.user.entity;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "application_table")
@NamedQuery(name = "findAll",query = "select entity from ApplicationEntity entity")
@NamedQuery(name = "userId",query = "select count(*) from  ApplicationEntity ent where ent.userId=:userBy")
@NamedQuery(name = "emailId",query = "select count(*) from  ApplicationEntity ent where ent.email=:emailBy")
@NamedQuery(name = "mobileId",query = "select count(*) from  ApplicationEntity ent where ent.mobile=:mobileBy")
@NamedQuery(name="findByUserIdAndPassword",query = "select entity from ApplicationEntity entity where entity.userId=:us and entity.password=:pas")
//@NamedQuery(name = "findByUserAndPassword",query = "select entity from ApplicationEntity where userId and password")
public class ApplicationEntity {
	@Id
	@Column(name = "signUpId")
	private int signUpId;
	@Column(name = "userId")
	private String userId;
	@Column(name = "emailId")
	private String email;
	@Column(name = "mobileNo")
	private Long mobile;
	@Column(name = "password")
	private String password;
	@Column(name = "createdBy")
	private String createdBy;
	@Column(name = "createdDate")
	private LocalDateTime createdDate = LocalDateTime.now();
	@Column(name = "updatedBy")
	private String updatedBy;
	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;
}
