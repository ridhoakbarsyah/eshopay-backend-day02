package com.codeid.eshopay.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeid.eshopay.model.dto.ProductDto;
import com.codeid.eshopay.model.entity.Category;
import com.codeid.eshopay.model.entity.Product;
import com.codeid.eshopay.model.entity.Supplier;
import com.codeid.eshopay.repository.ProductRepository;
import com.codeid.eshopay.service.ProductService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public static ProductDto mapToDto(Product product){
        return new ProductDto(
            product.getProductId(), product.getProductName(), 
            product.getQuantityPerUnit(), product.getUnitPrice(), 
            product.getUnitsInStock(), product.getUnitsOnOrder(), 
            product.getReorderLevel(), product.getDiscontinued(), 
            product.getPhoto(), 
            SupplierServiceImpl.mapToDto(product.getSupplier()),
            CategoryServiceImpl.mapToDto(product.getCategory()));
    }

    private Product mapToEntity(ProductDto productDto){
        return new Product(
            productDto.getProductId(), productDto.getProductName(),
            productDto.getQuantityPerUnit(), productDto.getUnitPrice(),
            productDto.getUnitsInStock(), productDto.getUnitsOnOrder(),
            productDto.getReorderLevel(), productDto.getDiscontinued(),
            productDto.getPhoto(),
            SupplierServiceImpl.mapToEntity(productDto.getSupplier()),
            CategoryServiceImpl.mapToEntity(productDto.getCategory()));
    }

    @Override
    public List<ProductDto> findAll(){
        log.debug("Request to get all Products");
        return this.productRepository.findAll().stream()
                .map(ProductServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Integer id) {
        log.debug("Request to get Product : {}", id);
        return this.productRepository.findById(id).map(ProductServiceImpl::mapToDto)
            .orElseThrow(()-> new EntityNotFoundException("Product not found with id "+id));
    }

    @Override
    public ProductDto save(ProductDto entity) {
        log.debug("Request to create Product : {}", entity);

        var product = mapToEntity(entity);

        //after save, langsung ubah ke dto
        return mapToDto(this.productRepository.save(product));    
    }

    @Override
    public ProductDto update(Integer id, ProductDto entity) {
        log.debug("Request to update Product : {}", id);
        var product = this.productRepository
                            .findById(id)
                            .orElseThrow(()-> new EntityNotFoundException("Product not found with id "+id));
        
        product.setProductName(entity.getProductName());
        product.setQuantityPerUnit(entity.getQuantityPerUnit());
        product.setUnitPrice(entity.getUnitPrice());
        product.setUnitsInStock(entity.getUnitsInStock());
        product.setUnitsOnOrder(entity.getUnitsOnOrder());
        product.setReorderLevel(entity.getReorderLevel());
        product.setDiscontinued(entity.getDiscontinued());
        product.setSupplier(new Supplier(entity.getSupplier().getSupplierId(), entity.getSupplier().getCompanyName()));
        product.setCategory(new Category(entity.getCategory().getCategoryId(), entity.getCategory().getCategoryName(),
                                    entity.getCategory().getDescription(), entity.getCategory().getPicture()));

        this.productRepository.save(product);
        return mapToDto(product);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete product : {}", id);

        var product = this.productRepository.findById(id)
        .orElseThrow(()-> new EntityNotFoundException("Product not found with id "+id));

       this.productRepository.deleteById(product.getProductId());
    }
    
}
