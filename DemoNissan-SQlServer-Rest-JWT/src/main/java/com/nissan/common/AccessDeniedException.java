package com.nissan.common;

public class AccessDeniedException extends RuntimeException { //User-Defined

	public AccessDeniedException(String message) {
		super(message);
	}

}
