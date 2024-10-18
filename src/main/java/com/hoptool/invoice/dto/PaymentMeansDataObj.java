/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public class PaymentMeansDataObj{

    public PaymentMeansDataObj(PaymentMeansData objx) {
        
        this.payment_means_code = objx.payment_means_code();
        this.payment_due_date = objx.payment_due_date();
    }
    
    
    public String payment_means_code;
    public String payment_due_date;
  
    
}
