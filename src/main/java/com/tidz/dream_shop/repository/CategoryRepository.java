package com.tidz.dream_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.dream_shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

}
