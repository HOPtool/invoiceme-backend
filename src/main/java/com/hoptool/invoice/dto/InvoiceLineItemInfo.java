/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record InvoiceLineItemInfo(
            @NotBlank
            @Size(min=2, max=20, message="hsn_code must be valid")
            String hsn_code,
            @NotBlank
            @Size(min=3, max=100, message="product_category must be valid")
            String product_category, 
            @NotNull
            @Min(value=0, message="discount_rate must be greater than {value}")
            double discount_rate,
            @NotNull
            @Min(value=0, message="discount_amount must be greater than {value}")
            double discount_amount,
            @NotNull
            @Min(value=0, message="discount_amount must be greater than {value}")
            double fee_rate, 
            @NotNull
            @Min(value=0, message="discount_amount must be greater than {value}")
            double fee_amount,
            long invoiced_quantity,
            double line_extension_amount, 
            InvoiceLineItems item, 
            InvoiceLinePrices price) {
    
}
