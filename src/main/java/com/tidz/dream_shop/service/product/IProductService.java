package com.tidz.dream_shop.service.product;

import java.util.List;

import com.tidz.dream_shop.model.Product;

public interface IProductService {
	Product addProduct(Product product);

	Product getProductById(Long id);

	void deleteProductById(Long id);

	void updateProduct(Product product, Long id);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByBrand(String name);

	List<Product> getProductsByCategoryAndBrand(String category, String brand);

	List<Product> getProductsByName(String name);

	List<Product> getProductsByBrandAndName(String brand, String name);

	Long countProductsByBrandAndName(String brand, String name);
}
