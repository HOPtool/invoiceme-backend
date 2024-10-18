/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.eivc.response.dto;

import com.hoptool.invoice.dto.InvoiceValidationData;

/**
 *
 * @author paycraftsystems-i
 */
public record UpdatedInvoiceResponse(String code,InvoiceValidationData data, String errorDesc) {
    
}
