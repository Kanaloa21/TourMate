package com.ssafy.tourmate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<String> authException(UnAuthorizedException e) {
		logger.error(e.getMessage());
		HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        
		return new ResponseEntity<>(e.getMessage(), resHeaders, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exception(Exception e) {
		logger.error(e.getMessage());
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
	        
		return new ResponseEntity<>(e.getMessage(), resHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
