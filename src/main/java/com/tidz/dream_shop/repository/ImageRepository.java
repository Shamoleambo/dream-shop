package com.tidz.dream_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.dream_shop.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
