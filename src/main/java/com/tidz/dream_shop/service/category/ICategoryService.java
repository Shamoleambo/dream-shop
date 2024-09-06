package com.tidz.dream_shop.service.category;

import java.util.List;

import com.tidz.dream_shop.model.Category;

public interface ICategoryService {

	Category getCategoryById(Long id);

	Category getCategoryByName(String name);

	List<Category> getAllCategories();

	Category addCategory(Category category);

	Category updateCategory(Category category);

	void deleteCategoryById(Long id);
}
