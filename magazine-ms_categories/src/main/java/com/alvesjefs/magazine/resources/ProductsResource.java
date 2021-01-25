package com.alvesjefs.magazine.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alvesjefs.magazine.domain.Products;
import com.alvesjefs.magazine.dto.ProductsDTO;
import com.alvesjefs.magazine.services.ProductsService;

@RestController
@RequestMapping(value = "api/products")
public class ProductsResource {

	@Autowired
	private ProductsService productsService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Products> findById(@PathVariable Long id) {
		Products findId = productsService.findById(id);
		return ResponseEntity.ok().body(findId);
	}

	@GetMapping(value = "/searchProductNumber")
	public ResponseEntity<ProductsDTO> findByProductsNumber(@RequestParam Integer productNumber) {
		ProductsDTO findProductNumber = productsService.findByProductNumber(productNumber);
		return ResponseEntity.ok().body(findProductNumber);
	}

	@GetMapping(value = "/searchPage")
	public ResponseEntity<Page<ProductsDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "3") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<ProductsDTO> findPage = productsService.findPage(page, linesPerPage, direction, orderBy);
		return ResponseEntity.ok().body(findPage);
	}

	@PostMapping(value = "/insertProduct")
	public ResponseEntity<ProductsDTO> insertProduct(@RequestBody ProductsDTO productsDTO) {
		ProductsDTO insertProduct = productsService.insertProduct(productsDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productsDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(insertProduct);
	}

	@PutMapping(value = "/updateProduct/{id}")
	public ResponseEntity<ProductsDTO> updateProducts(@PathVariable Long id, @RequestBody ProductsDTO productsDTO) {
		ProductsDTO updateProduct = productsService.updateProducts(id, productsDTO);
		return ResponseEntity.ok().body(updateProduct);
	}

	@DeleteMapping(value = "/deletedProduct/{id}")
	public ResponseEntity<ProductsDTO> deleteProduct(@PathVariable Long id) {
		productsService.deleteProducts(id);
		return ResponseEntity.noContent().build();
	}

}
