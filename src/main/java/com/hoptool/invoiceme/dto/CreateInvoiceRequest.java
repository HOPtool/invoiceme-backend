/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import java.util.List;

/**
 *
 * @author paycraftsystems-i
 */
public record CreateInvoiceRequest(String supplierTin, String invoiceType, String invoiceDate, String category, String dueDate, String currency, String supplierName, String billToCompanyName, String supplierEmail, String billToEmail, String supplierMobile, String billToContactPerson, String billToContactPersonDept, String supplierAddress, String billToContactPersonBranchOrDivision, String invoicePurpose, List<InvoiceItem> items, double subTotal, double tax, double shippingCost, double total,String paymentDetails) {
    
}
