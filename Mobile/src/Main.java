import listings.Listing;
import listings.ListingHolder;
import products.CarProduct;
import region.Region;
import users.RegisteredUser;

public class Main {
    public static void main(String[] args) {
        Region sofia_region = new Region("Sofia", "Sofia");
        ListingHolder listings = new ListingHolder();

        RegisteredUser reg_usr = new RegisteredUser("kalorenn", false,
                                                    "Kaloyan M", "passwd123",
                                                    "sample@email.bg", sofia_region);
        CarProduct c1 = new CarProduct("Toyota", "Corolla", 2005, "blue",
                1000, 5, "gasoline");
        CarProduct c2 = new CarProduct("Nissan", "Altima", 2012, "red",
                2200, 6, "diesel");

        listings.addListing(reg_usr.createListing("abc", c1, "14.04.2024",
                "prodavam toyota", "mn zapazen avtomobil", false, 12000));

        listings.addListing(reg_usr.createListing("acdc", c2, "14.04.2024",
                "prodavam nissan", "gore dolu zapazen avtomobil", false, 7000));

        System.out.println("Listings:");
        listings.getListings().forEach(System.out::println);

    }
}
