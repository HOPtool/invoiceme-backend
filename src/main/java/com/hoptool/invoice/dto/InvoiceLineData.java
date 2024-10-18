/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public record InvoiceLineData(
        
         long invoiced_quantity,
         double line_extension_amount,
         InvoiceLineDataItem item,
         InvoiceLineItemDataPrice price
        
        ) {
    
}
