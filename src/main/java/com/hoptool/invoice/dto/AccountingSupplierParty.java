/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import com.hoptool.invoiceme.validators.E164PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record AccountingSupplierParty(
        //String id,
        String party_name,
        @NotBlank
        @Size(min=3, max=20, message= "tin must valid")
        String tin,
        @NotBlank
        @Size(min=6, max=100, message= "must be a valid email")
        String email,
        @E164PhoneNumber
        String telephone,
        String business_description,
        PostalAddress postal_address) {
    
    
}
