/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.eivc.response.dto;

/**
 *
 * @author paycraftsystems-i
 */
public record EntityBusinessObject(String id, String reference, Object custom_settings, String created_at, String updated_at, String tin, String sector, String annual_turnover, boolean support_peppol, String notification_channels, String erp_system, String irn_template, boolean is_active) {
    
    /*
    "id": "e3e65222-f03a-4990-84a8-e50ab4aa8923",
                "reference": "Test-Reference",
                "custom_settings": null,
                "created_at": "2024-09-24T11:19:16.621541Z",
                "updated_at": "2024-09-24T11:19:16.621541Z",
                "tin": "***************",
                "sector": "Engineering and Drilling",
                "annual_turnover": "1 million - 50 million",
                "support_peppol": true,
                "notification_channels": "self, email, sms",
                "erp_system": "HHP-INT",
                "irn_template": "{{invoice_id(e.g:INV00XXX)}}-E88AF03A-{{YYYYMMDD(e.g:20240930)}}",
                "is_active": true
    */
    
}
