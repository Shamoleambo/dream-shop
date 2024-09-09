package com.tidz.dream_shop.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tidz.dream_shop.dto.ImageDto;
import com.tidz.dream_shop.model.Image;

public interface IImageService {

	Image getImageById(Long id);

	void deleteImageById(Long id);

	List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

	void updateImage(MultipartFile file, Long imageId);
}
