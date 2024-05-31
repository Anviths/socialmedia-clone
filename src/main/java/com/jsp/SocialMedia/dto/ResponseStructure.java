package com.jsp.SocialMedia.dto;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private int status_code;
	private String message;
	private T data;
}
