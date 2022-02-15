package com.grace.streampractice.chap5.model;

public class Van extends Car{
    public Van(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println("Driving a Van " + name + " from " + brand);
    }

}
