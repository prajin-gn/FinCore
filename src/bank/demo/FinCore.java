package bank.demo;
import java.util.ArrayList;
import java.util.Scanner;
import bank.core.*;

public class FinCore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        int ch;

        do {
            System.out.println("\n\n====== FinCore ======");
            System.out.println("1. Open an account");
            System.out.println("2. Deposite");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Transfer Between Accounts");
            System.out.println("6. Exit");
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
                    Account newAc;
                    String customerName;
                    double deposit;

                    try{
                        if (type == 1){
                            System.out.printf("Enter customer name: ");
                            customerName = sc.nextLine().trim();
                            System.out.printf("Enter initial deposit, min Rs. 1500: " );
                            deposit = sc.nextDouble();
                            sc.nextLine();
                            newAc = new SavingsAccount(customerName, deposit);
                        } else if(type == 2){
                            System.out.printf("Enter customer name: ");
                            customerName = sc.nextLine().trim();
                            System.out.printf("Enter initial deposit (optional, enter 0 to skip): " );
                            deposit = sc.nextDouble();
                            sc.nextLine();
                            newAc = new CurrentAccount(customerName, deposit);
                        } else {
                            System.out.printf("Enter customer name: ");
                            customerName = sc.nextLine().trim();
                            System.out.printf("Enter initial deposit (optional, enter 0 to skip): " );
                            deposit = sc.nextDouble();
                            newAc = new PremiumSavingsAccount(customerName, deposit);
                        }

                        System.out.println("Account created with account number: " + newAc.getAccountNumber());
                        accounts.add(newAc);
                    } catch(IllegalArgumentException e){
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 2:
                    
                    break;

                case 3:
                    
                    break;

                case 4:
                    
                    break;

                case 5:
                    
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;
            
                default:
                    break;
            }
        } while (ch != 6);

        sc.close();
    }
}
