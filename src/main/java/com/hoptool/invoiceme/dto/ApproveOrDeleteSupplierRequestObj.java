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
public class ApproveOrDeleteSupplierRequestObj
{
        
    public ApproveOrDeleteSupplierRequestObj(ApproveOrDeleteSupplierRequest rx)
    {
        this.tid = rx.tid();
        this.actionBy = rx.actionBy();
        this.actionByStr = rx.actionByStr();
    }


    public Long tid;
    public long actionBy;
    public String actionByStr;
    
    
}
