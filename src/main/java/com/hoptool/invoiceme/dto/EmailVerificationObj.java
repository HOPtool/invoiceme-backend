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
public class EmailVerificationObj
{
    public EmailVerificationObj(EmailVerificationRequest rx){
        
       this.companyEmail = rx.companyEmail();
       this.otp = rx.otp();
       this.corporateId = rx.corporateId();
        
    }
    
    
    public String companyEmail;
    public String otp;
    public String corporateId;
    
}
