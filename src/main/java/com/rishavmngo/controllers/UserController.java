package com.rishavmngo.controllers;

import com.rishavmngo.domain.dtos.LoginRequest;
import com.rishavmngo.domain.dtos.UserDto;
import com.rishavmngo.domain.entities.UserEntity;
import com.rishavmngo.interceptors.Logged;
import com.rishavmngo.services.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
@Produces("application/json")
@Consumes("application/json")
@Logged
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@POST
	@Path("/register")
	@Transactional
	public UserDto register(@Valid UserEntity user) {

		return userService.register(user);
	}

	@POST
	@Path("/login")
	public UserDto login(@Valid LoginRequest loginRequest) {

		return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
	}

	@PUT
	@Path("/update/{id}")
	@Transactional
	public Response update(@PathParam("id") Long id, UserEntity user) {

		userService.update(id, user);

		return Response.status(201).entity("User updation successful").build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Transactional
	public UserDto delete(@PathParam("id") Long id) {

		return userService.deleteById(id);

	}

}
