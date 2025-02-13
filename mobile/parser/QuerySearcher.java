package mobile.parser;

import mobile.listings.Listing;
import mobile.search.*;
import mobile.search.Filter;
import mobile.search.RangeFilter;
import mobile.search.composite.AndFilter;
import mobile.search.composite.OrFilter;
import mobile.vehicles.Car;

import java.util.ArrayList;
import java.util.List;

public class QuerySearcher implements Searcher {

    public Filter<Car> parseFilter(String query) {
        QueryParser parser = new QueryParser();
        List<String> queryInPolish = parser.toPolishNotation(query);

        FilterFactory<Car> filterFactory = new CarFilterFactory();
        return filterFactory.createFilter(queryInPolish);
    }

    @Override
    public List<Listing> search(List<Listing> listings, String query) {
        return applyFilters(listings, parseFilter(query));
    }

    private List<Listing> applyFilters(List<Listing> input, Filter<Car> filter) {
        List<Listing> result = new ArrayList<>();
        for (Listing listing : input) {
            if (filter.matches(listing.car())) {
                result.add(listing);
            }
        }
        return result;
    }

    private static class CarFilterFactory implements FilterFactory<Car> {

        @Override
        public Filter<Car> createFilter(List<String> queryInPolish) {
            String operator = queryInPolish.get(0);
            queryInPolish.remove(0);

            switch (operator) {
                case "=":
                    return createExactValueFilter(queryInPolish);
                case ">":
                    return createRangeFilter(queryInPolish, Integer.MAX_VALUE);
                case "<":
                    return createRangeFilter(queryInPolish, 0);
                case "&":
                    return new AndFilter<>(createFilter(queryInPolish), createFilter(queryInPolish));
                case "|":
                    return new OrFilter<>(createFilter(queryInPolish), createFilter(queryInPolish));
                default:
                    throw new IllegalArgumentException("Unknown operator: " + operator);
            }
        }

        private Filter<Car> createExactValueFilter(List<String> queryInPolish) {
            String value = trim(queryInPolish.remove(0));
            String type = queryInPolish.remove(0);

            switch (type) {
                case "brand":
                    return new CaseInsensitiveFilter<>(Car::brand, value);
                case "model":
                    return new CaseInsensitiveFilter<>(Car::model, value);
                case "year":
                    int year = Integer.parseInt(value);
                    return new ExactValueFilter<>(Car::year, year);
                default:
                    throw new IllegalArgumentException("Unknown type: " + type);
            }
        }

        private Filter<Car> createRangeFilter(List<String> queryInPolish, int upperBound) {
            String value = trim(queryInPolish.remove(0));
            String type = queryInPolish.remove(0);

            if (!type.equals("year")) {
                throw new IllegalArgumentException("Range filter only supports 'year' type.");
            }

            int year = Integer.parseInt(value);
            return new RangeFilter<>(Car::year, upperBound == 0 ? 0 : year, upperBound == 0 ? year : Integer.MAX_VALUE);
        }

        private String trim(String input) {
            if (input.length() > 1 && input.charAt(0) == '\'') {
                return input.substring(1, input.length() - 1);
            }
            return input;
        }
    }
}
