package com.tidz.dream_shop.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tidz.dream_shop.exception.AlreadyExistsException;
import com.tidz.dream_shop.exception.ResourceNotFoundException;
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

	@GetMapping("/category/{id}/category")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable("id") Long id) {
		try {
			Category category = this.categoryService.getCategoryById(id);
			return ResponseEntity.ok(new ApiResponse("Found", category));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/category/{name}/category")
	public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable("name") String name) {
		try {
			Category category = this.categoryService.getCategoryByName(name);
			return ResponseEntity.ok(new ApiResponse("Found", category));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@DeleteMapping("/category/{id}/delete")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("id") Long id) {
		try {
			this.categoryService.deleteCategoryById(id);
			return ResponseEntity.ok(new ApiResponse("Success", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@PutMapping("/category/{id}/update")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
		try {
			Category updatedCategory = this.categoryService.updateCategory(category, id);
			return ResponseEntity.ok(new ApiResponse("Updated", updatedCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}
}
