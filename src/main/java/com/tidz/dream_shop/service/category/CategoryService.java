package com.tidz.dream_shop.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Category;
import com.tidz.dream_shop.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(Long id) {
		return this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	}

	@Override
	public Category getCategoryByName(String name) {
		return this.categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		return this.categoryRepository.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		return null;
	}

	@Override
	public Category updateCategory(Category category, Long id) {
		return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
			oldCategory.setName(category.getName());
			return this.categoryRepository.save(oldCategory);
		}).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	}

	@Override
	public void deleteCategoryById(Long id) {
		this.categoryRepository.findById(id).ifPresentOrElse(this.categoryRepository::delete, () -> {
			throw new ResourceNotFoundException("Category not found");
		});
	}

}
