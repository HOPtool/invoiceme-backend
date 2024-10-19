/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.dto;


import com.hoptool.invoiceme.validators.HoptoolUserPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author root
 */

public record ChangeProfilePasswordRequest (
    
     @NotBlank
     @Size(min = 3, message="email cannot be empty")
     String email,
    
     @NotBlank
     @HoptoolUserPassword
     String password,
     @NotBlank
     @HoptoolUserPassword
     String newPassword,
     @NotBlank
     @HoptoolUserPassword
     String verifyPassword,

     @NotBlank
     @Size(min = 3, message="corporateId must be valid")
     String corporateId
     
        

)
{
    /*
    public ProfileSyncRequest{
  
       if(!password.equals(verifyPassword))
       {
       
           throw new IllegalArgumentException("Password and verifyPassword must be same");
       }
    }
   */
  
}
