package filters;

public interface Filter<T> {
    boolean matches(T item);
}
