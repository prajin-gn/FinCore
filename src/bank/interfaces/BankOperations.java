package bank.interfaces;

import bank.core.Account;

public interface BankOperations {
    public void deposit(double amount);
    public void withdraw(double amount);
    public void balanceEnquiry();
    public void transfer(Account to, double amount);
}
