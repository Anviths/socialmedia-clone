package com.jsp.SocialMedia.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.SocialMedia.dto.ResponseStructure;

@RestControllerAdvice
public class ExeptionHandler {

	@ExceptionHandler(UserSignUpException.class)
	public ResponseEntity<ResponseStructure<String>> userAlreadyExist(UserSignUpException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setStatus_code(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ResponseStructure<String>> postException(PostException e) {
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setStatus_code(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity< Map<String, String>> validationException(MethodArgumentNotValidException e) {
		Map<String, String> map=new HashMap<String, String>();
		List<ObjectError> allErrors = e.getAllErrors();
		for (ObjectError objectError : allErrors) {
			
			FieldError error=(FieldError) objectError;
			map.put(error.getField(), error.getDefaultMessage());
		}
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
	
}
