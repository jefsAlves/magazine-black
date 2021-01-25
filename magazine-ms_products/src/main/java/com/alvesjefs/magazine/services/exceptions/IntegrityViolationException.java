package com.alvesjefs.magazine.services.exceptions;

import java.io.Serializable;

public class IntegrityViolationException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public IntegrityViolationException() {
	}

	public IntegrityViolationException(String msg) {
		super(msg);
	}
}
