package com.codeid.eshopay.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeid.eshopay.model.dto.CategoryDto;
import com.codeid.eshopay.model.entity.Category;
import com.codeid.eshopay.repository.CategoryRepository;
import com.codeid.eshopay.service.CategoryService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public static CategoryDto mapToDto(Category category){
        return new CategoryDto(
            category.getCategoryId(),
            category.getCategoryName(),
            category.getDescription(),
            category.getPicture()
            );
    }

    public static Category mapToEntity(CategoryDto categoryDto){
        return new Category(
            categoryDto.getCategoryId(),
            categoryDto.getCategoryName(),
            categoryDto.getDescription(),
            categoryDto.getPicture());
    }

    @Override
    public List<CategoryDto> findAll() {
        log.debug("Request get all data from categories"); 

        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        log.debug("Request to get category : {}", id);
        
        return this.categoryRepository.findById(id).map(CategoryServiceImpl::mapToDto)
            .orElseThrow(()-> new EntityNotFoundException("Category not found with id "+id));
    }

    @Override
    public CategoryDto save(CategoryDto entity) {
        log.debug("Request to create category : {}", entity);

        Category category = new Category();
        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        category.setPicture(entity.getPicture());

        return mapToDto(this.categoryRepository
            .save(category)); 
    }

    @Override
    public CategoryDto update(Integer id, CategoryDto entity) {
        log.debug("Request to update category : {}", id);

        var category = this.categoryRepository
                            .findById(id)
                            .orElse(null);

        category.setCategoryName(entity.getCategoryName());
        category.setDescription(entity.getDescription());
        this.categoryRepository.save(category);
        return mapToDto(category);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete category : {}", id);

        var category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Category with id " + id)); 

        this.categoryRepository.delete(category);
    }
    
}
