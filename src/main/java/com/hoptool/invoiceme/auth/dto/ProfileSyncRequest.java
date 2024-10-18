/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.auth.dto;


import com.hoptool.invoiceme.auth.dto.*;
import com.hoptool.invoiceme.validators.E164PhoneNumber;
import com.hoptool.invoiceme.validators.HoptoolUserPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author root
 */

public record ProfileSyncRequest (
    
     @NotBlank
     @Size(min = 3, message="code cannot be empty")
     String code,
     @NotBlank
     @Size(min = 3, message="codeLink cannot be empty")
     String codeLink,
     @NotBlank
     @HoptoolUserPassword
     //@Size(min = 8, message="must be alphanuric with at least 8 characters one upper case on special character, one number")
     String password,
     @NotBlank
     //@Size(min = 8, message="must match value in password")
     @HoptoolUserPassword
     String verifyPassword,
//     @NotBlank
//     @Size(min = 4, message="must be 4 digits long")
//     String pin,
//     @NotBlank
//     @Size(min = 4, message="must be equal the value in pin")
//     String verifyPin,
     
//     @Size(min = 4, message="must be 4 digits long")
//     String newPin,
     @NotBlank
     @E164PhoneNumber
     //@Size(min = 14, max= 14, message="must be 14 digits long with a leadind +234")
     String msisdn,
     //String userCode,
     @NotBlank
     @Size(min = 3, message="must be 3 at least characters long")
     String channel,
     @NotNull
     @Range(min=1,message="must be greater than zero")
     long pid,
     @NotBlank
     @Size(min = 4, message="must be greater than 4 characters long")
     String controlCode,
     //@NotBlank
     //@Size(min = 4, message="must be greater than 4 characters long")
     //String principal,
     //String principalControlCode,
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
