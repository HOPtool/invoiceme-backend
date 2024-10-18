/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */
@ToString()
public class UserLoginObj
{
    public UserLoginObj(UserLogin rx) 
    {
        this.password = rx.password();
        this.email = rx.email();
        this.corporateId = rx.corporateId();
    }
    
    
    public String email;
    public String password;
    public String corporateId;
    
    
}
