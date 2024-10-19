/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.auth.dto;

import lombok.ToString;

/**
 *
 * @author root
 */

@ToString
public class ChangeProfilePasswordRequestObj
{

    public ChangeProfilePasswordRequestObj(ChangeProfilePasswordRequest rx) {
        
        
         
        this.code = rx.code();
        this.codeLink = rx.codeLink();
        this.channel = rx.channel();
        this.pid = rx.pid();
        this.controlCode = rx.controlCode();
        this.clientId = rx.clientId();
        this.password = rx.password();
        this.newPassword = rx.newPassword();
    
    }

    
      
    
     public String code;
     public String codeLink;
     public String channel;
     public String newPassword;
     public String password;
     public long pid;
     public String controlCode;
     public String clientId;


}
