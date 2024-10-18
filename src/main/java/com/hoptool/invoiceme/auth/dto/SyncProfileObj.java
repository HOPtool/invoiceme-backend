/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class SyncProfileObj
{
    public SyncProfileObj(SyncProfile rx) {
        
       this.msisdn = rx.msisdn();
       this.channel = rx.channel();
       this.controlCode = rx.controlCode();
       this.codeLink = rx.codeLink();
       this.pid = rx.pid();
       this.verifyPassword = rx.verifyPassword();
       this.password = rx.verifyPassword();
       this.clientId = rx.clientId();
       this.code = rx.code();
    
    
    }
    
    
    public String msisdn;
    public String channel;
    public String controlCode;
    public String codeLink;
    public long pid;
    public String verifyPassword;
    public String password;
    public String clientId;
    public String code;
    
}
