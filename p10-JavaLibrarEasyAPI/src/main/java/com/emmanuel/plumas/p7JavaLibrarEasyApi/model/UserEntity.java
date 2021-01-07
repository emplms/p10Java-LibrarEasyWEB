package com.emmanuel.plumas.p7JavaLibrarEasyApi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="libraryUser")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long userId;
	private String userLastName;
	private String userFirstName;
	private String userPhoneNumber;
	private String userEmail;
	private String userPassword;
	
	public UserEntity(Long userId, String userLastName, String userFirstName, String userPhoneNumber, String userEmail,
			String userPassword) {
		super();
		this.userId = userId;
		this.userLastName = userLastName;
		this.userFirstName = userFirstName;
		this.userPhoneNumber = userPhoneNumber;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public UserEntity() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userLastName=" + userLastName + ", userFirstName=" + userFirstName
				+ ", userPhoneNumber=" + userPhoneNumber + ", userEmail=" + userEmail + ", userPassword=" + userPassword
				+ "]";
	}
}
