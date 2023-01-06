package com.patterns.gamma.singletons;

import java.util.HashMap;

enum Subsystem {
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer {
    private static int instanceCount = 0;

    private Printer() {
        instanceCount++;
        System.out.println("Total of " + instanceCount + "instances created.");
    }

    private static HashMap<Subsystem, Printer> instances = new HashMap<>();

    // Lazy-loading
    public static Printer get(Subsystem subsystem) {
        if (instances.containsKey(subsystem)) {
            return instances.get(subsystem);
        }

        Printer instance = new Printer();
        instances.put(subsystem, instance);
        return instance;
    }
}

class MultitonDemo {
    public static void main(String[] args) {
        Printer primary = Printer.get(Subsystem.PRIMARY);
        Printer aux = Printer.get(Subsystem.AUXILIARY);
        Printer aux2 = Printer.get(Subsystem.AUXILIARY);
    }
}
