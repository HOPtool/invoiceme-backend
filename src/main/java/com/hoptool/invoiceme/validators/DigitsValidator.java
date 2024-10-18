/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hoptool.invoiceme.validators;

/**
 *
 * @author paycraftsystems-i
 */
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DigitsValidator implements ConstraintValidator<DigitsOnly, String> {

    @Override
    public void initialize(DigitsOnly constraintAnnotation) {
        // Initialization code, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Consider empty valid; change this based on your requirements
        }
        return value.matches("\\d+"); // Regular expression for digits only
    }
}
