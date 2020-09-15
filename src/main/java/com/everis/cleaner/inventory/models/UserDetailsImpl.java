package com.everis.cleaner.inventory.models;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.everis.cleaner.inventory.domain.User;
import com.everis.cleaner.inventory.enums.RoleEnum;
import com.everis.cleaner.inventory.models.dto.JwtUserResDto;
import com.everis.cleaner.inventory.utils.JwtTokenUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;
	
	private String email;
	
	@Autowired
	JwtTokenUtil jwtUtils;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String email ,String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;		
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		//TODO depois de ter feito o mapeamento com usuarioXRoles usaremos a entidade ao invés do ENUM hardcoded
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUserName(), 
				"teste@teste.com",
				user.getPassWord(), 
				authorities);
	}
	
	public static JwtUserResDto buildJwtUserRes(final Authentication authentication,											
												final String tokenGenerated) {		
		SecurityContextHolder.getContext().setAuthentication(authentication);		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<RoleEnum> roles = userDetails.getAuthorities().stream()
							  .map(item -> RoleEnum.valueOf(item.getAuthority()))
							  .collect(Collectors.toList());			
		JwtUserResDto userRes = new JwtUserResDto(userDetails.getUsername(), tokenGenerated, roles);	
		
		return userRes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
