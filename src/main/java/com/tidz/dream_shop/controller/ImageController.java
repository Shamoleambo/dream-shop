package com.tidz.dream_shop.controller;

import static org.springframework.http.HttpStatus.*;

import java.sql.SQLException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tidz.dream_shop.dto.ImageDto;
import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Image;
import com.tidz.dream_shop.response.ApiResponse;
import com.tidz.dream_shop.service.image.IImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {

	private final IImageService imageService;

	@PostMapping("/upload")
	public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files,
			@RequestParam Long productId) {
		try {
			List<ImageDto> imagesDtos = this.imageService.saveImages(files, productId);
			return ResponseEntity.ok(new ApiResponse("Upload success!", imagesDtos));
		} catch (Exception e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed", e.getMessage()));
		}
	}

	@GetMapping("/image/download/{imageId}")
	public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
		Image image = this.imageService.getImageById(imageId);
		ByteArrayResource resource = new ByteArrayResource(
				image.getImage().getBytes(1, (int) image.getImage().length()));

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
				.body(resource);
	}

	@PutMapping("/image/{imageId}/update")
	public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file) {
		try {
			Image image = this.imageService.getImageById(imageId);
			if (image != null) {
				this.imageService.updateImage(file, imageId);
				return ResponseEntity.ok(new ApiResponse("Update success!", null));
			}

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}

		return ResponseEntity.status(INTERNAL_SERVER_ERROR)
				.body(new ApiResponse("Update Failed", INTERNAL_SERVER_ERROR));
	}

}
