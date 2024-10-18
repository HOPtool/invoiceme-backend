/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author root
 */

public record UserLoginRequest (
    
     String channel,
     long pid,
     String controlCode,
     String password,
     String code,
     String codeLink,
     String clientId
        

)
{

  
}
