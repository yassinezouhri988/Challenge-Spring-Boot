package org.sid.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import springfox.documentation.annotations.ApiIgnore;

@ResponseStatus
@ApiIgnore
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}