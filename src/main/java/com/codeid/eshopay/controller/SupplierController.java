package com.codeid.eshopay.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeid.eshopay.model.dto.SupplierDto;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.SupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/supplier/")
public class SupplierController extends BaseCrudController<SupplierDto, Integer>{
    private final SupplierService supplierService;

    @Override
    protected BaseCrudService<SupplierDto, Integer> getService() {
        return supplierService;
    }

    @Override
    public ResponseEntity<SupplierDto> create(@Valid SupplierDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<SupplierDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<SupplierDto> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<SupplierDto> update(Integer id, @Valid SupplierDto entity) {
        return super.update(id, entity);
    }

    
}
