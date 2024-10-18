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
public record EntityRequest(
        @NotBlank
        @Size(min=10, max=50, message="entity id cannot be null, must be be at least 10 and 50 characters long")
        String entity_id, 
        @NotBlank
        @Size(min=3, max=30, message="Invoice id cannot be null, must be be at least 3 and 30 characters long")
        String invoiceId,
        @NotBlank
        @Size(min=30, max=50, message="Invoice id cannot be null, must be be at least 30 and 50 characters long")
        String requestId) {
    
}
