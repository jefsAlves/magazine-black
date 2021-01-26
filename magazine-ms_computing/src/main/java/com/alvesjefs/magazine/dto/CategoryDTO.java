package com.alvesjefs.magazine.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.alvesjefs.magazine.domain.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer categoryNumber;

	private Set<ProductsDTO> products = new HashSet<>();

	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String name, Integer categoryNumber) {
		this.id = id;
		this.name = name;
		this.categoryNumber = categoryNumber;
	}

	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
		categoryNumber = category.getCategoryNumber();
		products = category.getProducts().stream().map(x -> new ProductsDTO(x)).collect(Collectors.toSet());
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

	public Integer getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(Integer categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public Set<ProductsDTO> getProducts() {
		return products;
	}

}
