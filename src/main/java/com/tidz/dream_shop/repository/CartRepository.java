package com.tidz.dream_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.dream_shop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
