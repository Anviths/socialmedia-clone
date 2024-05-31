package com.jsp.SocialMedia.entity;

import org.springframework.stereotype.Component;

import com.jsp.SocialMedia.dto.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Freindship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int friendship_id;
	@ManyToOne
	private User user1_id;
	@ManyToOne
	private User user2_id;
	private Status status;
}
