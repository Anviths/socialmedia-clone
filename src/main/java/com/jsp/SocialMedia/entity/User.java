package com.jsp.SocialMedia.entity;

import java.sql.Time;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
@Validated
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	@NotNull(message = "user name is balnk")
	private String userName;
	private String password;
	@Email(message = "not valid email type")
	private String email;
	@Min(6000000000l)
	@Max(9999999999l)
	private long phone;
	@NotNull
	private String fullname;
	@NotNull
	private String profile_picture;
	@NotNull
	private String cover_photo;

	private String bio;
	private LocalDate date_of_birth;
	@CreationTimestamp
	private Time created_at;
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", fullname=" + fullname + ", profile_picture=" + profile_picture
				+ ", cover_photo=" + cover_photo + ", bio=" + bio + ", date_of_birth=" + date_of_birth + ", created_at="
				+ created_at + "]";
	}

	
}
