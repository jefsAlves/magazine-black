package com.alvesjefs.magazine.services.exceptions;

import java.io.Serializable;

public class ViolateIntegrityException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public ViolateIntegrityException() {
	}

	public ViolateIntegrityException(String msg) {
		super(msg);
	}

}
