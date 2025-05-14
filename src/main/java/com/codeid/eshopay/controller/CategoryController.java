package com.codeid.eshopay.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeid.eshopay.model.dto.CategoryDto;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/category/")
public class CategoryController extends BaseCrudController<CategoryDto, Integer>{

    private final CategoryService categoryService;

    @Override
    protected BaseCrudService<CategoryDto, Integer> getService() {
        return categoryService;    
    }

    @Override
    public ResponseEntity<CategoryDto> create(@Valid CategoryDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<CategoryDto> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<CategoryDto> update(Integer id, @Valid CategoryDto entity) {
        return super.update(id, entity);
    }
    
    
    
}
