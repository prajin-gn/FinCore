package bank.core;

import bank.exceptions.InsufficientBalanceException;

public class CurrentAccount extends Account{
    protected final double overdraft = 50_000;

    public CurrentAccount(String customerName){
        super(customerName, "Current Account", 0);
    }

    public CurrentAccount(String customerName, double balance){
        super(customerName, "Current Account", balance);
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
        addLog("Withdraw", amount, balance);
    }
}
