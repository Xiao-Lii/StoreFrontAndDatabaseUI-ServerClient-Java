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

    public ArrayList<Order> getListOfCustOrders(){return listOfCustOrders;}
}
