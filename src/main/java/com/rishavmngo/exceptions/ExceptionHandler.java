package com.rishavmngo.exceptions;

import com.fasterxml.jackson.core.JsonParseException;

// import com.fasterxml.jackson.core.JsonParseException;
// import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {

		Response errorResponse = mapExceptionToResponse(exception);
		return errorResponse;
	}

	private Response mapExceptionToResponse(Exception exception) {

		// log.info(exception.getMessage(), exception);
		if (exception instanceof BadRequestException) {
			return Response.status(400).entity(exception.getMessage()).build();
		} else if (exception instanceof WebApplicationException) {
			Throwable cause = exception.getCause();
			if (cause instanceof JsonParseException)
				return Response.status(400).entity("Invalid JSON format").build();
			else

				return Response.status(400).entity("Bad request").build();

		} else if (exception instanceof UnauthorizedException) {

			return Response.status(401).entity(exception.getMessage()).build();
		} else {
			return Response.serverError().entity("Internal Server Error").build();
		}
	}

}
