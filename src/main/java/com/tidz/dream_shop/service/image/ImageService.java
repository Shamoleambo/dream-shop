package com.tidz.dream_shop.service.image;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tidz.dream_shop.exception.ResourceNotFoundException;
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
		return this.imageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No image found with id: " + id));
	}

	@Override
	public void deleteImageById(Long id) {
		this.imageRepository.findById(id).ifPresentOrElse(this.imageRepository::delete, () -> {
			throw new ResourceNotFoundException("No image found with id: " + id);
		});

	}

	@Override
	public Image saveImage(MultipartFile file, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		Image image = this.getImageById(imageId);
		try {
			image.setFileName(file.getOriginalFilename());
			image.setImage(new SerialBlob(file.getBytes()));
			this.imageRepository.save(image);
		} catch (IOException | SQLException e) {
			throw new RuntimeException(e.getMessage()); 
		}

	}

}
