package com.jsp.SocialMedia.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SocialMedia.dto.ResponseStructure;
import com.jsp.SocialMedia.entity.Post;
import com.jsp.SocialMedia.entity.User;
@Service
public interface PostService {
	
    ResponseEntity<ResponseStructure<Post>> savePost(Post post,MultipartFile post_file);
    ResponseEntity<ResponseStructure<List<Post>>> findAllPostOfUser(User user);
 
	ResponseEntity<byte[]> getPostImage(String imageName) throws IOException;
   
}
