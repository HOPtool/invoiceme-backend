/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record SysLoginRequest(
        
        
        @NotBlank
        @Size(min=3, message="ux cannot not be null or empty")
        String ux,
        @NotBlank
        @Size(min=16, max= 16, message="iv must be 16 characters long ")
        String iv,
        @Size(min=16, max= 16, message="key be 16 characters long ")
        String key
       ) 
{
    
}
