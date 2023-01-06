package com.patterns.gamma.singletons;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("Initializing");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            System.err.println("Failed to initialize StaticBlockSingleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
class StaticBlockSingletonDemo {
}
