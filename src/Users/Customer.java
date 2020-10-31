package Users;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Order> listOfCustOrders;


    public Customer(String email, String password, String displayName) {
        super(email, password, displayName);
    }
}
