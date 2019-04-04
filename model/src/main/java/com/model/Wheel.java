package com.model;

import com.model.enums.TyreType;

public class Wheel {
    private String model;
    private int size;
    private TyreType type;

    public void setModel(String model) {
        String regex = "[A-Z\\s]+";
        if (!model.matches(regex)) {
            System.out.println("assinging default value PIRELLI");
            this.model = "PIRELLI";
        }
        this.model = model;
    }

    public void setSize(int size) {
        if (size < 0) {
            System.out.println("assinging default value - 10");
            this.size = 10;
        }
        this.size = size;
    }

    public Wheel() {
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "model='" + model + '\'' +
                ", size=" + size +
                ", type=" + type +
                '}';
    }

    public Wheel(String model, int size, TyreType type) {
        this.model = model;
        this.size = size;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public int getSize() {
        return size;
    }

    public TyreType getType() {
        return type;
    }

    public void setType(TyreType type) {
        this.type = type;
    }
}
