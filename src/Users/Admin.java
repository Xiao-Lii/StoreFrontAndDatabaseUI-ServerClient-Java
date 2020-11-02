package Users;

public class Admin extends User {
    public Admin(String email, String password, String displayName) {
        super(email, password, displayName);
    }

    @Override
    public String toString(){
        return String.format("%-30s\t - \t%-10s", this.getEmail(), this.getDisplayName());
    }
}
