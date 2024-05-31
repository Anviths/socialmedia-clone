package com.jsp.SocialMedia.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.SocialMedia.dto.ResponseStructure;
import com.jsp.SocialMedia.entity.Post;
import com.jsp.SocialMedia.entity.User;
import com.jsp.SocialMedia.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/facebook")
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	ObjectMapper mapper;

	@PostMapping("/post")
	public ResponseEntity<ResponseStructure<Post>> savePoster(@RequestParam String postdata,
			@RequestParam("post") MultipartFile file, HttpSession httpSession) {

		try {
			User user = (User) httpSession.getAttribute("logedUser");
			Post post = mapper.readValue(postdata, Post.class);
			post.setUser(user);
			return postService.savePost(post, file);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseStructure<Post>>(HttpStatus.BAD_REQUEST);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseStructure<Post>>(HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("/post")
	public ResponseEntity<ResponseStructure<List<Post>>> findAllPoster(HttpSession session) {
		return postService.findAllPostOfUser((User) session.getAttribute("logedUser")) ;
	}

	@GetMapping("/image/{imageName}")
	public ResponseEntity<byte[]> findimage(@PathVariable String imageName) throws IOException {
		 return postService.getPostImage(imageName);
	}
}
