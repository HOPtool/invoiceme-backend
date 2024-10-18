/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public record LegalMonetaryTotalObj
        (
        
         double line_extension_amount,
         double tax_exclusive_amount,
         double tax_inclusive_amount,
         double payable_amount
        ) 
{
    
}
