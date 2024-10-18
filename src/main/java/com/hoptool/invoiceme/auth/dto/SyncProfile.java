/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.auth.dto;

/**
 *
 * @author paycraftsystems-i
 */
public record SyncProfile(String msisdn, String channel, String controlCode, String codeLink, long pid, String verifyPassword, String password, String clientId, String code) {
    
}
