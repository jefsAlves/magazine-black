package com.alvesjefs.magazine.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvesjefs.magazine.domain.Category;
import com.alvesjefs.magazine.domain.Products;
import com.alvesjefs.magazine.dto.CategoryDTO;
import com.alvesjefs.magazine.dto.ProductsDTO;
import com.alvesjefs.magazine.repositories.CategoryRepository;
import com.alvesjefs.magazine.repositories.ProductsRepository;
import com.alvesjefs.magazine.services.exceptions.IdNotFoundException;
import com.alvesjefs.magazine.services.exceptions.NameNotFoundExceptions;
import com.alvesjefs.magazine.services.exceptions.ViolateIntegrityException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductsRepository productsRepository;

	@Transactional(readOnly = true)
	public Category findById(Long id) {
		Optional<Category> findId = categoryRepository.findById(id);
		return findId.orElseThrow(() -> new IdNotFoundException("Id not found!"));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findByName(String name) {
		Category findName = categoryRepository.findByName(name);
		if (findName == null) {
			throw new NameNotFoundExceptions("Name not found!");
		}

		return new CategoryDTO(findName);
	}

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Category> findPage = categoryRepository.findAll(pageRequest);
		return findPage.map(x -> new CategoryDTO(x));
	}

	@Transactional
	public CategoryDTO insertCategory(CategoryDTO categoryDTO) {
		Category category = new Category(null, categoryDTO.getName(), categoryDTO.getCategoryNumber());
		for (ProductsDTO prod : categoryDTO.getProducts()) {
			Products products = productsRepository.getOne(prod.getId());
			category.getProducts().add(products);
		}

		category = categoryRepository.save(category);
		return new CategoryDTO(category);
	}

	@Transactional
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
		Category updateCategory = categoryRepository.getOne(id);
		updateCategory.setName(categoryDTO.getName());
		return new CategoryDTO(updateCategory);
	}

	@Transactional
	public void deleteCategoty(Long id) {
		try {
			categoryRepository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new IdNotFoundException("Id not found!");
		}
		catch (DataIntegrityViolationException e) {
			throw new ViolateIntegrityException("Category cannot be deleted");
		}
	}
}