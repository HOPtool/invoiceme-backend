/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record AccountingCustomerParty(
        //String id, 
        String party_name, 
        @NotBlank
        @Size(min=3, max=30, message="tin is required")
        String tin,
        @Email
        String email,
        String telephone,
        String business_description,
        PostalAddress postal_address) {
    
    
}
