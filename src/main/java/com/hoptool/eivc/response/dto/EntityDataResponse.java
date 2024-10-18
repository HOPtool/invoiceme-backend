/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.eivc.response.dto;

import java.util.List;

/**
 *
 * @author paycraftsystems-i
 */
public record EntityDataResponse(String id, String reference, Object custom_settings, String created_at, String updated_at, List<EntityBusinessObject> businesses,  boolean is_active, String app_reference) {
  
    
}
