package com.jsp.SocialMedia.exception;

public class UserAlreadyPresentException extends RuntimeException {

	@Override
	public String getMessage() {
		
		return "user already exist";
	}
}
