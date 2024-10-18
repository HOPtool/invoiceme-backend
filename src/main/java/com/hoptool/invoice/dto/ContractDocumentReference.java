/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 *
 * @author paycraftsystems-i
 */
public record ContractDocumentReference(
         @NotBlank
         @Size(min=10, max = 100, message="invalid irn ")
         String irn,
         @NotBlank
         @Size(min=10, max = 10, message="invalid issue date should be in format YYYY-MM-DD")
         String issue_date
        
        ) {
    
}
