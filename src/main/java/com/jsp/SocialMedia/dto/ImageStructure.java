package com.jsp.SocialMedia.dto;

import org.springframework.http.MediaType;

import lombok.Data;

@Data
public class ImageStructure<T> {
 private int statuscode;
 private MediaType type;
 private T data;
	
}
