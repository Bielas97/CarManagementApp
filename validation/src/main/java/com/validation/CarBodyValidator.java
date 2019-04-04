package com.validation;

import com.model.CarBody;

import java.util.HashMap;
import java.util.Map;

public class CarBodyValidator {
    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> validate(CarBody carBody) {

        if (carBody == null) {
            errors.put("carBody", "null");
        }

        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}
