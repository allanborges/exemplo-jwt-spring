package com.everis.cleaner.inventory.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.everis.cleaner.inventory.models.UserDetailsImpl;
import com.everis.cleaner.inventory.models.dto.UserRequestDto;
import com.everis.cleaner.inventory.utils.JwtTokenUtil;

@RestController
public class JwtAuthController {
		
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthController.class);
		
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtTokenUtil jwtUtils;

	@PostMapping("/auth")
	public ResponseEntity<?> authenticateUser(@RequestBody UserRequestDto userRequest, WebRequest webRequest) {
		webRequest.setAttribute("userRequest", userRequest, RequestAttributes.SCOPE_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getUsername(), 
						userRequest.getPassword()));
		
		String jwt = jwtUtils.generateJwtToken(authentication);		
		
		logger.info("token Generated for User {} - {}",userRequest.getUsername(), jwt);
		
		return ResponseEntity.ok(UserDetailsImpl.buildJwtUserRes(authentication, jwt));
	}

}
