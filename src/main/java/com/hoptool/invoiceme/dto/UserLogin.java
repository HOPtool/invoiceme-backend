/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.validators.HoptoolUserPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record UserLogin(
        @NotBlank
        @Size(min=7, max = 100, message="email cannot be blank")
        String email, 
        @NotBlank
        @HoptoolUserPassword
        @Size(min=7, max = 100, message="password cannot be blank")
        String password, 
        @NotBlank
        @Size(min=7, max = 100, message="corporate id must be valid")
        String corporateId) {
    
}
