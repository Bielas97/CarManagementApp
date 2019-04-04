package com.validation;

import com.model.Car;

import java.util.HashMap;
import java.util.Map;

public class CarValidator {
    private Map<String, String> errors = new HashMap<>();

    public Map<String, String> validate(Car car) {

        if (car == null) {
            errors.put("car", "null");
        }

        if (!isModelValid(car)) {
            errors.put("model", "not valid");
        }

        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private boolean isModelValid(Car car) {
        return car.getModel() != null && car.getModel().matches("[A-Z ]+");
    }

}
