/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.validators.HoptoolUserPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */

@ToString
public class ChangeProfilePasswordRequestObj {

    
    public ChangeProfilePasswordRequestObj(ChangeProfilePasswordRequest rx) {
        
      this.email = rx.email();
      this.password = rx.password();
      this.verifyPassword = rx.verifyPassword();
      this.corporateId = rx.corporateId();
    
    }
    
     public String email;
     public String password;
     public String verifyPassword;
     public String corporateId;
    
}
