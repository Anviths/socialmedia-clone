package com.jsp.SocialMedia.exception;

public class PostException extends RuntimeException {

	

	private String messsage;

	public PostException(String messsage) {
		super();
		this.messsage = messsage;
	}
	
	@Override
	public String getMessage() {
		
		return messsage;
	}
	
}
