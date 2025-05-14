package com.codeid.eshopay.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeid.eshopay.model.dto.ProductDto;
import com.codeid.eshopay.model.enumeration.EnumStatus;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.FileStorageService;
import com.codeid.eshopay.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product/")
public class ProductController extends BaseMultipartController<ProductDto, Integer>{
    
    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @Override
    protected BaseCrudService<ProductDto, Integer> getService() {
        return productService;    
    }
    
    @Override
    public ResponseEntity<ProductDto> create(@Valid ProductDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<ProductDto> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<ProductDto> update(Integer id, @Valid ProductDto entity) {
        return super.update(id, entity);
    }

    @Override
    public ResponseEntity<?> createMultipart(ProductDto dto, MultipartFile file, String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload product photo");
        }

        try {
            String fileName = fileStorageService.storeFileWithRandomName(file);
            
            dto.setPhoto(fileName);
            var productDto= productService.save(dto);    

            ApiResponse<ProductDto> response = new ApiResponse<ProductDto>(EnumStatus.Succeed.toString(), "Product created", productDto);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }    
    }

    @Override
    public ResponseEntity<?> viewImage(String fileName) {
        try {
            Resource resource = fileStorageService.loadFile(fileName);
            
            // Cek jika file adalah image
            String contentType = determineContentType(fileName);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                           "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }    
    }

    @Override
    public ResponseEntity<?> updateMultipart(Integer id, ProductDto dto, MultipartFile file, String description) {
        throw new UnsupportedOperationException("Unimplemented method 'updateMultipart'");
    }
    
}
