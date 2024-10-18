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
public class TinOrCACLookupObj
{
    public TinOrCACLookupObj(TinOrCACLookup rx) 
    {
        this.businessNo = rx.businessNo();
        this.validationType = rx.validationType();
        
    }
    
    public String validationType;
    public String businessNo;
    
}
