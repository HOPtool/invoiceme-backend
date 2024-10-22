/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author paycraftsystems-i
 */
public record FilterSuppliers(
        @NotBlank
        @Size(min=19,max=19, message="from date must be in the format yyyy-MM-dd HH:mm:ss")
        String fromDate,
        @NotBlank
        @Size(min=19,max=19, message="to date must be in the format yyyy-MM-dd HH:mm:ss")
        String toDate, 
        @NotBlank
        @Size(min=3, message="status must be valid either ALL, INACTICE, ACTIVE, APPROVED, DELETED")
        String status, 
        @NotNull
        @Min(1)
        int pageId,
        @NotNull
        @Min(1)
        int pageSize, 
        @NotNull
        @Min(1)
        long actionBy, 
        @NotBlank
        @Size(min=3, message="action by must be valid")
        String  actionByStr, 
        String businessId,
        String searchParam
        ) {
    
}
