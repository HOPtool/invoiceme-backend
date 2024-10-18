/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import com.google.gson.Gson;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */
@Slf4j
@ToString()
public class ErrorDataObj
{
    
    public ErrorDataObj(String data)
    {
        log.info(" ErrorDataObj data = " +  data);
        ErrorData rx = new Gson().fromJson(data, ErrorData.class);
        this.code = rx.code();
        this.data = rx.data();
        this.error = rx.error();
        this.message = rx.message();
    }
    
 
    public String code;
    public Object data;
    public String message;
    public ErrorDetails error;
    
}
