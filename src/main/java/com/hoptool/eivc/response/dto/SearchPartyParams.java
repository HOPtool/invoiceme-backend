/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.eivc.response.dto;

/**
 *
 * @author paycraftsystems-i
 */
public record SearchPartyParams(int size, int page, String sort_by, boolean sort_direction_desc, String party_name) {
    
}
