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
public record GenerateIRNRequest(
        @NotBlank
        @Size(min=3, message="InvoiceId must be at least 3 characters long (alphanumeric accepted)")
        String invoiceId,
        @NotBlank
        @Size(min=3, max= 40, message ="business id must be between 3 and 30 characters long")
        String business_id



)

  {
 
}
