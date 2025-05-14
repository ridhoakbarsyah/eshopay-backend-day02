package com.codeid.eshopay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codeid.eshopay.model.entity.Department;
import com.codeid.eshopay.repository.DepartmentRepository;
import com.codeid.eshopay.service.implementation.DepartmentServiceImpl;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class DepartmentTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void testFindById() {
        // arrange
        // data source
        Department mockDept = new Department(1, "HR");
        when(departmentRepository.findById(1)).thenReturn(Optional.of(mockDept));

        // act
        var result = departmentService.findById(1);

        // assert
        assertNotNull(result);
        assertEquals("HR", result.getDepartmentName());
    }

    @Test
    void testFindById_notFound_throwsException() {
        // Arrange
        when(departmentRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            departmentService.findById(99);
        });

        assertEquals("Department not found with id 99", ex.getMessage());
    }


    @Test
    void testSave_success() {
        //arrange
        var input = new Department(null, "Finance");
        var saved = new Department(2, "Finance");

        when(departmentRepository.save(input)).thenReturn(saved);
        
        var inputDto = DepartmentServiceImpl.mapToDto(input);
        
        //act
        var result = departmentService.save(inputDto);

        //assert
        assertNotNull(result.getDepartmentId());
        assertEquals("Finance", result.getDepartmentName());
    }

    @Test
    void testDelete_success() {
        //1. arrange
        Integer departmentId = 1;
        Department mockDept = new Department(departmentId, "Sales");

        //mockup repository response
        when (departmentRepository.findById(departmentId)).thenReturn(Optional.of(mockDept));

        //mock void method
        doNothing().when(departmentRepository).delete(mockDept);

        //2. act
        departmentService.delete(departmentId);

        // assert
        //verifikasi : 
        //1. findById() Call 1x with parameter
        //2. delete() call 1x dengan object yg benar

        verify(departmentRepository,times(1)).findById(departmentId);
        verify(departmentRepository,times(1)).delete(mockDept);

    }
}
