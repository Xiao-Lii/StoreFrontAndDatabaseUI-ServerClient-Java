package Users;


import java.util.ArrayList;

public abstract class User {
    private String email;
    private String password;
    private String displayName;
    private String accountType;
    private ArrayList<Order> listOfCustOrders;

    public User(String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.accountType = null;
    }

    public User() {
        this.email = null;
        this.password = null;
        this.displayName = null;
        this.accountType = null;
    }

    // THIS IS IMPORTANT WHEN DISPLAYING TO LISTVIEW TABLES
    @Override
    public String toString(){
        return String.format("%-30s\t - \t%-10s", this.getEmail(), this.getDisplayName());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Order> getListOfCustOrders(){return listOfCustOrders;}

}