package com.tidz.dream_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.dream_shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryName(String category);

	List<Product> findByBrand(String name);

	List<Product> findByCategoryNameAndBrand(String category, String brand);

	List<Product> findByName(String name);

	List<Product> findByBrandAndName(String brand, String name);

	Long countByBrandAndName(String brand, String name);
}
