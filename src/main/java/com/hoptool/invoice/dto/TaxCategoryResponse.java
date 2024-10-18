/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import java.util.List;

/**
 *
 * @author paycraftsystems-i
 */
public record TaxCategoryResponse(int code, List<TaxCategories> data) {
    
}
