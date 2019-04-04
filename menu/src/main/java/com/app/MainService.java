package com.app;

import com.exception.MyException;
import com.model.Car;
import com.model.enums.CarBodyType;
import com.model.enums.EngineType;
import com.model.enums.TyreType;
import com.service.CarStore;
import com.service.UserService;
import com.service.enums.SortCriterium;
import com.service.enums.Stats;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainService {
    UserService userService;
    CarStore carStore;
    Scanner scanner = new Scanner(System.in);

    public MainService(List<String> filenames) {
        this.userService = new UserService();
        this.carStore = new CarStore(filenames);
    }

    public void mainMenu() {
        while (true) {
            printMenu();
            int option = userService.getInt("Choose Option");
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2();
                    break;
                case 3:
                    option3();
                    break;
                case 4:
                    option4();
                    break;
                case 5:
                    option5();
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    option7();
                    break;
                case 8:
                    option8();
                    break;
                default:
                    System.out.println("wWrong number");
            }
        }
    }

    private void option8() {
        List<String> components = chooseComponents();
        carStore.printCars(carStore.getCarsWithAllComponents(components));
    }

    private List<String> chooseComponents() {
        List<String> components = new ArrayList<>();
        String component = "";
        System.out.println("choose components");
        System.out.println("if ready write exit");
        while (true) {
            System.out.println("write next component: ");
            component = scanner.next();
            if (component.equalsIgnoreCase("exit")) {
                break;
            }
            components.add(component.toUpperCase());
        }
        return components;
    }

    private void option7() {
        Map<TyreType, List<Car>> mapa = carStore.getCarsOfTyreType();
        for (Map.Entry<TyreType, List<Car>> entry : mapa.entrySet()) {
            System.out.println("key : " + entry.getKey());
            System.out.println("value : " + entry.getValue());
            System.out.println("------------------------------");
        }
    }

    private void option6() {
        Map<Car, Integer> mapa = carStore.getMileageForEachCar();
        for (Map.Entry<Car, Integer> entry : mapa.entrySet()) {
            System.out.println("key : " + entry.getKey());
            System.out.println("value : " + entry.getValue());
            System.out.println("------------------------------");
        }
    }

    private void option5() {
        Stats stats = chooseStats();
        carStore.statistics(stats);
    }

    private Stats chooseStats() {
        for (int i = 1; i < Stats.values().length + 1; i++) {
            System.out.println(i + ". " + Stats.values()[i - 1]);
        }
        System.out.println("Choose Stats");
        int option = Integer.parseInt(scanner.next());
        switch (option) {
            case 1:
                return Stats.PRICE;
            case 2:
                return Stats.MILEAGE;
            case 3:
                return Stats.POWER;
            default:
                throw new MyException("You chose wrong number of stats");
        }
    }

    private void option4() {
        EngineType engineType = chooseEngineType();
        carStore.printCars(carStore.findByEngineType(engineType));
    }

    private EngineType chooseEngineType() {
        for (int i = 1; i < EngineType.values().length + 1; i++) {
            System.out.println(i + ". " + EngineType.values()[i - 1]);
        }
        System.out.println("Choose Engine type");
        int option = Integer.parseInt(scanner.next());
        switch (option) {
            case 1:
                return EngineType.DIESEL;
            case 2:
                return EngineType.GASOLINE;
            case 3:
                return EngineType.LPG;
            default:
                throw new MyException("You chose wrong number of engine type");
        }
    }

    private void option3() {
        CarBodyType carBodyType = chooseCarBodyType();
        System.out.println("write min price: ");
        BigDecimal min = scanner.nextBigDecimal();
        System.out.println("write max price: ");
        BigDecimal max = scanner.nextBigDecimal();
        carStore.printCars(carStore.findByCarBodyBetwwenPrice(carBodyType, min, max));
    }

    private CarBodyType chooseCarBodyType() {
        for (int i = 1; i < CarBodyType.values().length + 1; i++) {
            System.out.println(i + ". " + CarBodyType.values()[i - 1]);
        }
        System.out.println("Choose Car Body type");
        int option = Integer.parseInt(scanner.next());
        switch (option) {
            case 1:
                return CarBodyType.SEDAN;
            case 2:
                return CarBodyType.HATCHBACK;
            case 3:
                return CarBodyType.COMBI;
            default:
                throw new MyException("You chose wrong number of car body type");
        }
    }

    private void option2() {
        SortCriterium sortCriterium = chooseSortCriterium();
        System.out.println("descending? [y/n]");
        String descending = scanner.next();
        boolean desc = false;
        switch (descending) {
            case "y":
                desc = true;
                break;
            case "n":
                desc = false;
                break;
            default:
                throw new MyException("You didn answer correctly for question about descending");
        }
        carStore.printCars(carStore.sortByfield(sortCriterium, desc));
    }

    private void option1() {
        carStore.printCars(carStore.getCars());
    }

    private SortCriterium chooseSortCriterium() {
        for (int i = 1; i < SortCriterium.values().length + 1; i++) {
            System.out.println(i + ". " + SortCriterium.values()[i - 1]);
        }
        System.out.println("Choose sort criterium");
        int option = Integer.parseInt(scanner.next());
        switch (option) {
            case 1:
                return SortCriterium.COMPONENTS_AMOUNT;
            case 2:
                return SortCriterium.POWER;
            case 3:
                return SortCriterium.TYRE_SIZE;
            default:
                throw new MyException("You chose wrong number of sort cytierium");
        }
    }

    private void printMenu() {
        System.out.println("1 - show all cars");
        System.out.println("2 - sort by field");
        System.out.println("3 - find cars by carbody between price");
        System.out.println("4 - find car by engine type");
        System.out.println("5 - show statistics");
        System.out.println("6 - show mileage for each car");
        System.out.println("7 - show cars of tyre type");
        System.out.println("8 - show cars with given components");
    }
}
