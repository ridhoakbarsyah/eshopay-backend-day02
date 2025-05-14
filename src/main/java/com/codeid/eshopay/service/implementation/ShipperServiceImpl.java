package com.codeid.eshopay.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeid.eshopay.model.dto.ShipperDto;
import com.codeid.eshopay.model.entity.Shipper;
import com.codeid.eshopay.repository.ShipperRepository;
import com.codeid.eshopay.service.ShipperService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipperServiceImpl implements ShipperService{
    private final ShipperRepository shipperRepository;

    public static ShipperDto mapToDto(Shipper shipper){
        return new ShipperDto(
            shipper.getShipperId(), 
            shipper.getCompanyName(),
            shipper.getPhone());
    }

    @Override
    public List<ShipperDto> findAll() {
        log.debug("Request get all data from shippers"); 
       return this.shipperRepository.findAll()
                .stream()
                .map(ShipperServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShipperDto findById(Integer id) {
        log.debug("Request to get Department : {}", id);

        return this.shipperRepository.findById(id).map(ShipperServiceImpl::mapToDto)
            .orElseThrow(()-> new EntityNotFoundException("Shipper not found with id "+id));
    }

    @Override
    public ShipperDto save(ShipperDto entity) {
        log.debug("Request to create shipper : {}", entity);
        
        Shipper shipper = new Shipper();
        shipper.setCompanyName(entity.getCompanyName());
        shipper.setPhone(entity.getPhone());

        return mapToDto(this.shipperRepository
                .save(shipper));}

    @Override
    public ShipperDto update(Integer id, ShipperDto entity) {
        log.debug("Request to update Shipper : {}", id);

        var shipper = this.shipperRepository
                        .findById(id)
                        .orElse(null);

        shipper.setCompanyName(entity.getCompanyName());
        shipper.setPhone(entity.getPhone());
        this.shipperRepository.save(shipper);
        return mapToDto(shipper);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Department : {}", id);

        var department = this.shipperRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Shipper with id " + id)); 

        this.shipperRepository.delete(department);
    }

    
}
