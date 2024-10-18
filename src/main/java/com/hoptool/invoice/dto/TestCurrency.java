/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoice.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author paycraftsystems-i
 */
public class TestCurrency {
    
     static String resp = "{ \n" +
"\n" +
"  \"code\": 200, \n" +
"\n" +
"  \"data\": { \n" +
"\n" +
"    \"AED\": { \n" +
"\n" +
"      \"symbol\": \"AED\", \n" +
"\n" +
"      \"name\": \"United Arab Emirates Dirham\", \n" +
"\n" +
"      \"symbol_native\": \"د.إ.‏\", \n" +
"\n" +
"      \"decimal_digits\": 2, \n" +
"\n" +
"      \"rounding\": 0, \n" +
"\n" +
"      \"code\": \"AED\", \n" +
"\n" +
"      \"name_plural\": \"UAE dirhams\" \n" +
"\n" +
"    }, \n" +
"\n" +
"    \"AFN\": { \n" +
"\n" +
"      \"symbol\": \"Af\", \n" +
"\n" +
"      \"name\": \"Afghan Afghani\", \n" +
"\n" +
"      \"symbol_native\": \"؋\", \n" +
"\n" +
"      \"decimal_digits\": 0, \n" +
"\n" +
"      \"rounding\": 0, \n" +
"\n" +
"      \"code\": \"AFN\", \n" +
"\n" +
"      \"name_plural\": \"Afghan Afghanis\" \n" +
"\n" +
"    }, \n" +
"\n" +
"    \"ZMK\": { \n" +
"\n" +
"      \"symbol\": \"ZK\", \n" +
"\n" +
"      \"name\": \"Zambian Kwacha\", \n" +
"\n" +
"      \"symbol_native\": \"ZK\", \n" +
"\n" +
"      \"decimal_digits\": 0, \n" +
"\n" +
"      \"rounding\": 0, \n" +
"\n" +
"      \"code\": \"ZMK\", \n" +
"\n" +
"      \"name_plural\": \"Zambian kwachas\" \n" +
"\n" +
"    } \n" +
"\n" +
"  } \n" +
"\n" +
"} ";
    
    
    public static void main(String[] args) {
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response response = objectMapper.readValue(resp, Response.class);
            System.out.println(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
    }
    
}
