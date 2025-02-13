package mobile.search;
import java.util.List;

public interface FilterFactory<T> {
    Filter<T> createFilter(List<String> queryInPolish);
}
