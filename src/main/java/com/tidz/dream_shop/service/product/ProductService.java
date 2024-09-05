package com.tidz.dream_shop.service.product;

import java.util.List;

import com.tidz.dream_shop.exception.ProductNotFoundException;
import com.tidz.dream_shop.model.Product;
import com.tidz.dream_shop.repository.ProductRepository;

public class ProductService implements IProductService {

	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(Long id) {
		return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProduct(Product product, Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByCategoryId(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByBrand(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
