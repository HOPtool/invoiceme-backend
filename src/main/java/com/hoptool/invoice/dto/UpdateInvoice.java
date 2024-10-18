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
public record UpdateInvoice(
        @NotBlank
        @Size(min=4, max=20, message ="invalid payment_status passed the valid values are PAID, CANCELLED")
        String payment_status, 
        @NotBlank
        @Size(min=4, max=40, message ="invalid irn passed, please check and try again")
        String irn, 
        @NotBlank
        @Size(min=4, max=20, message ="invalid payment_status passed, please check and try again")
        String reference, 
        @NotBlank
        @Size(min=30, max=40, message ="invalid requestId passed, must be a minimum of 30 and maximum of 40 characters")
       
        String requestId,
        
        @NotBlank
        @Size(min=20, max=50, message ="invalid business_id passed, must be a minimum of 20 and maximum of 50 characters")
        String business_id
        
        
        ) {
    
    
    
    
}
