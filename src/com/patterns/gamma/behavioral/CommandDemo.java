package com.patterns.gamma.behavioral;

/*
* We use the command pattern, because, ordinary Java statements are perishable i.e.: they cannot undo a
* field assignment or directly serialize a sequence of actions. Or, when we want an object that represents an
* operation. The command contains all the information necessary for the action to be taken.
*/

import com.google.common.collect.Lists;

import java.util.List;

class BankAccount {
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", the balance is now " + balance);
    }

    public Boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ", the balance is now " + balance);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command {
    void handle();
    void undo();
}

class BankAccountCommand implements Command {
    private BankAccount account;
    public enum Action {
        DEPOSIT, WITHDRAW
    }
    private Action action;
    private int amount;
    private Boolean success = false;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void handle() {
        switch (action) {
            case DEPOSIT:
                success = true;
                account.deposit(amount);
                break;
            case WITHDRAW:
                success = account.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {
        if (!success) return;
        switch (action) {
            case DEPOSIT:
                account.withdraw(amount);
                break;
            case WITHDRAW:
                account.deposit(amount);
                break;
        }
    }
}

class CommandDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        System.out.println(account);

        List<BankAccountCommand> commands = List.of(
                new BankAccountCommand(account, BankAccountCommand.Action.DEPOSIT, 1500),
                new BankAccountCommand(account, BankAccountCommand.Action.WITHDRAW, 1000)
        );

        for (BankAccountCommand command : commands) {
            command.handle();
            System.out.println(account);
        }

        for (BankAccountCommand command : Lists.reverse(commands)) {
            command.undo();
            System.out.println(account);
        }
    }
}
