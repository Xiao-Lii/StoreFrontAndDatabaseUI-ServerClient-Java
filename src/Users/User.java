package Users;


public abstract class User {
    private String email;
    private String password;
    private String displayName;
    private Order custOrder;

    public User(String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.custOrder = null;
    }

    public User() {

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

    public Order getCustOrder() { return custOrder; }

    public void setCustOrder(Order custOrder) { this.custOrder = custOrder; }
}