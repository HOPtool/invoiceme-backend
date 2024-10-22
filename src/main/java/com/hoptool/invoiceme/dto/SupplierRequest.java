/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.validators.E164PhoneNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record SupplierRequest(
        @NotBlank
        @Size(min=3, max=100, message="company name must be valid")
        String companyName, 
        @NotBlank
        @Size(min=3, max=15, message="cac number must be valid")
        String cacNumber,
        @NotBlank
        @Size(min=3, max=15, message="tin number must be valid")
        String tinNumber,
        @NotBlank
        @Size(min=3, max=80, message="contact email must be valid")
        String contactEmail, 
        @NotBlank
        @E164PhoneNumber
        String contactPhoneNumber, 
        @NotBlank
        @Size(min=3, max=15, message="cac number must be valid")
        String bankName,
        @NotBlank
        @Size(min=3, max=10, message="bank code must be valid")
        String bankCode,
        @NotBlank
        @Size(min=3, max=15, message="bank sort code must be valid")
        String bankSortCode,
        @NotBlank
        @Size(min=10, max=10, message="bank account must be valid")
        String bankAccountNumber,
        @NotBlank
        @Size(min=3, max=200, message="company address must be valid")
        String companyAddress,
        @NotBlank
        @Size(min=3, max=20, message="businss id must be valid")
        String businessId, 
        @NotNull
        @Min(1)
        //,  message="actionBy id must be valid")
        Long actionBy,
        @NotBlank
        @Size(min=3, max=20, message="businss id must be valid")
        String actionByStr
        ) 

{
    
}
