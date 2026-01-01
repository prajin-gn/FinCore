package bank.core;

public class PremiumSavingsAccount extends SavingsAccount{
    public PremiumSavingsAccount(String customerName, double balance){
        super(customerName, "Premium Savings Account", balance, 0);
    }
}
