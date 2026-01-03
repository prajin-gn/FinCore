package bank.demo;
import java.util.ArrayList;
import java.util.Scanner;
import bank.core.*;
import bank.exceptions.InvalidAccountException;

public class FinCore {
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;
        Account ac, fromAccount, toAccount;
        String accountNumber, toAccountNumber;
        double amount;
        System.out.println("Program by: Midhun Raj T(1MJ24IS052)\nHarshith MS (1MJ24IS025)\nGN Prajin (1MJ24IS022)\nLikhith M (1MJ24IS041)\nKT Vaibhav (1MJ24IS035)");

        do {
            System.out.println("\n\n====== FinCore ======");
            System.out.println("1. Open an account");
            System.out.println("2. Deposite");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Transfer Between Accounts");
            System.out.println("6. Get Transcation Logs");
            System.out.println("7. Exit");
            System.out.printf("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\nAccount Types:");
                    System.out.println("1. Savings account - min balance: Rs.1500");
                    System.out.println("2. Current account - over draft: Rs. 50000");
                    System.out.println("3. Premium Savings account - min balance: Rs. 0");
                    System.out.printf("Enter choice: ");
                    int type = sc.nextInt();
                    sc.nextLine();
                    String customerName;
                    double deposit;

                    try{
                        if (type == 1){
                            System.out.printf("Enter customer name: ");
                            customerName = sc.nextLine().trim();
                            System.out.printf("Enter initial deposit, min Rs. 1500: " );
                            deposit = sc.nextDouble();
                            sc.nextLine();
                            ac = new SavingsAccount(customerName, deposit);
                        } else if(type == 2){
                            System.out.printf("Enter customer name: ");
                            customerName = sc.nextLine().trim();
                            System.out.printf("Enter initial deposit (optional, enter 0 to skip): " );
                            deposit = sc.nextDouble();
                            sc.nextLine();
                            ac = new CurrentAccount(customerName, deposit);
                        } else {
                            System.out.printf("Enter customer name: ");
                            customerName = sc.nextLine().trim();
                            System.out.printf("Enter initial deposit (optional, enter 0 to skip): " );
                            deposit = sc.nextDouble();
                            ac = new PremiumSavingsAccount(customerName, deposit);
                        }

                        ac.display();
                        accounts.add(ac);
                    } catch(IllegalArgumentException e){
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 2:
                    System.out.printf("Enter account number: ");
                    accountNumber = sc.next();
                    try{
                        ac = getAccount(accountNumber);
                        System.out.printf("Enter amount: ");
                        amount = sc.nextDouble();
                        ac.deposit(amount);
                    } catch (Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.printf("Enter account number: ");
                    accountNumber = sc.next();
                    try{
                        ac = getAccount(accountNumber);
                        System.out.printf("Enter amount: ");
                        amount = sc.nextDouble();
                        ac.withdraw(amount);
                    } catch (Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.printf("Enter account number: ");
                    accountNumber = sc.next();
                    try{
                        ac = getAccount(accountNumber);
                        ac.balanceEnquiry();
                    } catch (Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    try{
                        System.out.printf("Enter form account number: ");
                        accountNumber = sc.next();
                        fromAccount = getAccount(accountNumber);

                        System.out.printf("Enter to account number: ");
                        toAccountNumber = sc.next();
                        toAccount = getAccount(toAccountNumber);

                        System.out.printf("Enter amount: ");
                        amount = sc.nextDouble();

                        fromAccount.transfer(toAccount, amount);
                    } catch (Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.printf("Enter account number: ");
                    accountNumber = sc.next();
                    try{
                        ac = getAccount(accountNumber);
                        ac.displayLogs();
                    } catch (Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;
            
                default:
                    break;
            }
        } while (ch != 7);

        sc.close();
    }

    static Account getAccount(String accountNumber) throws InvalidAccountException{
        for(Account ac : accounts){
            if (accountNumber.equals(ac.getAccountNumber())){
                return ac;
            }
        }
        throw new InvalidAccountException("Account not found!");
    }
}
