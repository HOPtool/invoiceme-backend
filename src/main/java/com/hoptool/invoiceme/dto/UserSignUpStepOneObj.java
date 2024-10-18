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
@ToString
public class UserSignUpStepOneObj
{
    public UserSignUpStepOneObj(UserSignUpStepOne rx){
        
        
        this.fullname = rx.fullname();
        this.companyEmail = rx.companyEmail();
        this.password = rx.password();
        this.corporateId = rx.corporateId();
    
}


    public String fullname;
    public String companyEmail;
    public String password;
    public String corporateId;

}