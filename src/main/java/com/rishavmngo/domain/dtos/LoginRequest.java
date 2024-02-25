package com.rishavmngo.domain.dtos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {

	@NotEmpty(message = "Email should not be empty!!")
	@Email(message = "Email is invalid!!")
	private String email;

	@NotEmpty(message = "Password should not be empty!!")
	private String password;

}
