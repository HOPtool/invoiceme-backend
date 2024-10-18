/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import java.util.List;

/**
 *
 * @author paycraftsystems-i
 */
public record InvoiceLineObj(
        
        double taxable_amount,
        double tax_amount,
        TaxCategoryObj tax_category
        
       //List<InvoiceLineData> invoice_line
        
       ) {
    
}
