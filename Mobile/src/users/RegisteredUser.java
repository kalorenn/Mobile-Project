package users;
import listings.*;
import products.Product;
import region.Region;

public class RegisteredUser extends User {
    private String full_name;
    private String password;
    private String email;
    private Region region;

    public RegisteredUser(String username, Boolean is_preferential,
                          String full_name, String password,
                          String email, Region region) {
        super(username, is_preferential);
        this.full_name = full_name;
        this.password = password;
        this.email = email;
        this.region = region;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Region getRegion() {
        return region;
    }

    public Listing createListing(String id, Product product,
                                 String creationDate, String title, String description,
                                 Boolean is_promoted, Integer price) {
        //creates sample car listing
        Listing listing = new Listing(id, product, region,
                                    this, creationDate, title, description,
                                    is_promoted, price);
        return listing;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "full_name='" + full_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", region=" + region +
                ", username='" + username + '\'' +
                ", is_preferential=" + is_preferential +
                '}';
    }
}
