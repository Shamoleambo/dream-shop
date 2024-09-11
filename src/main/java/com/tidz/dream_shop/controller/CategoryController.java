package com.tidz.dream_shop.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tidz.dream_shop.exception.AlreadyExistsException;
import com.tidz.dream_shop.model.Category;
import com.tidz.dream_shop.response.ApiResponse;
import com.tidz.dream_shop.service.category.ICategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

	private final ICategoryService categoryService;

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAll() {
		try {
			List<Category> categories = this.categoryService.getAllCategories();
			return ResponseEntity.ok(new ApiResponse("Found!", categories));

		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:", INTERNAL_SERVER_ERROR));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name) {
		try {
			Category category = this.categoryService.addCategory(name);
			return ResponseEntity.ok(new ApiResponse("Success", category));
		} catch (AlreadyExistsException e) {
			return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
		}
	}
}
