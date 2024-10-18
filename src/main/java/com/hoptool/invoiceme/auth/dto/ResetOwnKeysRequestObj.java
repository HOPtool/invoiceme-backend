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
public class ResetOwnKeysRequestObj {

    
    public ResetOwnKeysRequestObj(ResetOwnKeysRequest rx) {
        
        this.ux = rx.ux();
        this.iv = rx.iv();
        this.key = rx.key();
    }
    
    public String ux;
    public String iv;
    public String key;
    
}
