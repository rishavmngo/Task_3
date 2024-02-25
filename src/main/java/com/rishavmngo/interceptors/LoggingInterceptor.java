
package com.rishavmngo.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Logged
@Interceptor
public class LoggingInterceptor {
	private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

	private final ObjectMapper objectMapper;

	public LoggingInterceptor() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@AroundInvoke
	public Object logMethod(InvocationContext context) throws Exception {
		Class<?> targetClass = context.getTarget().getClass();
		log.info("Entering  {}.{} ", targetClass.getSimpleName(), context.getMethod().getName());

		logMethodParameters(context.getParameters());

		try {
			Object result = context.proceed();
			// log.info("Result: ");
			log.info("Result:\n{}", toJsonString(result));
			return result;
		} catch (Exception e) {
			// log.info("Result: ");
			log.error("Result:\nError in {}.{} '{}'", targetClass.getSimpleName(), context.getMethod().getName(),
					e.getMessage(), e);
			throw e;
		} finally {
			// log.info("Exiting method {} in class : {}", context.getMethod().getName(),
			// targetClass.getSimpleName());
			log.info("Exiting  {}.{} ", targetClass.getSimpleName(), context.getMethod().getName());
		}
	}

	private void logMethodParameters(Object[] parameters) throws Exception {
		if (parameters != null && parameters.length > 0) {
			log.info("Method parameters as JSON:");

			for (Object parameter : parameters) {
				log.info(toJsonString(parameter));
			}
		} else {
			log.info("Method has no parameters.");
		}
	}

	private String toJsonString(Object object) throws Exception {
		return objectMapper.writeValueAsString(object);
	}
}
