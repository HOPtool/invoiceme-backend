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
public class FIRSMBSLoginObj
{
    public FIRSMBSLoginObj(FIRSMBSLogin rx) {
        
        
        this.email = rx.email();
        this.password = rx.password();
    }
    
    public String email;
    public String password;
}
