package com.patterns.gamma.singletons;

import java.io.*;

class SimpleSingleton implements Serializable {
    // A private constructor prevents access
    private SimpleSingleton() {
    }

    private static final SimpleSingleton INSTANCE = new SimpleSingleton();

    public static SimpleSingleton getINSTANCE() {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    protected Object readResolve() {
        return INSTANCE;
    }
}

class SingletonDemo {

    static void saveToFile(SimpleSingleton singleton, String fileName) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            objOut.writeObject(singleton);
        }
    }

    static SimpleSingleton readFromFile(String fileName) throws Exception {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            return (SimpleSingleton) objIn.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleSingleton singleton1 = SimpleSingleton.getINSTANCE();
        singleton1.setValue(1000);

        /*  Issues with the private constructor method:
            1. Reflection
            2. Serialization */

        String fileName = "SimpleSingleton.bin";
        saveToFile(singleton1, fileName);

        singleton1.setValue(2000);

        SimpleSingleton singleton2 = readFromFile(fileName);

        // As we can see the values are different. We do therefore not have a Singleton
        System.out.println(singleton1 == singleton2);
        System.out.println(singleton1.getValue());
        System.out.println(singleton2.getValue());
    }
}
