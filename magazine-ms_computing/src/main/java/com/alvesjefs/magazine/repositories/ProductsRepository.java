package com.alvesjefs.magazine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvesjefs.magazine.domain.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

	Products findByProductNumber(Integer productNumber);

}
