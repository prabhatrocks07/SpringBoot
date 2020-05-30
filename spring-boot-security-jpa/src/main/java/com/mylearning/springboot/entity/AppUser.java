package com.mylearning.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="APP_USER", uniqueConstraints= {@UniqueConstraint(name="APP_USER_UK", columnNames="USER_NAME")})
public class AppUser {

	@Id
	@GeneratedValue
	@Column(name="USER_ID", nullable=false)
	private Long userId;
	
	@Column(name="USER_NAME", length=36, nullable=false)
	private String userName;
	
	@Column(name="ENCRYPTED_PASSWORD", length=128, nullable=false)
	private String encryptedPassword;
	
	@Column(name="Enabled", length=1, nullable=false)
	private boolean enabled;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
