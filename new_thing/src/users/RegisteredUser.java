package users;
import listings.*;

public class RegisteredUser extends User {
    protected String first_name;
    protected String last_name;
    protected String password;
    protected String email;

    public RegisteredUser(String username, Boolean is_preferential, String first_name, String last_name, String password, String email) {
        super(username, is_preferential);
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
    }

    public Listing createListing() {
        Listing listing = new Listing();
        return listing;
    }
}
