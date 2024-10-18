/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author paycraftsystems-i
 */
@Table(name="billing_reference")
public class BillingReferenceLog {
    
    
       @Column(name="business_id")
       public String businessId;
       
       
       @Column(name="original_invoice_id")
       public long originalLnvoiceId;
    
       @Column(name="irn")
       public String irn;
      
       @Column(name="issue_date")
       public LocalDate issueDate;
     
}
