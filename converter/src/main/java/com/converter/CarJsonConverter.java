package com.converter;

import com.model.Car;

public class CarJsonConverter extends JsonConverter<Car> {
    public CarJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
