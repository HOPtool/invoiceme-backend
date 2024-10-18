/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;

import lombok.ToString;

/**
 *
 * @author paycraftsystems-i
 */
@ToString
public class ResponseStatusHeaders {

    public ResponseStatusHeaders(long httpStatus, String ststusStr) {
        this.statusCode = httpStatus;
        this.statusDescription = ststusStr;
    }
    
    public long statusCode;
    public String statusDescription;
    
}
