package com.codeid.eshopay.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeid.eshopay.model.dto.ShipperDto;
import com.codeid.eshopay.service.BaseCrudService;
import com.codeid.eshopay.service.ShipperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/shipper/")
public class ShipperController extends BaseCrudController<ShipperDto, Integer>{
    
    private final ShipperService shipperService;

    @Override
    protected BaseCrudService<ShipperDto, Integer> getService() {
        return shipperService;    
    }

    @Override
    public ResponseEntity<ShipperDto> create(@Valid ShipperDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<ShipperDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<ShipperDto> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<ShipperDto> update(Integer id, @Valid ShipperDto entity) {
        return super.update(id, entity);
    }
    
}
