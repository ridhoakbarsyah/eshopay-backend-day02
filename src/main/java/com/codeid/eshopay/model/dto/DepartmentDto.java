package com.codeid.eshopay.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Integer departmentId;

    @Size(max = 30, message = "Length value must not exceeded than 30")
    private String departmentName;
}
