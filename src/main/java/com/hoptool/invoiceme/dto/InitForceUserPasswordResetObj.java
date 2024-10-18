/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public class InitForceUserPasswordResetObj{

    
    public InitForceUserPasswordResetObj(InitForceUserPasswordReset rx)
    {
        
        this.email = rx.email();
        this.corporateId = rx.corporateId();
        
    }
    
        public String email;
        public String corporateId;
    
}
