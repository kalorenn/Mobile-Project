package filters;

public abstract class RangeFilter<T extends Comparable<T>> extends Filter<T[]> {
    protected T min_value;
    protected T max_value;

    public RangeFilter(String name) {
        super(name);
    }

    public void setRange(T min_value, T max_value) {
        this.min_value = min_value;
        this.max_value = max_value;
    }

    @Override
    public T[] getFilterValue() {
        return (T[]) new Comparable[]{min_value, max_value};
    }
}
