/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;


import com.hoptool.invoiceme.validators.E164PhoneNumber;
import com.hoptool.invoiceme.validators.HoptoolUserPassword;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */


public record ForceSyncProfilePasswordRequest(
        
        //@NotBlank
        //@Size(min=8, max=16, message="password must be between 8  and 16 characters long, with one special character, one number")
        @HoptoolUserPassword
        String password,
        @NotBlank
        @Size(min=6,  message="code must be at least 6 characters long")
        String code,
        @NotBlank
        @Size(min=8, max=40, message="codeLink must be between 8  and 40 characters long")
        String codeLink,
        @NotBlank
        @HoptoolUserPassword
        //@Size(min=8, max=16, message="verify password must be same as password")
        String verifyPassword,
        @NotNull
        @Min(value=1, message="must be at least {value}")
        long pid,
        @NotBlank
        @Size(min=3, max=30, message="controlCode  must be a valid string")
        String controlCode,
        @NotNull
        @E164PhoneNumber
        //@Size(min=3, max=30, message="controlCode  must be a valid string")
        String msisdn, 
        @NotBlank
        @Size(min=3, max=30, message="clientId  must be a valid string")
        String clientId,
         @NotBlank
         @Size(min = 3, message="must be 3 at least characters long")
        String channel,
        
         @Size(min = 4, max=4, message="must be 4  characters long")
         String otp
        
        ) 
{
  

}
