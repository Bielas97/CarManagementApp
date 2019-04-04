package com.model;

import com.model.enums.EngineType;


public class Engine {
    private EngineType type;
    private double power;

    public void setPower(double power) {
        if(power <0){
            System.out.println("assigning default value - 10");
            this.power = 10;
        }
        this.power = power;
    }

    public Engine(EngineType type, double power) {
        this.type = type;
        this.power = power;
    }

    public Engine() {
    }

    public EngineType getType() {
        return type;
    }

    public void setType(EngineType type) {
        this.type = type;
    }

    public double getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "type=" + type +
                ", power=" + power +
                '}';
    }
}
