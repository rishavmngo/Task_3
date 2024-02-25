package com.rishavmngo.mappers;

import com.rishavmngo.domain.dtos.UserDto;
import com.rishavmngo.domain.entities.UserEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

	public UserDto mapTo(UserEntity userEntity) {

		return UserDto.builder().id(userEntity.getId()).username(userEntity.getUsername()).email(userEntity.getEmail())
				.firstname(userEntity.getFirstname()).lastname(userEntity.getLastname()).build();

	}

	public UserEntity mapFrom(UserDto userDto) {

		return UserEntity.builder().id(userDto.getId()).username(userDto.getUsername()).email(userDto.getEmail())
				.firstname(userDto.getFirstname()).lastname(userDto.getLastname()).password(null).build();

	}

}
