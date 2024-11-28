package products;

import products.filter_descriptors.CarFilterDescriptor;
import products.filter_descriptors.FilterDescriptor;

public class CarProduct extends Product {
    private String color;
    private Integer mileage;
    private Integer gearbox_count;
    private String engine_type;

    public CarProduct(String brand, String model,
                      Integer year_of_production, String color,
                      Integer mileage, Integer gearbox_count, String engine_type) {
        super(brand, model, year_of_production);
        this.color = color;
        this.mileage = mileage;
        this.gearbox_count = gearbox_count;
        this.engine_type = engine_type;
    }

    public String getColor() {
        return color;
    }

    public Integer getMileage() {
        return mileage;
    }

    public Integer getGearbox_count() {
        return gearbox_count;
    }

    public String getEngine_type() {
        return engine_type;
    }

    @Override
    FilterDescriptor parseFilters() {
        CarFilterDescriptor cfd = new CarFilterDescriptor();
        return cfd;
    }
}
