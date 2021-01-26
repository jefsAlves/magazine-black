package com.alvesjefs.magazine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvesjefs.magazine.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);
}
