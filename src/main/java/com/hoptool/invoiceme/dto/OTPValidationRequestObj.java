/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class OTPValidationRequestObj
{
    
        public OTPValidationRequestObj(OTPValidationRequest rx)
        {
             this.otp = rx.otp();
             this.corporateId = rx.corporateId();
             this.userEmail = rx.userEmail();
        }
        
        public String otp;
        public String corporateId;
        public String userEmail;

    
}
