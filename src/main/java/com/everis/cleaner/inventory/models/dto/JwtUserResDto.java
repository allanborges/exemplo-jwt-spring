package com.everis.cleaner.inventory.models.dto;

import java.io.Serializable;
import java.util.List;

import com.everis.cleaner.inventory.enums.RoleEnum;

public class JwtUserResDto implements Serializable {
	
	private static final long serialVersionUID = 1L;	

	private final String token;
	private final String userName;
	private final List<RoleEnum> roles;

	public JwtUserResDto(final String userName,
			               final String token, 
			               final List<RoleEnum> roles) {
		this.token = token;
		this.roles = roles;
		this.userName = userName;
	}

	public String getToken() {
		return this.token;
	}

	public String getUserName() {
		return userName;
	}

	public List<RoleEnum> getRoles() {
		return roles;
	}

}
