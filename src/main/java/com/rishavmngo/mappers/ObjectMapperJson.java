package com.rishavmngo.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class ObjectMapperJson {

	private final ObjectMapper mapper;

	public ObjectMapperJson() {

		this.mapper = new ObjectMapper();
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public String json(Object object) {

		try {

			return this.mapper.writeValueAsString(object);

		} catch (Exception e) {
			throw new BadRequestException("Error converting to json");
			// return "Error converting to JSON";

		}

	}

}
