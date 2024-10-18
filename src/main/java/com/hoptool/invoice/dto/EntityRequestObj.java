/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class EntityRequestObj{
    

        
        
        public  EntityRequestObj(EntityRequest rx) 
        {
             this.entity_id = rx.entity_id();
             this.requestId = rx.requestId();
             this.invoiceId = rx.invoiceId();
        }
        
        
        public String entity_id;
        public String requestId;
        public String  invoiceId;
    
}
