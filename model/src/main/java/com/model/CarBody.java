package com.model;

import com.model.enums.CarBodyColor;
import com.model.enums.CarBodyType;

import java.util.List;
import java.util.stream.Collectors;


public class CarBody {
    private CarBodyColor color;
    private CarBodyType type;
    private List<String> components;

    public void setComponents(List<String> components) {

        System.out.println("assingin only components with big letters");
        String regex = "[A-Z\\s]+";
        this.components = components.stream()
                .filter(component -> component.matches(regex))
                .collect(Collectors.toList());
    }

    public CarBody() {
    }

    @Override
    public String toString() {
        return "CarBody{" +
                "color=" + color +
                ", type=" + type +
                ", components=" + components +
                '}';
    }

    public CarBody(CarBodyColor color, CarBodyType type, List<String> components) {
        this.color = color;
        this.type = type;
        this.components = components;
    }

    public CarBodyColor getColor() {
        return color;
    }

    public CarBodyType getType() {
        return type;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setColor(CarBodyColor color) {
        this.color = color;
    }

    public void setType(CarBodyType type) {
        this.type = type;
    }
}
