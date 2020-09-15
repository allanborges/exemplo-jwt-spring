package com.everis.cleaner.inventory.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.everis.cleaner.inventory.enums.RoleEnum;


//TODO realizar o mapeamento do usuario aqui ex: @entity....
public class User implements Serializable{
	
	private Long id;	
	private String userName;
	private String passWord;
	//TODO precisaremos mapear depois a entidade e fazer o relacionamento com os privilegios
	private List<String> roles = new ArrayList<>();	
	
	
	public User() {
		
	}
	
	// construtor hardcoded por enquanto
	public User(Long id, String userName, String passWord, List<String> roles) {		
		this.id = id;		
		this.userName = userName;
		this.passWord = passWord;
		this.roles = roles;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}	

}
