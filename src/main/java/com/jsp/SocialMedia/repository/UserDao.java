package com.jsp.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.SocialMedia.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	User findByUserName(String username);
	User findByUserNameAndPassword(String userName,String Password);
	User findByEmailAndPassword(String email,String Password);
	User findByPhoneAndPassword(long phone,String Password);
	User findByEmail(String userName);
	User findByPhone(long parseLong);
}
