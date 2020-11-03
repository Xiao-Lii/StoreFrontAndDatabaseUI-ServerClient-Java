package Users;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Order> listOfCustOrders;
    private boolean anyOpenOrders;
    private String accountType;

    public Customer() {
        super();
        this.listOfCustOrders = new ArrayList<>();
        this.anyOpenOrders = false;
        this.accountType = "Customer";
    }

    public Customer(String email, String password, String displayName) {
        super(email, password, displayName);
        this.listOfCustOrders = new ArrayList<>();
        this.anyOpenOrders = false;
        this.accountType = "Customer";
    }

    public void createOrder() throws IllegalArgumentException{
        if (anyOpenOrders == true){
            throw new IllegalArgumentException("Error. Can't create a new order with an order already open.");
        }
        if (this.listOfCustOrders.size() == 0) {
            this.listOfCustOrders.add(new Order());
            this.anyOpenOrders = true;          // CHANGE TO TRUE NOW WE HAVE OPENED AN ORDER
                                                // CHANGE TO FALSE WHEN FINALIZING THE ORDER
        }
    }

    public void finalizeOrder() throws IllegalArgumentException{

    }

    @Override
    public String toString(){
        return String.format("%-30s\t - \t%-10s", this.getEmail(), this.getDisplayName());
    }

    public ArrayList<Order> getListOfCustOrders(){return listOfCustOrders;}

    public boolean isAnyOpenOrders() {
        return anyOpenOrders;
    }

    public void setAnyOpenOrders(boolean anyOpenOrders) {
        this.anyOpenOrders = anyOpenOrders;
    }

    //public String getAccountType() {return accountType;}

    //public void setAccountType(String accountType) {this.accountType = accountType;}
}
