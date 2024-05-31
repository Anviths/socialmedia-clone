package com.jsp.SocialMedia.exception;

public class UserSignUpException extends RuntimeException{

	private String message;
	public UserSignUpException(String message) {
        this.message=message;
	}
	@Override
	public String getMessage() {
		
		return message;
	}
}
