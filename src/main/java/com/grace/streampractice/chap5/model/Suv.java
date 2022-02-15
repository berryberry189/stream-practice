package com.grace.streampractice.chap5.model;

public class Suv extends Car{
    public Suv(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println("Driving a Suv " + name + " from " + brand);
    }
}
