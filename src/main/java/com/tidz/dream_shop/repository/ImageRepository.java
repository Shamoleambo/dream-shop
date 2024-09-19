package com.tidz.dream_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.dream_shop.model.Image;
import com.tidz.dream_shop.model.Product;

public interface ImageRepository extends JpaRepository<Image, Long> {

	List<Image> findByProductId(Long id);
}
