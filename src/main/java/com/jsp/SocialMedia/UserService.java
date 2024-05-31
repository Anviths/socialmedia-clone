package com.jsp.SocialMedia;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SocialMedia.dto.ResponseStructure;
import com.jsp.SocialMedia.entity.User;

import jakarta.servlet.http.HttpSession;

@Service
public interface UserService {

	ResponseEntity<ResponseStructure<User>> saveUser(User user,MultipartFile profile_pic,MultipartFile cover_photo) throws IOException;

	ResponseEntity<ResponseStructure<User>> login(String userName, String password,HttpSession httpSession);
}
