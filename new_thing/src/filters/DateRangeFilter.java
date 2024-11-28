package filters;

import java.time.LocalDate;

public class DateRangeFilter extends RangeFilter<LocalDate> {
    public DateRangeFilter(String name) {
        super(name);
    }
}
