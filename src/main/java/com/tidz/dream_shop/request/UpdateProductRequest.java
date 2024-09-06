package com.tidz.dream_shop.request;

import java.math.BigDecimal;

import com.tidz.dream_shop.model.Category;

import lombok.Data;

@Data
public class UpdateProductRequest {

	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private Integer inventory;
	private String description;
	private Category category;

}
