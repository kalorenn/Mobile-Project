package listings;
import filters.FilterDescriptor;
import users.*;
import products.*;

public class Listing {
    private String title;
    private String description;
    private Double price;
    private String creator;
    private String location;
    private Product product;
    private Boolean is_preferential;

    public Listing(String title, String description,
                   Double price, String creator, String location,
                   Product product, Boolean is_preferential) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creator = creator;
        this.location = location;
        this.product = product;
        this.is_preferential = is_preferential;

    }

}
