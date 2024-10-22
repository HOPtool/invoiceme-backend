/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.validators.E164PhoneNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record ApproveOrDeleteSupplierRequest(
        
        @NotNull
        @Min(1)
        //@Size(min=0, message="tid  must be valid")
        Long tid,
        @NotNull
        long actionBy,
        @NotBlank
        @Size(min=3, max=20, message="businss id must be valid")
        String actionByStr
        ) 

{
    
}
