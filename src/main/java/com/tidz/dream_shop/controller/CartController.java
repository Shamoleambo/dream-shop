package com.tidz.dream_shop.controller;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Cart;
import com.tidz.dream_shop.response.ApiResponse;
import com.tidz.dream_shop.service.cart.ICartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {

	private ICartService cartService;

	@GetMapping("/{cartId}/my-cart")
	public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
		try {
			Cart cart = cartService.getCart(cartId);
			return ResponseEntity.ok(new ApiResponse("Success", cart));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

}
