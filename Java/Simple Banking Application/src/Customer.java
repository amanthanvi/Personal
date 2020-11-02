import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Customer {
    String customerID; //Any unique combination of alphanumeric characters
    String customerName;
    String customerAddress;
    double customerBalance;

    public Customer(String name, String id, String address, double balance) {
        customerID = id;
        customerName = name;
        customerAddress = address;
        customerBalance = balance;
    }

    public Customer() {

    }

    public static void CheckBalance(Customer currCust) {
        System.out.println("\nYour current balance is: " + currCust.customerBalance);
        System.out.println("Returning to main menu...\n");
    }

    public static void Deposit(Customer currCust) {
        System.out.println("Enter the amount to deposit: ");
        Scanner keyboard = new Scanner(System.in);
        double amountDeposit = keyboard.nextInt();
        currCust.customerBalance = currCust.customerBalance + amountDeposit;
        //Update Customer Database
        updateCurrCustDatabase(currCust);
        System.out.println("You now have: " + currCust.customerBalance);
        keyboard.close();
    }

    public static void Withdraw() {
        System.out.println("Enter the amount to withdraw: ");
    }

    public static void updateCurrCustDatabase(Customer cust) throws IOException {
        File customers = new File("C:\\Users\\amant\\IdeaProjects\\SimpleBankingApplication\\src\\customers");
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

        Scanner fileReader = new Scanner(customers).useDelimiter(":");
        FileWriter writer = new FileWriter(customers);

        //update ID - updated by employee
        String newID = cust.customerID;

        //update name - updated by employee
        String newName = cust.customerName;

        //update address - updated by customer or employee
        String newAddress = cust.customerAddress;

        //update balance - updated by customer via deposit, withdraw, etc.
        double newBal = cust.customerBalance;

        int indexInLine = 0;

        for (int line = 0; line < data.size(); line++) {
            String[] temp = data.get(line).split(":");
            String textOnCurrLine = temp[0] + ":" + temp[1] + ":" + temp[2] + ":" + temp[3];
            String separator =":";
            int sepPos = textOnCurrLine.indexOf(separator);
            System.out.println("Substring after separator = "+str.substring(sepPos + separator.length()));
            double currBal = Double.parseDouble(temp[3]);

            //update ID
            if (!newID.equalsIgnoreCase(temp[0])) {
                temp[0] = newID;
                /*
                String newLine = line.substring(0, 5) + "Abhishek" + line.substring(5);
                FileWriter writer = new FileWriter(file);
                writer.write(newLine);
                writer.close();
                * */
                writer.write(newID);
            }
            indexInLine++;

            //update name
            if (!newName.equalsIgnoreCase(temp[1])) {
                temp[1] = newName;
            }
            indexInLine++;

            //update address
            if (!newAddress.equalsIgnoreCase(temp[2])) {
                temp[2] = newAddress;
            }
            indexInLine++;

            //update balance
            if (newBal != currBal) {
                temp[3] = temp[3].valueOf(newBal);
            }
            indexInLine = 0;
        }
        fileReader.close();
        writer.close();
    }

    public static void createNewCust() {

    }
}
