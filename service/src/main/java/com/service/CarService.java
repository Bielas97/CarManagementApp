package com.service;

import com.converter.CarJsonConverter;
import com.model.Car;

public class CarService {
    public Car loadCarFromFile(String filename){

        Car car = new Car();
        CarJsonConverter carJsonConverter = new CarJsonConverter(filename);
        carJsonConverter.fromJson().ifPresent(c -> {
            car.setModel(c.getModel());
            car.setCarBody(c.getCarBody());
            car.setEngine(c.getEngine());
            car.setMileage(c.getMileage());
            car.setPrice(c.getPrice());
            car.setWheel(c.getWheel());
        });
        return car;
    }
}
