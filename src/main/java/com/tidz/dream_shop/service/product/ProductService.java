package com.tidz.dream_shop.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tidz.dream_shop.exception.ProductNotFoundException;
import com.tidz.dream_shop.model.Product;
import com.tidz.dream_shop.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

	private final ProductRepository productRepository;

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
		this.productRepository.findById(id).ifPresentOrElse(this.productRepository::delete, () -> {
			throw new ProductNotFoundException("Product not found");
		});

	}

	@Override
	public void updateProduct(Product product, Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return this.productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(String name) {
		return this.productRepository.findByBrand(name);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
		return this.productRepository.findByCategoryNameAndBrand(category, brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		return this.productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		return this.productRepository.findByBrandAndName(brand, name);
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		return this.productRepository.countByBrandAndName(brand, name);
	}

}
