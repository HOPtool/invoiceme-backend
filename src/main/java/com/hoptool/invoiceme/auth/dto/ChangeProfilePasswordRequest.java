/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.auth.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author root
 */

public record ChangeProfilePasswordRequest (
    
     @NotBlank
     @Size(min = 3, message="code cannot be empty")
     String code,
     @NotBlank
     @Size(min = 3, message="codeLink cannot be empty")
     String codeLink,
    
     @NotBlank
     @Size(min = 3, message="must be 3 at least characters long")
     String channel,
     @NotNull
     @Range(min=1,message="must be greater than zero")
     long pid,
     @NotBlank
     @Size(min = 4, message="must be greater than 4 characters long")
     String controlCode,
     @NotBlank
     @Size(min = 4, message="password must be valid")
     String password,
     @NotBlank
     @Size(min = 4, message="new password must be valid")
     String newPassword,
     @NotBlank
     @Size(min = 3, message="must be greater than 4 characters long")
     String clientId
     
        

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
