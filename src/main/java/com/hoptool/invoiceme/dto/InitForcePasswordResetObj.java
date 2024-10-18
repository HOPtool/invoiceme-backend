/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class InitForcePasswordResetObj{
    
    public InitForcePasswordResetObj(InitForcePasswordReset rx) {
   
        this.code = rx.code();
        this.channel = rx.channel();
        this.clientId = rx.clientId();
    }


    public String code;
    public String channel;
    public String clientId;

}
