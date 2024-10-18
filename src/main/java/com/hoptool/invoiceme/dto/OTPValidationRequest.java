/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.validators.DigitsOnly;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record OTPValidationRequest(
        @DigitsOnly
        @NotBlank
        @Size(min=4, max=6, message="otp must be between 4 and 6 characters long")
        String otp, 
        @NotBlank
        @Size(min=4, max=14, message="corporateId must be 10  characters long")
        String corporateId, 
        @Email
        @NotBlank
        @Size(min=4, max=60, message="userEmail must be a valid email")
        String userEmail) 
{
    
}
