package Users;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Order> listOfCustOrders;


    public Customer(String email, String password, String displayName, ArrayList<Order> listOfCustOrders) {
        super(email, password, displayName);
        this.listOfCustOrders = listOfCustOrders;
    }

    public Customer() { super(); }

    public Customer(String email, String password, String displayName) {
        super(email, password, displayName);
    }

    @Override
    public String toString(){
        return String.format("%-30s\t - \t%-10s", this.getEmail(), this.getDisplayName());
    }

    public ArrayList<Order> getListOfCustOrders(){return listOfCustOrders;}
}
