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

public enum InvoiceTypesEnum {
  
    Credit_Note("2"),
    Commercial_Invoice("1"),
    // ...
    Debit_Note("3"),
    
    Factored_Invoice("4"),
    
    Statement_of_Account("5"),
    
    Purchase_Order("6"),
    
    
    Proforma_Invoice("7"),
    
    
    Consignment_Invoice("8"),
    
    Self_billed_Credit_Note("9"),
    
    Credit_Note_Request("10"),
    
    Invoice_Request("11"),
    
    Final_Settlement("12"),
    
    Bill_of_Lading("13"),
    
    Waybill("14"),
    
    Shipping_Instructions("15"),
    
    Certificate_of_Origin("16"),
    
    Customs_Declaration("17"),
    
    Packing_List("18"),
    
    Self_Billed_Invoice("0");
    
    
    private static final Map<String, InvoiceTypesEnum> BY_LABEL = new HashMap<>();
    
    static 
    {
        for (InvoiceTypesEnum e: values()) 
        {
          
            BY_LABEL.put(e.label, e);
        }
    }

    public final String label;

    private InvoiceTypesEnum(String label) {
        this.label = label;
    }
    
    
    public static boolean isValidName(String label) 
    {
      for (InvoiceTypesEnum e : values()) {
       
        if (e.name().equals(label) ) 
        {
            return true;
        }
       }
      return false;
    }
    public static InvoiceTypesEnum valueOfLabel(String label) 
    {
      for (InvoiceTypesEnum e : values()) {
        if (e.label.equals(label)) 
        {
            return e;
        }
       }
    return null;
   }
    
    public static boolean isValidLabel(String label) 
    {
      for (InvoiceTypesEnum e : values()) {
        if (e.label.equals(label)) 
        {
            return true;
        }
       }
    return false;
   }
   
}
