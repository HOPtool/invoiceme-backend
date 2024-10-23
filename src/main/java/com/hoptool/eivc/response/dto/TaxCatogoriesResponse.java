/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.eivc.response.dto;

import com.hoptool.invoice.dto.TaxCategoriesData;
import java.util.List;

/**
 *
 * @author paycraftsystems-i
 */
public record TaxCatogoriesResponse(int code, List<TaxCategoriesData> data) {
    
}
