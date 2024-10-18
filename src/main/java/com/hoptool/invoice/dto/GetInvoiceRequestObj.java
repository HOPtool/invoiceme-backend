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
public class GetInvoiceRequestObj
{

    public GetInvoiceRequestObj(GetInvoiceRequest objx) {
        
        this.irn = objx.irn();
    }
    
       public String irn;

 
}
