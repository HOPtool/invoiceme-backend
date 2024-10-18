/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.invoiceme.auth.dto;

/**
 *
 * @author root
 */

public class UserLoginRequestObj{
    
public  UserLoginRequestObj(UserLoginRequest rx)
{   
     this.channel = rx.channel();
     this.pid = rx.pid();
     this.controlCode = rx.controlCode();
     this.password = rx.password();
     this.code = rx.code();
     this.codeLink = rx.codeLink();
     this.clientId = rx.clientId();
 }       


      public String channel;
      public long pid;
      public String controlCode;
      public String password;
      public String code;
      public String codeLink;
      public String clientId;
  
}
