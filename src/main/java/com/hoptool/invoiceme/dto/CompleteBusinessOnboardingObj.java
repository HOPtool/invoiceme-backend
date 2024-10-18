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
public class CompleteBusinessOnboardingObj
{
    public CompleteBusinessOnboardingObj(CompleteBusinessOnboarding rx) 
    {
        
        this.logoUrl = rx.logoUrl();
        this.firsMBSNo = rx.firsMBSNo();
        this.businessVerificationType = rx.businessVerificationType();
        this.msisdn = rx.companyMsisdn();
        this.businessNo = rx.businessNo();
        this.companyEmail = rx.companyEmail();
        this.corporateId = rx.corporateId();
        this.businessName = rx.businessName();
    
        
    }
    
    
   public String logoUrl;
   public String firsMBSNo;
   public String msisdn;
   public String businessVerificationType;
   public String tin;
   public String companyEmail;
   public String corporateId;
   public String businessNo;
   public String businessName;
    
}
