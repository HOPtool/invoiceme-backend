/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class SysDataDTOObj
{
    public  SysDataDTOObj(SysDataDTO rx) {
        
        this.paramName = rx.paramName();
        this.paramValue = rx.paramValue();
        this.comments = rx.comments();
    
    }
    

public String paramName;
public String paramValue;
public String comments;



}
