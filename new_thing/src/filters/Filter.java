package filters;

public abstract class Filter<T> {
    protected String name;

    public Filter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract T getFilterValue();
}
