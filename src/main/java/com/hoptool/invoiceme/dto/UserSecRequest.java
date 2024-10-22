/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;


import jakarta.json.Json;

/**
 *
 * @author paycraftsystems-i
 */
public record UserSecRequest(
   long tid,
   long pid,
   String code,
   String userCode,
   String msisdn,
   String codeLink,
   String vHash1,
   String vHash,
   String syncDate,
   String controlCode,
   String lastLoginDate,
   String modifiedDate)
{
    
  
    
}
