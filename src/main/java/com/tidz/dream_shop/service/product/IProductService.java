package com.tidz.dream_shop.service.product;

import java.util.List;

import com.tidz.dream_shop.model.Product;
import com.tidz.dream_shop.request.AddProductRequest;
import com.tidz.dream_shop.request.UpdateProductRequest;

public interface IProductService {
	Product addProduct(AddProductRequest product);

	Product getProductById(Long id);

	void deleteProductById(Long id);

	Product updateProduct(UpdateProductRequest request, Long id);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByBrand(String name);

	List<Product> getProductsByCategoryAndBrand(String category, String brand);

	List<Product> getProductsByName(String name);

	List<Product> getProductsByBrandAndName(String brand, String name);

	Long countProductsByBrandAndName(String brand, String name);
}
