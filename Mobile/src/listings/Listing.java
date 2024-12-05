package listings;
import products.*;
import region.*;
import users.*;

public class Listing {

    private String id;
    private Product product;
    private Region region;
    private User owner;
    private String creationDate;
    private String title;
    private String description;
    private Boolean is_promoted;
    private Integer price;

    public Listing(String id, Product product, Region region,
                   User owner, String creationDate, String title, String description,
                   Boolean is_promoted, Integer price) {
        this.id = id;
        this.product = product;
        this.region = region;
        this.owner = owner;
        this.creationDate = creationDate;
        this.title = title;
        this.description = description;
        this.is_promoted = is_promoted;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Region getRegion() {
        return region;
    }

    public User getOwner() {
        return owner;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIs_promoted() {
        return is_promoted;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", region=" + region +
                ", owner=" + owner +
                ", creationDate='" + creationDate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", is_promoted=" + is_promoted +
                ", price=" + price +
                '}';
    }
}
