/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;

import com.hoptool.invoiceme.auth.dto.*;
import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */

@ToString
public class ProfileSyncRequestObj {

    
    public ProfileSyncRequestObj(ProfileSyncRequest rx) {
        
        this.code = rx.code();
        this.codeLink = rx.codeLink();
        this.password = rx.password();
        this.verifyPassword = rx.verifyPassword();
        //this.pin = rx.pin();
        //this.verifyPin = rx.verifyPin();
        //this.newPassword = rx.newPassword();
        //this.newPin = rx.newPin();
        this.msisdn = rx.msisdn();
        
        this.channel = rx.channel();
        //this.userCode = rx.userCode();
        this.pid = rx.pid();
        this.controlCode = rx.controlCode();
        //this.principal = rx.principal();
        this.clientId = rx.clientId();
    }
    
     public String code;
     public String codeLink;
     public String password;
     public String verifyPassword;
     public String pin;
     public String verifyPin;
     public String newPassword;
     public String newPin;
     public String msisdn;
     public String channel;
     public long pid;
     public String controlCode;
     //public String userCode;
     //public String principal;
     public String clientId;
    
}
