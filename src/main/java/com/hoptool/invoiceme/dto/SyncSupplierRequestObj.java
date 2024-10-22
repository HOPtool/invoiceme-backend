/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */

@ToString
public class SyncSupplierRequestObj
{
    public SyncSupplierRequestObj(SyncSupplierRequest rx) 
    {
         this.companyName  = rx.companyName();
         this.cacNumber = rx.cacNumber();
         this.tinNumber = rx.tinNumber();
         this.contactEmail = rx.contactEmail();
         this.contactPhoneNumber = rx.contactPhoneNumber();
         this.bankName = rx.bankName();
         this.bankCode = rx.bankCode();
         this.bankSortCode = rx.bankSortCode();
         this.bankAccountNumber = rx.bankAccountNumber();
         this.companyAddress = rx.companyAddress();
         this.actionBy = rx.actionBy();
         this.actionByStr = rx.actionByStr();
         this.businessId = rx.businessId();
         this.tid = rx.tid();
            
    }
        
      
    
    public String companyName;
    public String cacNumber;
    public String tinNumber;
    public String contactEmail;
    public String contactPhoneNumber;
    public String bankName;
    public String bankCode;
    public String bankSortCode;
    public String bankAccountNumber;
    public String companyAddress;
    public long actionBy;
    public String actionByStr;
    public String businessId;
    public long tid;
        
        
        
}
