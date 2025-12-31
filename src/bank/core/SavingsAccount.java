package bank.core;

import bank.exceptions.InsufficientBalanceException;

public class SavingsAccount extends Account{
    public SavingsAccount(String customerName, double balance, double minBalance){
        super(customerName, "Savings Account", balance, minBalance);

        if (balance < minBalance){
            throw new IllegalArgumentException("Initial deposite must be greater than Rs." + minBalance);
        }

    }

    public SavingsAccount(String customerName, String accountType, double balance, double minBalance){
        super(customerName, accountType, balance, minBalance);

        if (balance < minBalance){
            throw new IllegalArgumentException("Initial deposite must be greater than Rs." + minBalance);
        }

    }

    @Override
    public void withdraw(double amount){
        if(this.balance - amount < minBalance){
            throw new InsufficientBalanceException("Insufficient Balance, minimum balance: Rs." + minBalance +", attempted balance: " + (this.balance-amount));
        }

        super.withdraw(amount);
    }
}
