/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */
@ToString
public class ValidateIRNRequestObj
{

    public ValidateIRNRequestObj(ValidateIRNRequest objx) {
    
             this.invoice_reference = objx.invoice_reference();
             this.business_id = objx.business_id();
             this.irn = objx.irn();
             this.request_id = objx.request_id();
    
    }
    

    
    
     public String invoice_reference;
     public String business_id;
     public String request_id;
     public String irn;
        
           
        
  
    
}
