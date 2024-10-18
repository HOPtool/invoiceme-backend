/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public record ErrorResponse(String code, ErrorData data, String message, 
        
        ErrorDetails error){
    
    
    //{"code":400,"data":null,"message":"error has occurred","error":{"id":"42451030-23f6-4138-aae5-244ae3bad481","handler":"invoice_actions","details":"invalid UUID length: 17","public_message":"unable to process your request, check input"}}
    
}
