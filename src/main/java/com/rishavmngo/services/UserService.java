package com.rishavmngo.services;

import com.rishavmngo.domain.dtos.UserDto;
import com.rishavmngo.domain.entities.UserEntity;
import com.rishavmngo.exceptions.UnauthorizedException;
import com.rishavmngo.interceptors.Logged;
import com.rishavmngo.mappers.UserMapper;
import com.rishavmngo.repositories.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;

	UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public UserDto register(UserEntity user) {

		if (userRepository.userExist(user.getEmail())) {
			throw new BadRequestException("Email already taken!");
		}

		return userMapper.mapTo(userRepository.create(user));

	}

	public UserDto login(String email, String password) {

		UserEntity user = userRepository.findByEmail(email);

		if (user == null || !password.equals(user.getPassword())) {
			throw new UnauthorizedException("Wrong credentials!");
		}

		return userMapper.mapTo(user);
	}

	public void update(Long id, UserEntity partialUser) {

		UserEntity user = userRepository.findById(id);

		if (user == null) {

			throw new BadRequestException("User does't exist with id: " + id);
		}

		userRepository.partialUpdate(user, partialUser);

	}

	public UserDto deleteById(Long id) {

		UserEntity user = userRepository.findById(id);

		if (user == null) {
			throw new BadRequestException("User does't exist with id: " + id);
		}

		userRepository.deleteById(id);
		return userMapper.mapTo(user);

	}

}
