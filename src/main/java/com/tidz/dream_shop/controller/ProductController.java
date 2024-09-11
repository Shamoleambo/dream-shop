package com.tidz.dream_shop.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Product;
import com.tidz.dream_shop.response.ApiResponse;
import com.tidz.dream_shop.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

	private final IProductService productService;

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllProducts() {
		List<Product> products = this.productService.getAllProducts();
		return ResponseEntity.ok(new ApiResponse("Success", products));
	}

	@GetMapping("/product/{id}/product")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
		try {
			Product product = this.productService.getProductById(id);
			return ResponseEntity.ok(new ApiResponse("Success", product));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

}
