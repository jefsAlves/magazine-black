package com.alvesjefs.magazine.services.exceptions;

import java.io.Serializable;

public class NameNotFoundExceptions extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public NameNotFoundExceptions() {
	}

	public NameNotFoundExceptions(String msg) {
		super(msg);
	}

}
