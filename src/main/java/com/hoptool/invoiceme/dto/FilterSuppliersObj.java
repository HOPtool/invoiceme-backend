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
public class FilterSuppliersObj
{
    public FilterSuppliersObj(FilterSuppliers fx) 
    {
         this.fromDate = fx.fromDate();
         this.toDate = fx.toDate();
         this.status = fx.status();
         this.actionBy = fx.actionBy();
         this.actionByStr = fx.actionByStr();
         this.searchParam = fx.searchParam();
         this.pageId = fx.pageId();
         this.pageSize = fx.pageSize();
         this.businessId = fx.businessId();
            
    }
     
    public String fromDate;
    public String toDate;
    public String status;
    public long actionBy;
    public String actionByStr;
    public String searchParam;
    public int pageId;
    public int pageSize;
    public String businessId;
        
}
