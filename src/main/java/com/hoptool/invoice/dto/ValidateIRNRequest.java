/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */

public record ValidateIRNRequest(
        
            @NotBlank
            @Size(min=3, max= 30, message ="invoice_reference must be between 3 and 30 characters long")
            String invoice_reference,
            @NotBlank
            @Size(min=3, max= 40, message ="business id must be between 3 and 30 characters long")
            String business_id,
            @NotBlank
            @Size(min=3, max= 30, message ="irn must be between 3 and 30 characters long")
            String irn,
            @NotBlank
            @Size(min=30, max= 40, message ="request id must be between 30 and 40 characters long")
            String request_id
        
        ) {
    
}
