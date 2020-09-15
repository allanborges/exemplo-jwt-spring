package com.everis.cleaner.inventory.exceptions.handler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.everis.cleaner.inventory.models.ErrorMessage;
import com.everis.cleaner.inventory.models.dto.UserRequestDto;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(value= {AuthenticationException.class,BadCredentialsException.class})
	protected ResponseEntity<Object> userNotAuthorizedAccess(RuntimeException ex, WebRequest request) {
		UserRequestDto userRequest = (UserRequestDto) request.getAttribute("userRequest", RequestAttributes.SCOPE_REQUEST);
		ErrorMessage errorMessage = new ErrorMessage(messageSource.getMessage("msg_user_not_authorized",
																			  new Object[] {userRequest.getUsername()},
																			  Locale.ENGLISH),
																	          HttpStatus.UNAUTHORIZED.value());		
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}
}

