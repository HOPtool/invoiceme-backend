/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class UpdateInvoiceObj
{
    public UpdateInvoiceObj(UpdateInvoice rx) {
        
       this.payment_status = rx.payment_status();
       this.reference = rx.reference();
       this.requestId = rx.requestId();
       this.irn = rx.irn();
       this.business_id = rx.business_id();
        
    }
    
    
    public String payment_status;
    public String reference;
    public String irn;
    public String requestId;
    public String  business_id;
    
}
