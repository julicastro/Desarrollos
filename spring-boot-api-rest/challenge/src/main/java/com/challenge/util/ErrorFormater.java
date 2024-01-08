package com.challenge.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorFormater {

    public static String formatError (BindingResult br){
        String formattedErrors = "";
        if (br.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError error : br.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return formattedErrors = String.join("\n", errors);
        }
        return formattedErrors;
    }


}
