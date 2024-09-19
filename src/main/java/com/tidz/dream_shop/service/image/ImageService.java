package com.tidz.dream_shop.service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tidz.dream_shop.dto.ImageDto;
import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Image;
import com.tidz.dream_shop.model.Product;
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
	public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
		Product product = this.productService.getProductById(productId);
		List<ImageDto> savedImageDto = new ArrayList<>();
		for (MultipartFile file : files) {
			try {
				Image image = new Image();
				image.setFileName(file.getName());
				image.setFileType(file.getContentType());
				image.setImage(new SerialBlob(file.getBytes()));
				image.setProduct(product);

				String buildDownloadUrl = "/api/v1/images/image/download/";
				String downloadUrl = buildDownloadUrl + image.getId();
				image.setDownloadUrl(downloadUrl);

				Image savedImage = this.imageRepository.save(image);
				savedImage.setDownloadUrl(buildDownloadUrl + image.getId());
				this.imageRepository.save(savedImage);

				ImageDto imageDto = new ImageDto();
				imageDto.setId(savedImage.getId());
				imageDto.setFileName(savedImage.getFileName());
				imageDto.setDownloadUrl(savedImage.getDownloadUrl());

				savedImageDto.add(imageDto);
			} catch (IOException | SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return savedImageDto;
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
