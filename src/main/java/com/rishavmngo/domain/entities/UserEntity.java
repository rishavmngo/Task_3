package com.rishavmngo.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	@NotBlank(message = "Username should not be empty!")
	private String username;

	@Column(unique = true)
	@NotBlank(message = "Email should not be empty!")
	@Email(message = "Invalid email!")
	private String email;

	private String firstname;

	private String lastname;

	@NotBlank(message = "Password should not be empty!")
	private String password;
}
