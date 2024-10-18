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



/**
 *
 * @author paycraftsystems-i
 */
@ToString
public class GenerateIRNRequestObj
{
        
        
        public GenerateIRNRequestObj(GenerateIRNRequest objx) {
             this.invoiceId = objx.invoiceId();
        }
       
        public String invoiceId;
    
}
