/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

/**
 *
 * @author paycraftsystems-i
 */


public class CorporateCustomDataRequestObj{
   
    public CorporateCustomDataRequestObj(CorporateCustomDataRequest rx)
    {
        this.corporateId = rx.corporateId();
        this.domain = rx.domain();
        this.domainType = rx.domainType();
        this.colorCode = rx.colorCode();
        this.colorCode2 = rx.colorCode2();
        this.domainType = rx.domainType();
    
    
    }
    
            
            

    public String corporateId;
    public String domain;
    public String colorCode;
    public String colorCode2;
    public String domainType;




}
