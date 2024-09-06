package com.tidz.dream_shop.service.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tidz.dream_shop.model.Image;
import com.tidz.dream_shop.repository.ImageRepository;
import com.tidz.dream_shop.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

	private final ImageRepository imageRepository;
	private final IProductService productService;

	@Override
	public Image getImageById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteImageById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image saveImage(MultipartFile file, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		// TODO Auto-generated method stub

	}

}
