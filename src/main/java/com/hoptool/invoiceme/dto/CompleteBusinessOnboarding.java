/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.validators.E164PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record CompleteBusinessOnboarding(
        @NotBlank
        @Email
        @Size(min=6, max=70, message="company email must be valid")
        String companyEmail,
        @NotBlank
        @E164PhoneNumber
        String companyMsisdn,
        @NotBlank
        @Size(min=8, max=20, message="corporate id must be valid")
        String corporateId, 
        @NotBlank
        @Size(min=4, max=140, message="logo url  must be valid")
        String logoUrl, 
        String firsMBSNo,
        @NotBlank
        @Size(min=3, max=10, message="business verification type must be a valid (TIN, CAC) no ")
        String businessVerificationType, 
        @NotBlank
        @Size(min=4, max=20, message="tin or cac no must be valid")
        String businessNo,
        @NotBlank
        @Size(min=4, max=200, message="business name no must be valid")
        String businessName
        
        ) 
{
    
}
