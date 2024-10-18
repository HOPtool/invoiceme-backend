/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.resources;

/**
 *
 * @author taysayshaguy
 */
public class ServiceException extends Exception{
    
    String message;
    int code;
    
    public ServiceException(String message) {
        this.message = message;
    }
    
    public ServiceException(Exception e) {
        this.message = e.getMessage();
        super.setStackTrace(e.getStackTrace());
    }

    public ServiceException(int code,String message) {
        this.message = message;
        this.code = code;
    }

    
    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
    
    
    
}
