package com.tidz.dream_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.dream_shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
