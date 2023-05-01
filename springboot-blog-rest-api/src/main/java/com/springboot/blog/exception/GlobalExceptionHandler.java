package com.springboot.blog.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.springboot.blog.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	// handle specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResouceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {
				
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetails> handleBlogApidException(BlogApiException exception,
			WebRequest webRequest) {
				
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	// global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handlerGlobaldException(Exception exception,
			WebRequest webRequest) {
				
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
