package bank.core;
import java.util.ArrayList;

public abstract class Account {
    static long count = 0;
    static final String BANK_NAME = "FinCore";
    private String accountNumber;
    private String customerName;
    private String accountType;
    protected double balance;
    private ArrayList<TransactionLogs> logs = new ArrayList<>();


    public Account(){
        this.accountNumber = BANK_NAME + Account.count;
        this.customerName = "Unknown";
        this.accountType = "Generic Account";
        this.balance = 0;
        Account.count++;
    }

    public Account(String customerName, String accountType){
        this.accountNumber = BANK_NAME + Account.count;
        this.customerName = customerName;
        this.accountType = accountType;
        this.balance = 0;
        Account.count++;
    }

    public Account(String customerName, String accountType, double balance){
        this.accountNumber = BANK_NAME + Account.count;
        this.customerName = customerName;
        this.accountType = accountType;
        this.balance = balance;
        Account.count++;
    }

    public void deposit(double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("Enter an amount larger than 0");
        }
        balance += amount;
        addLog("Deposited", amount, balance);
    }

    abstract public void withdraw(double amount);

    public void display(){
        System.out.println("\nAccount details:");
        System.out.println("Account number: " + accountNumber);
        System.out.println("Customer name: " + customerName);
        System.out.println("Account type: " + accountType);
        System.out.printf("Balance: %.2f\n\n", balance);
    }

    public void displayLogs(){
        System.out.println("Logs: ");
        System.out.printf(("ID\tAction\tAmount\tBalance\n"));
        for(TransactionLogs t: logs){
            System.out.println(t.getLog());
        }
    }

    public void addLog(String action, double amount, double newBalance){
        logs.add(0, new TransactionLogs(action, amount, balance));
    }

    public class TransactionLogs {
        static int count = 0;
        int tid;
        double amount, newBalance;
        String action;

        TransactionLogs(String action, double amount, double newBalance){
            this.tid = count;
            this.action = action;
            this.amount = amount;
            this.newBalance = newBalance;
            count++;
        }

        String getLog(){
            return String.format("%d\t%s\t%.2f\t%.2f", tid, action, amount, newBalance);
        }
    }
}