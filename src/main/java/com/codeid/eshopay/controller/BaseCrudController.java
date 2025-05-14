package com.codeid.eshopay.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import com.codeid.eshopay.service.BaseCrudService;

public abstract class BaseCrudController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

     @GetMapping("/")
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<T> create(@RequestBody @Valid T entity) {
        return ResponseEntity.ok(getService().save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody @Valid T entity) {
        return ResponseEntity.ok(getService().update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
