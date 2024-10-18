/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.hoptool.invoiceme.enumz;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paycraftsystems-i
 */

public enum InvoiceCategoriesEnum {
    CASH_2("2"),
    AIRTIME_1("1"),
    // ...
    DATA_3("3"),
    
    NONE_0("0");
    
    private static final Map<String, InvoiceCategoriesEnum> BY_LABEL = new HashMap<>();
    
    static 
    {
        for (InvoiceCategoriesEnum e: values()) 
        {
          
            BY_LABEL.put(e.label, e);
        }
    }

    public final String label;

    private InvoiceCategoriesEnum(String label) {
        this.label = label;
    }
    
    
    public static boolean isValidName(String label) 
    {
      for (InvoiceCategoriesEnum e : values()) {
       
        if (e.name().equals(label) ) 
        {
            return true;
        }
       }
      return false;
    }
    public static InvoiceCategoriesEnum valueOfLabel(String label) 
    {
      for (InvoiceCategoriesEnum e : values()) {
        if (e.label.equals(label)) 
        {
            return e;
        }
       }
    return null;
   }
    
    public static boolean isValidLabel(String label) 
    {
      for (InvoiceCategoriesEnum e : values()) {
        if (e.label.equals(label)) 
        {
            return true;
        }
       }
    return false;
   }
   
}
