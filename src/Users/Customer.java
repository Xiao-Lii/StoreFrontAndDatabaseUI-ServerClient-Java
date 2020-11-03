package Users;

import Product.Product;

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
            throw new IllegalArgumentException("Error. Can't create a new order with an order already open");
        }
        for (Order o : this.listOfCustOrders){
            if (o.getIsFinalized() == false){
                throw new IllegalArgumentException("Error. Can't create a new order with an order already open");
            }
        }
        this.listOfCustOrders.add(new Order());
        this.anyOpenOrders = true;
    }

    public void addProductToCustomerOrder(Product product){
        for (Order o : this.listOfCustOrders){
            // WE WANT TO FIND THE UNFINALIZED ORDER
            if (o.getIsFinalized() == false){
                o.addProductToOrder(product);
            }
        }
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
