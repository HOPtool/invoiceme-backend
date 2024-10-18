package com.hoptool.invoiceme.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.ToString;


/*
   enabling records
*/
public record ResetKeysRequest(

        @NotBlank
        @Email
        // @NotNull
        @Size(min = 3)
        String ux


) {
}
