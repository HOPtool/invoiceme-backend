/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;

import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */


@ToString
public class ForceSyncProfilePasswordRequestObj
{
        
    
      public ForceSyncProfilePasswordRequestObj(ForceSyncProfilePasswordRequest rx)
      {
        this.password = rx.password();
        this.code = rx.code();
        this.codeLink = rx.codeLink();
        this.verifyPassword = rx.verifyPassword();
        this.channel = rx.channel();
        this.pid = rx.pid();
        this.clientId = rx.clientId();
        this.controlCode = rx.controlCode();
        this.msisdn = rx.msisdn();
        this.otp = rx.otp();
       }
        

      
        public String password;
        public String code;
        public String otp;
        public String clientId;
        public String codeLink;
        public String verifyPassword;
        public String channel;
        public String controlCode;
        public String msisdn;
        public long pid;


}
