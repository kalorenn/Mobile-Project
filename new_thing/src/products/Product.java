package products;

import products.filter_descriptors.*;

public abstract class Product {
    protected String brand;
    protected String model;
    protected Integer year_of_production;

    public Product(String brand, String model, Integer year_of_production) {
        this.brand = brand;
        this.model = model;
        this.year_of_production = year_of_production;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear_of_production() {
        return year_of_production;
    }

    abstract FilterDescriptor parseFilters();
}
