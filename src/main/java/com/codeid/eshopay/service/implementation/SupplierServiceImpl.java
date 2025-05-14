package com.codeid.eshopay.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeid.eshopay.model.dto.SupplierDto;
import com.codeid.eshopay.model.entity.Supplier;
import com.codeid.eshopay.repository.SupplierRepository;
import com.codeid.eshopay.service.SupplierService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService{
    
    private final SupplierRepository supplierRepository;

    public static SupplierDto mapToDto(Supplier supplier){
        return new SupplierDto(
            supplier.getSupplierId(), 
            supplier.getCompanyName());
    }

    public static Supplier mapToEntity(SupplierDto supplierDto){
        return new Supplier(
            supplierDto.getSupplierId(),
            supplierDto.getCompanyName());
    }
    
    @Override
    public List<SupplierDto> findAll() {
        log.debug("Request get all data from suppliers"); 
       return this.supplierRepository.findAll()
                .stream()
                .map(SupplierServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto findById(Integer id) {
        log.debug("Request to get Supplier : {}", id);

        return this.supplierRepository.findById(id).map(SupplierServiceImpl::mapToDto)
            .orElseThrow(()-> new EntityNotFoundException("Supplier not found with id "+id));
    }

    @Override
    public SupplierDto save(SupplierDto entity) {
       log.debug("Request to create Supplier : {}", entity);

        return mapToDto(this.supplierRepository
                .save(new Supplier(entity.getCompanyName())));  
    }

    @Override
    public SupplierDto update(Integer id, SupplierDto entity) {
        log.debug("Request to update Supplier : {}", id);

        var supplier = this.supplierRepository
                            .findById(id)
                            .orElse(null);

        supplier.setCompanyName(entity.getCompanyName());
        this.supplierRepository.save(supplier);
        return mapToDto(supplier);}

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Supplier : {}", id);

        var department = this.supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Supplier with id " + id)); 

        this.supplierRepository.delete(department);
    }
    
}
