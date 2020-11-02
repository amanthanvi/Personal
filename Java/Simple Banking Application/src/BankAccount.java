public class BankAccount {
    String customerName;
    String customerID; //Any unique combination of alphanumeric characters
    String customerAddress;
    double customerBalance;
	
    public BankAccount(String name, String id, String address, double balance){
        customerName = name;
        customerID = id;
        customerAddress = address;
        customerBalance = balance;
    }

}
