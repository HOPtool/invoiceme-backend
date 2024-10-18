/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

//import jakarta.json.bind.JsonbBuilder;

import com.google.gson.Gson;


/**
 *
 * @author paycraftsystems-i
 */
public class ErrorResponseObj
{

    public ErrorResponseObj(String obj) {
        
        ErrorResponse objx = new Gson().fromJson(obj, ErrorResponse.class);
        this.code = objx.code();
        this.data = objx.data();
        this.error = objx.error();
        this.message = objx.message();
    }
    
    
    public ErrorResponseObj(ErrorResponse objx) {
        
        this.code = objx.code();
        this.data = objx.data();
        this.error = objx.error();
        this.message = objx.message();
    }
    
    
    public String code;
    public ErrorData data;
    public String message;
    public ErrorDetails error;
    
    
    //{"code":400,"data":null,"message":"error has occurred","error":{"id":"42451030-23f6-4138-aae5-244ae3bad481","handler":"invoice_actions","details":"invalid UUID length: 17","public_message":"unable to process your request, check input"}}
    
}
