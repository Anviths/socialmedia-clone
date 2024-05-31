package com.jsp.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.SocialMedia.entity.Like;
@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

}
