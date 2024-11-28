package products.filter_descriptors;
import filters.*;

public class CarFilterDescriptor extends FilterDescriptor {
    public CarFilterDescriptor() {
        addFilter("price", new PriceRangeFilter("price"));
        addFilter("manufactureDate", new DateRangeFilter("manufactureDate"));
        addFilter("engineType", new ValueFilter("engineType"));
        addFilter("brand", new ValueFilter("brand"));
        addFilter("model", new ValueFilter("model"));
    }
}
