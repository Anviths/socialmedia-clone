package com.jsp.SocialMedia.service.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SocialMedia.dto.ResponseStructure;
import com.jsp.SocialMedia.entity.Post;
import com.jsp.SocialMedia.entity.User;
import com.jsp.SocialMedia.exception.PostException;
import com.jsp.SocialMedia.repository.PostRepository;
import com.jsp.SocialMedia.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Value("${post.upload.directory}")
	private String uploadDirectory;

	@Override
	public ResponseEntity<ResponseStructure<Post>> savePost(Post post, MultipartFile post_file) {
		if (!post_file.isEmpty()) {

			Post savedPost = postRepository.save(post);
			try {
				String name=saveImage(post_file, savedPost.getPost_id() + "" + savedPost.getUser().getUser_id());
				savedPost.setImage(name);
				postRepository.save(savedPost);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			postRepository.save(post);

			ResponseStructure<Post> responseStructure = new ResponseStructure<Post>();
			responseStructure.setStatus_code(HttpStatus.CREATED.value());
			responseStructure.setMessage("post sucessfully uploaded");
			responseStructure.setData(savedPost);
			return new ResponseEntity<ResponseStructure<Post>>(responseStructure, HttpStatus.CREATED);
		}
		throw new PostException("post not found");
	}

	private String saveImage(MultipartFile image, String user_name) throws IOException {

		String fileName = user_name + "post.jpeg";

		// Resolve the path where the image will be saved
		Path uploadPath = Paths.get(uploadDirectory);

		// Create the upload directory if it doesn't exist
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		// Save the image to the filesystem
		try {
			Files.copy(image.getInputStream(), uploadPath.resolve(fileName));
		} catch (IOException e) {
			throw new IOException("Failed to save image: " + e.getMessage());
		}

		// Return the path where the image is saved
		return uploadDirectory + "/" + fileName;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Post>>> findAllPostOfUser(User user) {

		List<Post> findByUser = postRepository.findByUser(user);

		ResponseStructure<List<Post>> responseStructure = new ResponseStructure<List<Post>>();
		responseStructure.setStatus_code(HttpStatus.OK.value());
		responseStructure.setMessage("post found");
		responseStructure.setData(findByUser);
		return new ResponseEntity<ResponseStructure<List<Post>>>(responseStructure, HttpStatus.OK);
	
		
	}
	@Override
    public ResponseEntity<byte[]> getPostImage(String imageName) throws IOException {
		
		Path imagePath = Paths.get(uploadDirectory, imageName);
        byte[] imageBytes = Files.readAllBytes(imagePath);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        
	
	}
}
