package com.jsp.SocialMedia.entity;

import java.sql.Time;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
@Table(name = "like Table")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int like_id;
	@CreationTimestamp
	private Time created_at;
	@ManyToOne
	private User user_id;
	@ManyToOne
	private Post post_id;

}
