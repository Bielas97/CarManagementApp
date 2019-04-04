package com.validation;

import com.model.Engine;

import java.util.HashMap;
import java.util.Map;

public class EngineValidator {
    private Map<String, String> errors = new HashMap<>();
    public Map<String, String> validate(Engine engine) {

        if (engine == null) {
            errors.put("Engine", "null");
        }

        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
