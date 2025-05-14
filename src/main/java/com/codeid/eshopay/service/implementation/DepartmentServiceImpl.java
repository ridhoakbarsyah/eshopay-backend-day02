package com.codeid.eshopay.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeid.eshopay.model.dto.DepartmentDto;
import com.codeid.eshopay.model.entity.Department;
import com.codeid.eshopay.repository.DepartmentRepository;
import com.codeid.eshopay.service.DepartmentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    
    private final DepartmentRepository departmentRepository;

    //Constructor injection vs Autowired: kalau injection dia imuttable, kalau autowired dia mutable
    // public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    //     this.departmentRepository = departmentRepository;
    // }
    //------- diwakili RequiredArgs lombok

    public static DepartmentDto mapToDto(Department department){
        return new DepartmentDto(
            department.getDepartmentId(), 
            department.getDepartmentName());
    }

    public static Department mapToEntity(DepartmentDto departmentDto){
        return new Department(
            departmentDto.getDepartmentId(), 
            departmentDto.getDepartmentName()
            );
    }

    @Override
    public List<DepartmentDto> findAll() {
       log.debug("Request get all data from departments"); 
       return this.departmentRepository.findAll()
                .stream()
                .map(DepartmentServiceImpl::mapToDto)
                .collect(Collectors.toList());
       
    }

    @Override
    public DepartmentDto findById(Integer id) {
        log.debug("Request to get Department : {}", id);

        return this.departmentRepository.findById(id).map(DepartmentServiceImpl::mapToDto)
            .orElseThrow(()-> new EntityNotFoundException("Department not found with id "+id));
    }

    @Override
    public DepartmentDto save(DepartmentDto entity) {
        log.debug("Request to create department : {}", entity);

        return mapToDto(this.departmentRepository
                .save(new Department(entity.getDepartmentName())));  
    }

    @Override
    public DepartmentDto update(Integer id, DepartmentDto entity) {
        log.debug("Request to update Department : {}", id);

        var department = this.departmentRepository
                            .findById(id)
                            .orElse(null);

        department.setDepartmentName(entity.getDepartmentName());
        this.departmentRepository.save(department);
        return mapToDto(department);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Department : {}", id);

        var department = this.departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Department with id " + id)); 

        this.departmentRepository.delete(department);
    }

    

    
    
}
