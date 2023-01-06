package com.patterns.gamma.behavioral;

/*
* Keep a memento of an object's state to return to that state. This is done by using a token/handle to
* represent the system state. The memento typically has no function of its own.
* */

class BankAccountToken {
    public int balance;

    public BankAccountToken(int balance) {
        this.balance = balance;
    }
}

class CurrentAccount {
    private int balance;

    public CurrentAccount(int balance) {
        this.balance = balance;
    }

    public BankAccountToken deposit(int amount) {
        balance += amount;
        return new BankAccountToken(balance);
    }

    public void restore(BankAccountToken token) {
        balance = token.balance;
    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "balance=" + balance +
                '}';
    }
}

class MementoDemo {
    public static void main(String[] args) {
        CurrentAccount account = new CurrentAccount(100);
        BankAccountToken token1 = account.deposit(50); // balance is now 150
        BankAccountToken token2 = account.deposit(25); // balance is not 175
        System.out.println(account);

        account.restore(token1);
        System.out.println(account);

        account.restore(token2);
        System.out.println(account);
    }
}
