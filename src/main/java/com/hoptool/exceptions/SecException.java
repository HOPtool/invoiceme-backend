/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.exceptions;

/**
 *
 * @author taysayshaguy
 */
public class SecException extends Exception{
    
    String message;
    String code;
    
    public SecException(String message) {
        this.message = message;
    }
    
    public SecException(Exception e) {
        this.message = e.getMessage();
        super.setStackTrace(e.getStackTrace());
    }

    public SecException(String code,String message) {
        this.message = message;
        this.code = code;
    }

    
    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
    
    
    
}
