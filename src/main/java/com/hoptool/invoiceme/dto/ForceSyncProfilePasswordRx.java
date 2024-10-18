/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;


import com.hoptool.invoiceme.validators.HoptoolUserPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */


public record ForceSyncProfilePasswordRx(
        
        @NotBlank
        //@Size(min=8, max=16, message="password must be between 8  and 16 characters long, with one special character, one number")
        @HoptoolUserPassword
        String password,
        
        @Email
        @Size(min=8, max=70, message="email must be between 8  and 70 characters long")
        String email,
        @NotBlank
        @HoptoolUserPassword
        //@Size(min=8, max=16, message="verify password must be same as password")
        String verifyPassword,
        @NotBlank
        @Size(min=3, max=30, message="corporateId  must be a valid string")
        String corporateId,
        @NotBlank
        @Size(min=3, max=30, message="clientId  must be a valid string")
       
         @Size(min = 4, max=4, message="must be 4  characters long")
         String otp
        
        ) 
{
  

}
