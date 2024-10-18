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
public class CreateInvoiceRequestObj
{
     public CreateInvoiceRequestObj(CreateInvoiceRequest rx) {
     
        this.supplierTin = rx.supplierTin();
        this.invoiceType = rx.invoiceType();
        this.invoiceDate = rx.invoiceDate();
        this.category = rx.category();
        this.dueDate = rx.dueDate();
        this.currency = rx.currency();
        this.supplierName = rx.supplierName();
        this.billToCompanyName = rx.billToCompanyName();
        this.supplierEmail = rx.supplierName();
        this.billToEmail = rx.billToEmail();
        this.supplierMobile = rx.supplierMobile();
        this.billToContactPerson = rx.billToContactPerson();
        this.billToContactPersonDept = rx.billToContactPersonDept();
        this.supplierAddress = rx.supplierAddress();
        this.billToContactPersonBranchOrDivision = rx.billToContactPersonBranchOrDivision();
        this.invoicePurpose = rx.invoicePurpose();
        this.items = rx.items();
        this.subTotal = rx.subTotal();
        this.tax = rx.tax();
        this.shippingCost = rx.shippingCost();
        this.total = rx.total();
        this.paymentDetails = rx.paymentDetails();
     }

     
     public String supplierTin;
     public String invoiceType;
     public String invoiceDate;
     public String category;
     public String dueDate;
     public String currency;
     public String supplierName;
     public String billToCompanyName;
     public String supplierEmail;
     public String billToEmail;
     public String supplierMobile;
     public String billToContactPerson;
     public String billToContactPersonDept;
     public String supplierAddress;
     public String billToContactPersonBranchOrDivision;
     public String invoicePurpose;
     public List<InvoiceItem> items;
     public double subTotal;
     public double tax;
     public double shippingCost;
     public double total;
     public String paymentDetails;
     
     
     
}
