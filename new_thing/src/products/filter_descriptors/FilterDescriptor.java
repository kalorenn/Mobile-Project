package products.filter_descriptors;

import filters.Filter;

import java.util.HashMap;
import java.util.Map;

public abstract class FilterDescriptor {
    protected final Map<String, Filter<?>> filters = new HashMap<>();

    public Map<String, Filter<?>> getFilters() {
        return filters;
    }

    public void addFilter(String fieldName, Filter<?> filter) {
        filters.put(fieldName, filter);
    }
}
