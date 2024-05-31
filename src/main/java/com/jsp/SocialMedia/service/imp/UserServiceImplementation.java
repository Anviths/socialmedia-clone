package com.jsp.SocialMedia.service.imp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SocialMedia.UserService;
import com.jsp.SocialMedia.dto.ResponseStructure;
import com.jsp.SocialMedia.entity.User;
import com.jsp.SocialMedia.exception.UserSignUpException;
import com.jsp.SocialMedia.repository.UserDao;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImplementation implements UserService {
 
	@Value("${image.upload.directory}")
    private String uploadDirectory;
	
	@Autowired
	UserDao user_dao;

	@Override
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user, MultipartFile profile_pic,
			MultipartFile cover_photo) throws IOException {

		if (user_dao.findByUserName(user.getUserName()) == null) {
			
			String profile_picPath= saveImage(profile_pic,user.getUserName());
			String cover_photoPath= saveCover(cover_photo,user.getUserName());
			System.out.println(profile_picPath);
			user.setProfile_picture(profile_picPath);
			user.setCover_photo(cover_photoPath);
			
			user=user_dao.save(user);
			ResponseStructure<User> structure=new ResponseStructure<User>();
			structure.setStatus_code(HttpStatus.CREATED.value());
			structure.setMessage("User saved sucessfully");
			structure.setData(user);
		return	new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
		} else {
			throw new UserSignUpException("user already present");
		}

	}

	private String saveCover(MultipartFile image, String user_name) throws IOException {
		
        String fileName =  user_name+"cover.jpeg";

        
        Path uploadPath = Paths.get(uploadDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Files.copy(image.getInputStream(), uploadPath.resolve(fileName));
        } catch (IOException e) {
            throw new IOException("Failed to save image: " + e.getMessage());
        }

        return uploadDirectory + "/" + fileName;
	}

	
	private String saveImage(MultipartFile image,String user_name) throws IOException {

		
        String fileName =  user_name+"profile.jpeg";

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
	public ResponseEntity<ResponseStructure<User>> login(String userName,String password,HttpSession httpSession) {
		
		 User login=null;
		try {
		if(user_dao.findByUserName(userName)!=null) {
			login=user_dao.findByUserNameAndPassword(userName, password);
			if(login==null) {
				throw new UserSignUpException("invalid password");
			}
		}
		
			else if(user_dao.findByEmail(userName)!=null) {
				login=user_dao.findByEmailAndPassword(userName, password);
				if(login==null) {
					throw new UserSignUpException("invalid password");
				}
				
			}
			else if(user_dao.findByPhone(Long.parseLong(userName) )!=null) {
				 login=user_dao.findByPhoneAndPassword(Long.parseLong(userName), password);
				if(login==null) {
					throw new UserSignUpException("invalid password");
				}
			}
			else {
				throw new UserSignUpException("invalid user name");
			}
		}
		catch (NumberFormatException e) {
			throw new UserSignUpException("invalid user name");
		}
		
		ResponseStructure<User> structure=new ResponseStructure<User>();
		structure.setStatus_code(HttpStatus.OK.value());
		structure.setMessage("loginSucessfully");
		structure.setData(login);
		httpSession.setAttribute("logedUser", login);
		return	new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
	}
	

