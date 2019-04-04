package com.app;

import com.service.CarStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> filenames = new ArrayList<>();
        filenames.add("car.json");
        filenames.add("car1.json");
        filenames.add("car2.json");


        MainService mainService = new MainService(filenames);
        mainService.mainMenu();
    }
}
