/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

/**
 *
 * @author paycraftsystems-i
 */
import java.util.Map;

public record Data(
    Map<String, Currency> currencies
) {}
