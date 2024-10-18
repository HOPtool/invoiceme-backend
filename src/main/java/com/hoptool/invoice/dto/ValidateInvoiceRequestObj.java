/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class ValidateInvoiceRequestObj
{
    
          public ValidateInvoiceRequestObj(ValidateInvoiceRequest rx )
          {
        
            this.invoice_reference = rx.invoice_reference();
            this.business_id = rx.business_id();
            this.irn = rx.irn();
           }
        
    
                    
          public String invoice_reference;
          public String business_id;
          public String irn;
    
}
