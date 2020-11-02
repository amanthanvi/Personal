import java.util.*;
import java.io.*;


public class BankingApp {
    public static Customer tempCust = new Customer();

    //Customer side, want to make an employee side application
    public static void main(String[] args) {
        Welcome();
    }

    //Enters into program and checks if you are employee or customer
    public static void Welcome() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to Aman Banking International! Are you an account-holder (1) or an employee (2)?");
        int chooseMode = keyboard.nextInt();

        //Customer
        if (chooseMode == 1) {
            Customer();
        }
        //Employee
        else {
            Employee();


        }

        keyboard.close();
    }

    //add sign up later

    public static void Employee() {
        File employees = new File("employees.txt");
        Scanner keyboard = new Scanner(System.in);
        String employeeID;
        System.out.println("Please enter your unique employee ID: ");
        employeeID = keyboard.nextLine();
        //Take employee ID and check against a text file and return which employee is entering.
        //ReadFile(employees);
        //Can manage accounts and create new accounts
        keyboard.close();


    }

    public static void Customer() {
        String currCustID = checkCustomer();
        if (currCustID.equalsIgnoreCase("")) {
            System.out.println("Customer not found! Exiting system...");
            System.exit(1);
        }
        CustomerMenu(currCustID);
        //Check balance
        //Withdraw
        //Deposit


    }

    public static String checkCustomer() {
        File customers = new File("C:\\Users\\amant\\IdeaProjects\\SimpleBankingApplication\\src\\customers");
        Scanner keyboard = new Scanner(System.in);
        String customerID;
        System.out.println("Please enter your unique customer ID: ");
        customerID = keyboard.nextLine();
        //validate that they are customer
        ArrayList<String> data = new ArrayList<String>();
        try {
            Scanner fr = new Scanner(customers);
            while (fr.hasNextLine()) {
                data.add(fr.nextLine());
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int line = 0; line < data.size(); line++) {
            //Array of what is on current line
            String[] temp = data.get(line).split(":");
            if (temp[0].equalsIgnoreCase(customerID)) {
                double bal = Double.parseDouble(temp[3]);
                tempCust = new Customer(temp[0], temp[1], temp[2], bal);
                return temp[1];
            }
        }
        keyboard.close();
        return "";
    }

    //Introduce customer into customer functionalities via customer menu
    public static void CustomerMenu(String name) {
        Scanner keyboard = new Scanner(System.in);
        int menuSelect = 0;
        do {
            System.out.print("Welcome " + name + "!" + "\n~~~~~ B A N K  M E N U ~~~~~~\n1. Check Balance\n2. Deposit\n"
                    + "3. Withdraw\n4. Exit\nSelect your option: ");
            menuSelect = keyboard.nextInt();
            if (menuSelect == 1) {
                tempCust.CheckBalance(tempCust);
            } else if (menuSelect == 2) {
                tempCust.Deposit(tempCust);
            } else if (menuSelect == 3) {
                tempCust.Withdraw();
            } else if (menuSelect == 4) {
                System.out.println("Thank you for using Aman Banking International!");
            } else {
                System.out.println("\nPlease enter a valid menu option.\n");
            }
        } while (menuSelect != 4);
        keyboard.close();
    }


}
