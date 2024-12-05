package products;

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
    public String toString() {
        return "CarProduct{" +
                "color='" + color + '\'' +
                ", mileage=" + mileage +
                ", gearbox_count=" + gearbox_count +
                ", engine_type='" + engine_type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year_of_production=" + year_of_production +
                '}';
    }
}
