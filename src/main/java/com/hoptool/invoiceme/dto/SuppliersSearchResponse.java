/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import com.hoptool.invoiceme.entities.SuppliersLog;
import java.util.List;

/**
 *
 * @author paycraftsystems-i
 */
public record SuppliersSearchResponse(int code, String errorDesc, int rowCount, List<SuppliersLog> suppliers) {
    
}
