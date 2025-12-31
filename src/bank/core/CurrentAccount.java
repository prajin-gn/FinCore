package bank.core;

import bank.exceptions.InsufficientBalanceException;

public class CurrentAccount extends Account{

    CurrentAccount(String customerName, double balance, double overdraft){
        super(customerName, "Current Account", balance, (-overdraft));
    }

    @Override
    public void withdraw(double amount){
        if(this.balance + minBalance - amount < 0){
            throw new InsufficientBalanceException("Insufficient Balance, Permitted overdraft: Rs." + minBalance + ", attempted balance: " + (this.balance-amount));
        }

        super.withdraw(amount);
    }
}
