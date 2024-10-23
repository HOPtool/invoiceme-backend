/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.controllers;

import com.hoptool.invoiceme.auth.dto.ResetKeysRequest;
import com.hoptool.invoiceme.auth.dto.ResetResponse;
import com.hoptool.invoiceme.auth.dto.ResponseStatusHeaders;
import com.hoptool.invoiceme.auth.dto.SysLoginRequest;
import com.hoptool.invoiceme.auth.dto.SysLoginResponse;
import com.hoptool.resources.ErrorCodes;
import com.hoptool.service.AuthService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author paycraftsystems-i
 */

@ApplicationScoped
@Slf4j
public class ServiceAuthController {
    
    @Inject
    AuthService authService;
    
    
    public SysLoginResponse doSysLogin(@Valid final SysLoginRequest request) {
        
        try 
        {
            return authService.doSysLogin(request);
            
        } catch (Exception e) {
            
            
            log.error("Exception @@ doSysLogin ",e);
            
            return new SysLoginResponse(new ResponseStatusHeaders(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)+" - "+e.getMessage()),null, null, null, 0);
        
        }
        
    }
    
    
    public ResetResponse doReset(@Valid final ResetKeysRequest request) {
        
        try 
        {
            return authService.doReset(request);
            
        } catch (Exception e) {
            
            
            log.error("Exception @@ doReset ",e);
            
            return new ResetResponse(ErrorCodes.SYSTEM_ERROR, null, null, e.getMessage());
            
            //return new SysLoginResponse(new ResponseStatusHeaders(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)),null, null, null, 0);
        
        }
        
    }
    
    
    public ResetResponse doResend(@Valid final ResetKeysRequest request) {
        
        try 
        {
            return authService.doResend(request);
            
        } catch (Exception e) {
            
            
            log.error("Exception @@ doResend ",e);
            
           // return new ResetResponse(new ResponseStatusHeaders(ErrorCodes.SYSTEM_ERROR, ErrorCodes.doErroDesc(ErrorCodes.SYSTEM_ERROR)),null, null, null, 0);
        
             return new ResetResponse(ErrorCodes.SYSTEM_ERROR, null, null, e.getMessage());
            
        }
        
    }
    
    
}
