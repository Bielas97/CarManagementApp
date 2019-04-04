package com.service;

import com.converter.CarJsonConverter;
import com.model.Car;
import com.model.Engine;
import com.model.enums.CarBodyType;
import com.model.enums.EngineType;
import com.model.enums.TyreType;
import com.service.enums.SortCriterium;
import com.service.enums.Stats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarStore {
    private Set<Car> cars;

    public Set<Car> getCars() {
        return cars;
    }

    public CarStore(List<String> filenames) {
        cars = new HashSet<>();
        for (String filename : filenames) {
            CarJsonConverter carJsonConverter = new CarJsonConverter(filename);
            cars.add(carJsonConverter.fromJson().orElseThrow(NegativeArraySizeException::new));
        }

        Car car = new Car();
    }

    public List<Car> sortByfield(SortCriterium criterium, boolean descending) {
        Stream<Car> carStream = cars.stream();
        switch (criterium) {
            case COMPONENTS_AMOUNT:
                carStream = carStream.sorted(Comparator.comparing(car -> car.getCarBody().getComponents().size()));
                break;
            case POWER:
                carStream = carStream.sorted(Comparator.comparing(car -> car.getEngine().getPower()));
                break;
            case TYRE_SIZE:
                carStream = carStream.sorted(Comparator.comparing(car -> car.getWheel().getSize()));
                break;
        }

        List<Car> sortedCars = carStream.collect(Collectors.toList());

        if (descending) {
            Collections.reverse(sortedCars);
        }
        return sortedCars;
    }

    public List<Car> findByCarBodyBetwwenPrice(CarBodyType carBodyType, BigDecimal min, BigDecimal max) {
        return cars.stream()
                .filter(car -> car.getCarBody().getType().equals(carBodyType))
                .filter(car -> car.getPrice().compareTo(min) >= 0 && car.getPrice().compareTo(max) <= 0)
                .collect(Collectors.toList());
    }

    public List<Car> findByEngineType(EngineType engineType) {
        return cars.stream()
                .filter(car -> car.getEngine().getType().equals(engineType))
                .sorted((c1, c2) -> c1.getModel().compareTo(c2.getModel()))
                .collect(Collectors.toList());
    }

    public void statistics(Stats stats) {
        switch (stats) {
            case MILEAGE:
                DoubleSummaryStatistics dss = cars.stream().collect(Collectors.summarizingDouble(Car::getMileage));
                System.out.println("MIN MILEAGE: " + dss.getMin());
                System.out.println("MAX MILEAGE: " + dss.getMax());
                System.out.println("AVG MILEAGE: " + dss.getAverage());
                break;
            case POWER:
                DoubleSummaryStatistics dss2 = cars.stream().map(Car::getEngine).collect(Collectors.summarizingDouble(Engine::getPower));
                System.out.println("MIN POWER: " + dss2.getMin());
                System.out.println("MAX POWER: " + dss2.getMax());
                System.out.println("AVG POWER: " + dss2.getAverage());
                break;
            case PRICE:
                BigDecimal totalPrice = cars
                        .stream()
                        .map(Car::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add/*(x1, x2) -> x1.add(x2)*/);
                System.out.println("MIN PRICE: " + cars.stream().min(Comparator.comparing(Car::getPrice)).orElseThrow(IllegalStateException::new).getPrice());
                System.out.println("MAX PRICE: " + cars.stream().max(Comparator.comparing(Car::getPrice)).orElseThrow(IllegalStateException::new).getPrice());
                System.out.println("AVG PRICE: " + totalPrice.divide(new BigDecimal(String.valueOf(cars.size())), 2, RoundingMode.CEILING));
                break;
        }
    }

    public Map<Car, Integer> getMileageForEachCar(){
        return cars.stream()
                .collect(Collectors.toMap(c -> c, Car::getMileage))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingLong(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                        )
                );
    }

    public Map<TyreType, List<Car>> getCarsOfTyreType(){
        return cars.stream()
                .collect(Collectors.groupingBy(c -> c.getWheel().getType()));
    }

    public List<Car> getCarsWithAllComponents(List<String> components){
        return cars.stream()
                .filter(car -> car.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    public void printCars (Collection<Car> carSet){
        for(Car car : carSet){
            System.out.println(car);
        }
    }

    @Override
    public String toString() {
        return "CarStore{" +
                "cars=" + cars +
                '}';
    }
}
