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
import java.util.regex.Pattern;

public class E164PhoneNumberValidator implements ConstraintValidator<E164PhoneNumber, String> {

    private static final String E164_REGEX = "^\\+?[1-9]\\d{1,14}$";
    private final Pattern pattern = Pattern.compile(E164_REGEX);

    @Override
    public void initialize(E164PhoneNumber constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Consider using @NotNull if null values should be invalid
        }
        return pattern.matcher(value).matches();
    }
}
