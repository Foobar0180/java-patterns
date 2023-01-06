package com.patterns.gamma.structural;

interface Driveable {
    void drive();
}

class Driver {
    public int age;

    public Driver(int age) {
        this.age = age;
    }
}

class Car implements Driveable {
    protected Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven");
    }
}

class CarProxy extends Car {
    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if (driver.age >= 18) {
            super.drive();
        }
        else {
            System.out.println("Driver to young to drive");
        }
    }
}

class ProtectionProxyDemo {
    public static void main(String[] args) {
//        Car car = new Car(new Driver(12));
        Car car = new CarProxy(new Driver(12));
        car.drive();
    }
}
