package com.rishavmngo.exceptions;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {

		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
		violations.forEach(violation -> errorMessage.append(violation.getMessage()).append("; "));
		log.info(exception.getMessage(), exception);
		return Response.status(400).entity(errorMessage).build();
	}

}
