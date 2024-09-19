package com.tidz.dream_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.dream_shop.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
