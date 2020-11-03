package Users;

public class Admin extends User {
    private String accountType;

    public Admin(String email, String password, String displayName) {
        super(email, password, displayName);
        this.accountType = "Admin";
    }

    @Override
    public String toString(){
        return String.format("%-30s\t - \t%-10s", this.getEmail(), this.getDisplayName());
    }

}
