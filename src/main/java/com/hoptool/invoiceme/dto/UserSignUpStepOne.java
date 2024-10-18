/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.validators.HoptoolUserPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record UserSignUpStepOne(
        @NotBlank
        @Size(min=1, max= 100, message="full name cannot be null")
        String fullname, 
        @Email
        @NotBlank
        @Size(min=1, max= 100, message="full name cannot be null")
        String companyEmail,
        @NotBlank
        @Size(min=1, max= 100, message="full name cannot be null")
        String companyMobile,
        @NotBlank
        @HoptoolUserPassword
        String password, 
        @NotBlank
        @HoptoolUserPassword
        String verifyPassword, 
        @NotNull
        @Size(min=1, max= 100, message="corporateId cannot be null")
        String corporateId){
    
}
