package com.patterns.gamma.behavioral;

/*
* A no-op object that conforms to the required interface, satisfying a dependency requirement of
* some other object i.e.: When a component-A uses component-B, it typically assumes component-B is non-null.
* */

interface Log {
    void info(String message);
    void warning(String message);
}

class ConsoleLog implements Log {
    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void warning(String message) {
        System.out.println("WARNING: " + message);
    }
}

final class NullLog implements Log {
    @Override
    public void info(String message) {

    }

    @Override
    public void warning(String message) {

    }
}

class SavingsAccount {
    private Log log;
    private int balance;

    public SavingsAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        balance += amount;

        log.info("Deposited " + amount);
    }
}

class NullObjectDemo {
    public static void main(String[] args) {
//        ConsoleLog log = new ConsoleLog();
//        SavingsAccount account = new SavingsAccount(null); // This will throw a null reference exception
//        SavingsAccount account = new SavingsAccount(log);

        NullLog log = new NullLog();
        SavingsAccount account = new SavingsAccount(log);

        account.deposit(100);

    }
}
