package com.rishavmngo.repositories;

import com.rishavmngo.domain.entities.UserEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {

	public UserEntity create(UserEntity user) {
		persistAndFlush(user);
		return user;
	}

	public UserEntity findByEmail(String email) {
		return find("email", email).firstResult();
	}

	public Boolean userExist(String email) {
		return find("email", email).count() > 0;

	}

	public void partialUpdate(UserEntity user, UserEntity partialUser) {

		if (partialUser.getFirstname() != null) {
			user.setFirstname(partialUser.getFirstname());
		}

		if (partialUser.getLastname() != null) {
			user.setLastname(partialUser.getLastname());
		}

		if (partialUser.getPassword() != null) {
			user.setPassword(partialUser.getPassword());
		}

		persist(user);

	}
}
