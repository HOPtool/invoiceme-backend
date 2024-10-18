/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class SysLoginRequestObj
{

    public SysLoginRequestObj(SysLoginRequest rx) 
    {
        
        this.ux = rx.ux();
        this.iv = rx.iv();
        this.key = rx.key();
        //this.apiCustomerName = rx.customerName();
        //this.customerCode = rx.customerCode();
    
    
    }

    public String ux;
    public String iv;
    public String key;
    public String apiCustomerName;
    public String customerCode;
    
}
