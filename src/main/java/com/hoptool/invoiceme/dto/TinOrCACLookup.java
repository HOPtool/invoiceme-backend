/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record TinOrCACLookup(
        @NotBlank
        @Size(min=3, max=3, message="invalid validation type")
        String validationType, 
        @NotNull
        @Size(min=3, max=20, message="invalid business no")
        String businessNo) {
    
}
