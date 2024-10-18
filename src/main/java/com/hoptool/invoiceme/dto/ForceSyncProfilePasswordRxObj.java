/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.auth.dto.*;
import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */


@ToString
public class ForceSyncProfilePasswordRxObj
{
        
    
      public ForceSyncProfilePasswordRxObj(ForceSyncProfilePasswordRx rx)
      {
        this.password = rx.password();
        this.verifyPassword = rx.verifyPassword();
        this.email = rx.email();
        this.corporateId = rx.corporateId();
        this.otp = rx.otp();
       }
        

      
        public String password;
        public String otp;
        public String clientId;
        public String email;
        public String verifyPassword;
        public String channel;
        public String corporateId;
        public String msisdn;


}
