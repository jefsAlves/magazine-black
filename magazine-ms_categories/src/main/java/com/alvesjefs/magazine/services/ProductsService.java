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

import com.alvesjefs.magazine.domain.Products;
import com.alvesjefs.magazine.dto.ProductsDTO;
import com.alvesjefs.magazine.repositories.ProductsRepository;
import com.alvesjefs.magazine.services.exceptions.IdNotFoundException;
import com.alvesjefs.magazine.services.exceptions.IntegrityViolationException;
import com.alvesjefs.magazine.services.exceptions.ProductsNumberNotFoundException;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	@Transactional(readOnly = true)
	public Products findById(Long id) {
		Optional<Products> findId = productsRepository.findById(id);
		return findId.orElseThrow(() -> new IdNotFoundException("Id not found!"));
	}

	@Transactional(readOnly = true)
	public ProductsDTO findByProductNumber(Integer productNumber) {
		Products findNumber = productsRepository.findByProductNumber(productNumber);
		if (findNumber == null) {
			throw new ProductsNumberNotFoundException("Product number not found!");
		}
		return new ProductsDTO(findNumber);
	}

	@Transactional(readOnly = true)
	public Page<ProductsDTO> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Products> findPage = productsRepository.findAll(pageRequest);
		return findPage.map(x -> new ProductsDTO(x));
	}

	@Transactional
	public ProductsDTO insertProduct(ProductsDTO productsDTO) {
		Products products = new Products(null, productsDTO.getName(), productsDTO.getDescription(), productsDTO.getPrice(), productsDTO.getProductNumber());
		products = productsRepository.save(products);
		return new ProductsDTO(products);
	}

	@Transactional
	public ProductsDTO updateProducts(Long id, ProductsDTO productsDTO) {
		Products products = productsRepository.getOne(id);
		products.setPrice(productsDTO.getPrice());
		products = productsRepository.save(products);
		return new ProductsDTO(products);
	}

	@Transactional
	public void deleteProducts(Long id) {
		try {
			productsRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new IdNotFoundException("Product not found!");
		}
		catch(DataIntegrityViolationException e) {
			throw new IntegrityViolationException("Product cannot be deleted!");
		}
	}

}
