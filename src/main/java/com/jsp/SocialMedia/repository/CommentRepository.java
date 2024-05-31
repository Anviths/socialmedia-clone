package com.jsp.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.SocialMedia.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
