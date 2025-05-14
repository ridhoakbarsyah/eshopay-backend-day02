package com.codeid.eshopay.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private Integer employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    private int jobId;

    private double salary;

    private Long managerId;

    private String photo;

    private DepartmentDto department;

    //private FileUploadResponse fileUploadResponse;
}
