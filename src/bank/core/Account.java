package bank.core;

import java.util.ArrayList;
import bank.interfaces.BankOperations;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account implements BankOperations{
    static long count = 0;
    static final String BANK_NAME = "FinCore";
    private String accountNumber;
    private String customerName;
    private String accountType;
    protected double balance;
    private ArrayList<TransactionLogs> logs = new ArrayList<>();

    // Constructors
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

    // Banking methods
    public void deposit(double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("Enter an amount larger than 0");
        }
        balance += amount;
        addLog("Deposit", amount, 


balance);
    }

    abstract public void withdraw(double amount);

    public void transfer(Account to, double amount){
        this.withdraw(amount);
        to.deposit(amount);
    }

    public void balanceEnquiry(){
        System.out.println("Current Balance: " + balance);
    }

    // Getters
    public void display(){
        System.out.println("\nAccount details");
        System.out.println("Account number: " + accountNumber);
        System.out.println("Customer name: " + customerName);
        System.out.println("Account type: " + accountType);
        System.out.printf("Balance: %.2f\n\n", balance);
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    // Logs
    public void displayLogs(){
        System.out.println("Logs");
        System.out.printf("%-3s %-12s %-10s %-10s %-12s%n", "ID", "Action", "Amount", "Balance", "Time & Date");
        for(TransactionLogs t: logs){
            System.out.println(t.getLog());
        }
    }

    protected void addLog(String action, double amount, double newBalance){
        logs.add(0, new TransactionLogs(action, amount, balance));
    }

    public class TransactionLogs {
        static int count = 0;
        int tid;
        double amount, newBalance;
        String action, dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        TransactionLogs(String action, double amount, double newBalance){
            this.tid = count;
            this.action = action;
            this.amount = amount;
            this.newBalance = newBalance;
            dateTime = LocalDateTime.now().format(formatter);
            count++;
        }

        String getLog(){
            return String.format("%-3d %-12s %-10.2f %-10.2f %-12s", tid, action, amount, newBalance, dateTime);
        }
    }
}