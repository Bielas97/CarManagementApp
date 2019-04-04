package com.validation;

import com.model.Engine;

import java.util.HashMap;
import java.util.Map;

public class WheelValidator {
    private Map<String, String> errors = new HashMap<>();
    public Map<String, String> validate(Engine engine) {

        if (engine == null) {
            errors.put("Wheel", "null");
        }

        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
