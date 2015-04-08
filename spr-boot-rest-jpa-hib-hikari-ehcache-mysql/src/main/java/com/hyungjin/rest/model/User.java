package com.hyungjin.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "USER")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="user")
public class User {
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, length = 10)
	private Long id;*/
	
	@Id
	@Column(name = "USERID", unique = true, nullable = false, length = 100)
	private String userId;
	
	@Column(name = "USERNAME", length = 50)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;
	
	@Column(name = "EMAIL", length = 50)
	private String email;
	
	
	public User(String userId, String userName, String password, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public User() {
		
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + "]";
	}

	
/*	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", password=" + password + ", email=" + email
				+ "]";
	}*/

	
}