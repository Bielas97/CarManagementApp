package com.model;


import java.math.BigDecimal;


public class Car {
    private String model;
    private BigDecimal price;
    private int mileage;
    private Engine engine;
    private Wheel wheel;
    private CarBody carBody;

    public Car() {
    }

    public Car(String model, BigDecimal price, int mileage, Engine engine, Wheel wheel, CarBody carBody) {
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.engine = engine;
        this.wheel = wheel;
        this.carBody = carBody;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    /*public Car(String filename) {
        CarJsonConverter carJsonConverter = new CarJsonConverter(filename);
        carJsonConverter.fromJson().ifPresent(car ->{
            this.model = car.getModel();
            this.price = car.getPrice();
            this.mileage = car.getMileage();
            this.engine = car.getEngine();
            this.wheel = car.getWheel();
            this.carBody = car.getCarBody();
        });
    }*/

    public void setModel(String model) {
        String regex = "[A-Z\\s]+";
        if(!model.matches(regex)){
            System.out.println("assinging default value AUDI");
            this.model = "AUDI";
        }
        this.model = model;
    }

    public void setPrice(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("assigning default value - 10");
            this.price = new BigDecimal(10);
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", engine=" + engine +
                ", wheel=" + wheel +
                ", carBody=" + carBody +
                '}';
    }
}
