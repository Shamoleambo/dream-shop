package com.tidz.dream_shop.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tidz.dream_shop.exception.ProductNotFoundException;
import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Category;
import com.tidz.dream_shop.model.Product;
import com.tidz.dream_shop.repository.CategoryRepository;
import com.tidz.dream_shop.repository.ProductRepository;
import com.tidz.dream_shop.request.AddProductRequest;
import com.tidz.dream_shop.request.UpdateProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setDescription(request.getDescription());

		Category category = this.categoryRepository.findByName(request.getCategory().getName());
		existingProduct.setCategory(category);

		return existingProduct;
	}

	private Product createProduct(AddProductRequest request, Category category) {
		return new Product(request.getName(), request.getBrand(), request.getPrice(), request.getInventory(),
				request.getDescription(), category);
	}

	@Override
	public Product addProduct(AddProductRequest request) {
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
				.orElseGet(() -> {
					Category newCategory = new Category(request.getCategory().getName());
					return categoryRepository.save(newCategory);
				});

		request.setCategory(category);
		return productRepository.save(createProduct(request, category));
	}

	@Override
	public Product getProductById(Long id) {
		return this.productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public void deleteProductById(Long id) {
		this.productRepository.findById(id).ifPresentOrElse(this.productRepository::delete, () -> {
			throw new ProductNotFoundException("Product not found");
		});

	}

	@Override
	public Product updateProduct(UpdateProductRequest request, Long id) {
		return this.productRepository.findById(id)
				.map(existingProduct -> updateExistingProduct(existingProduct, request)).map(productRepository::save)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
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
