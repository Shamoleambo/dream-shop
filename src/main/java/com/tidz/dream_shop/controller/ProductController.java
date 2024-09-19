package com.tidz.dream_shop.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Product;
import com.tidz.dream_shop.request.AddProductRequest;
import com.tidz.dream_shop.request.UpdateProductRequest;
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

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest productRequest) {
		try {
			Product product = this.productService.addProduct(productRequest);
			return ResponseEntity.ok(new ApiResponse("Success", product));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@PutMapping("/product/{id}/update")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest productRequest,
			@PathVariable("id") Long id) {
		try {
			Product product = this.productService.updateProduct(productRequest, id);
			return ResponseEntity.ok(new ApiResponse("Updated", product));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@DeleteMapping("/product/{id}/delete")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("id") Long id) {
		try {
			this.productService.deleteProductById(id);
			return ResponseEntity.ok(new ApiResponse("Product Deleted", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/by/brand-and-name")
	public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brandName,
			@RequestParam String productName) {
		try {
			List<Product> products = this.productService.getProductsByBrandAndName(brandName, productName);

			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not Found", null));
			}

			return ResponseEntity.ok(new ApiResponse("Products Found", products));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/by/category-and-brand")
	public ResponseEntity<ApiResponse> getProductsByCategoryAndName(@RequestParam String categoryName,
			@RequestParam String brandName) {
		try {
			List<Product> products = this.productService.getProductsByCategoryAndBrand(categoryName, brandName);

			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not Found", null));
			}

			return ResponseEntity.ok(new ApiResponse("Products Found", products));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/by/name/{name}")
	public ResponseEntity<ApiResponse> getProductsByName(@PathVariable("name") String name) {
		try {
			List<Product> products = this.productService.getProductsByName(name);

			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not Found", null));
			}

			return ResponseEntity.ok(new ApiResponse("Products Found", products));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/product/by-brand")
	public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {
		try {
			List<Product> products = this.productService.getProductsByBrand(brand);

			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not Found", null));
			}

			return ResponseEntity.ok(new ApiResponse("Products Found", products));

		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/product/{category}/all/products")
	public ResponseEntity<ApiResponse> findProductsByCategory(@PathVariable String category) {
		try {
			List<Product> products = this.productService.getProductsByCategory(category);

			if (products.isEmpty()) {
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not Found", null));
			}

			return ResponseEntity.ok(new ApiResponse("Products Found", products));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/product/count/by-brand/and-name")
	public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand,
			@RequestParam String name) {
		try {
			Long productCount = this.productService.countProductsByBrandAndName(brand, name);
			return ResponseEntity.ok(new ApiResponse("Product Count", productCount));
		} catch (Exception e) {
			return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
		}
	}
}
