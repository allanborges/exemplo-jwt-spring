package com.everis.cleaner.inventory.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.everis.cleaner.inventory.domain.User;
import com.everis.cleaner.inventory.enums.RoleEnum;
import com.everis.cleaner.inventory.models.UserDetailsImpl;

//TODO precisamos ter informações de conexão para poder fazer acesso e validar,criar o registro de usuário
@Service
public class UserDetailServiceImpl implements UserDetailsService{
		
	//TODO realizar a injecao depois do userRepository 
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//TODO totalmente hardcode , depois precisameremos implementar o repository do usuario
		User user = new User(1L,username, encoder.encode("123"), Arrays.asList(RoleEnum.ROLE_TEST1.name(),RoleEnum.ROLE_TEST2.name()));
		return UserDetailsImpl.build(user);
	}

}
