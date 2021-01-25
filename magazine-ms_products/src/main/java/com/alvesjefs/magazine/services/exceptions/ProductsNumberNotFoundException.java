package com.alvesjefs.magazine.services.exceptions;

import java.io.Serializable;

public class ProductsNumberNotFoundException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProductsNumberNotFoundException() {
	}

	public ProductsNumberNotFoundException(String msg) {
		super(msg);
	}

}
