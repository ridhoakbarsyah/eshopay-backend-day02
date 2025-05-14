package com.codeid.eshopay.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperDto {
    private Integer shipperId;

    @Size(max = 40, message = "max length of company name is 40")
    private String companyName;

    @Size(max = 24, message = "max length of phone number is 24")
    private String phone; 
}
