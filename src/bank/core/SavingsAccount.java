package bank.core;

import bank.exceptions.InsufficientBalanceException;

public class SavingsAccount extends Account{
    protected final double minBalance;

    public SavingsAccount(String customerName, double balance){
        this(customerName, "Savings Account", balance, 1500);
    }

    public SavingsAccount(String customerName, String accountType, double balance, double minBalance){
        super(customerName, accountType, balance);
        this.minBalance = minBalance;
        if (balance < minBalance){
            throw new IllegalArgumentException("Initial deposite must be greater than Rs." + minBalance);
        }

    }

    @Override
    public void withdraw(double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("Enter an amount larger than 0");
        }

        if(this.balance - amount < minBalance){
            throw new InsufficientBalanceException("Insufficient Balance, minimum balance: Rs." + minBalance +", attempted balance: " + (this.balance-amount));
        }

        balance -= amount;
        addLog("Withdrew", amount, balance);
    }
}
