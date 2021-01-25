package com.alvesjefs.magazine.dto;

import java.io.Serializable;

import com.alvesjefs.magazine.domain.Products;

public class ProductsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private Double price;
	private Integer productNumber;

	public ProductsDTO() {
	}

	public ProductsDTO(Long id, String name, String description, Double price, Integer productNumber) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.productNumber = productNumber;
	}

	public ProductsDTO(Products products) {
		id = products.getId();
		name = products.getName();
		description = products.getDescription();
		price = products.getPrice();
		productNumber = products.getProductNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

}
