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
public record GetInvoiceRequest(
        @NotBlank
        @Size(min=3, message="InvoiceId must be at least 3 characters long (alphanumeric accepted)")
        String irn) 
{
 
}
