package bank.core;

import bank.exceptions.InsufficientBalanceException;

public class CurrentAccount extends Account{
    double overdraft;

    CurrentAccount(String customerName, double balance, double overdraft){
        super(customerName, "Current Account", balance);
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("Enter an amount larger than 0");
        }

        if(this.balance + overdraft - amount < 0){
            throw new InsufficientBalanceException("Insufficient Balance, Permitted overdraft: Rs." + overdraft + ", attempted balance: " + (this.balance-amount));
        }

        balance -= amount;
        addLog("Withdrew", amount, balance);
    }
}
