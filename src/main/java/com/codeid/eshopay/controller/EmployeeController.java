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

import com.codeid.eshopay.model.dto.EmployeeDto;
import com.codeid.eshopay.model.enumeration.EnumStatus;
import com.codeid.eshopay.model.response.ApiResponse;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.EmployeeService;
import com.codeid.eshopay.service.FileStorageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee/")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController extends BaseMultipartController<EmployeeDto, Integer>{

    private final EmployeeService employeeService;
    private final FileStorageService fileStorageService;

    @Override
    protected BaseCrudService<EmployeeDto, Integer> getService() {
        return employeeService;
    }

    @Override
    public ResponseEntity<EmployeeDto> create(@Valid EmployeeDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<EmployeeDto> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<EmployeeDto> update(Integer id, @Valid EmployeeDto entity) {
        return super.update(id, entity);
    }

    @Override
    public ResponseEntity<?> createMultipart(EmployeeDto dto, MultipartFile file, String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload employee photo");
        }

        try {
            String fileName = fileStorageService.storeFileWithRandomName(file);
            
            dto.setPhoto(fileName);
            var employeeDto= employeeService.save(dto);    

            ApiResponse<EmployeeDto> response = new ApiResponse<EmployeeDto>(EnumStatus.Succeed.toString(), "Employee created", employeeDto);

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
    public ResponseEntity<?> updateMultipart(Integer id, EmployeeDto dto, MultipartFile file, String description) {
        
        throw new UnsupportedOperationException("Unimplemented method 'updateMultipart'");
    }
    
}
