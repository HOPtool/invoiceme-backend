/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
public record Currency(
    String symbol,
    String name,
    String symbol_native,
    int decimal_digits,
    int rounding,
    String code,
    String name_plural
) {}