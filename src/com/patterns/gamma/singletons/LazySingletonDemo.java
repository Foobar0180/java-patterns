package com.patterns.gamma.singletons;

class LazySingleton {
    private LazySingleton() {
        System.out.println("Initializing");
    }

    private static LazySingleton instance;

//    // Adding synchronized option can impact performance
//    public static synchronized LazySingleton getInstance() {
//        // This can cause problems where multiple threads are trying to instantiate multiple instances of this
//        // leading to race-conditions
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    // Double-check locking is out-dated
    public static LazySingleton getInstance() {
        // First check
        if (instance == null) {
            synchronized (LazySingleton.class) {
                // Second check
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}

class LazySingletonDemo {

}
