package com.hoptool.invoiceme.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.ToString;


/*
   enabling records
*/
public record ResetOwnKeysRequest(

        
        @NotBlank
        @Email
        @Size(min = 4, max=30, message="Invalid ux parameter, must be in the format of an email")
        String ux,
        
        @NotBlank
        @Size(min = 16, max=16, message="Invalid iv parameter, must be 16 characters in length")
        String iv,
        
        @NotBlank
        @Size(min = 16, max=16, message="Invalid key parameter, must be 16 characters in length")
        String key
) 
{
}
