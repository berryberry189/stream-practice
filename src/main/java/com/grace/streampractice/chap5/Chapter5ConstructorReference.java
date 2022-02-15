package com.grace.streampractice.chap5;

import com.grace.streampractice.chap4.model.User;
import com.grace.streampractice.chap5.model.Car;
import com.grace.streampractice.chap5.model.Sedan;
import com.grace.streampractice.chap5.model.Suv;
import com.grace.streampractice.chap5.model.Van;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Chapter5ConstructorReference {

    public static void main(String[] args) {
        Map<String, BiFunction<String, String, Car>> carTypeToConstructorMap = new HashMap<>();
        carTypeToConstructorMap.put("sedan", Sedan::new);
        carTypeToConstructorMap.put("suv", Suv::new);
        carTypeToConstructorMap.put("van", Van::new);

        User alice = new User(3, "Alice");
        BiFunction<Integer, String, User> userCreator = User::new;
        User charlie = userCreator.apply(3, "charlie");
        System.out.println(charlie);

        String[][] inputs = new String[][]{
                {"sedan", "Sonata", "Hyundai"},
                {"van", "Sienna", "Toyota"},
                {"sedan", "Model S", "Tesla"},
                {"suv", "Sorento", "Kia"}
        };

        List<Car> cars =  new ArrayList<>();
        for (String[] input : inputs) {
            String carType = input[0];
            String name = input[1];
            String brand = input[2];

            cars.add(carTypeToConstructorMap.get(carType).apply(name, brand));
        }

        for (Car car : cars) {
            car.drive();
        }
    }
}
