package mobile.parser;

import mobile.listings.Listing;
import mobile.search.ExactValueFilter;
import mobile.search.Filter;
import mobile.search.RangeFilter;
import mobile.search.composite.AndFilter;
import mobile.search.composite.OrFilter;
import mobile.vehicles.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuerySearcher implements Searcher{

    private List<Listing> apply_filters(List<Listing> input, Filter<Car> filter){
        List<Listing> result = new ArrayList<>();

        for (Listing listing : input) {
            if(filter.matches(listing.car())){
                result.add(listing);
            }

        }

        return result;
    }

    private String trim (String input){
        if (input.length() > 1) {
            if(input.charAt(0) == 39){
                String trimmed = input.substring(1, input.length() - 1);
                return trimmed;
            }
        }
        return input;
    }

    private Filter<Car> extract_filter (List<String> query_in_polish){

        if (query_in_polish.get(0).equals("=")) {
            query_in_polish.remove(0);
            String value = query_in_polish.remove(0);
            value = trim(value);
            String type = query_in_polish.remove(0);

            if(type.equals("brand")){
                Filter<Car> filter = new ExactValueFilter<>(c -> c.brand(), value);
                return filter;
            }
            else if(type.equals("model")){
                Filter<Car> filter = new ExactValueFilter<>(c -> c.model(), value);
                return filter;
            }
            else if(type.equals("year")){
                int value_to_int = Integer.parseInt(value);
                Filter<Car> filter = new ExactValueFilter<>(c -> c.year(), value_to_int);
                return filter;
            }
        }
        else if (query_in_polish.get(0).equals(">")) {
            query_in_polish.remove(0);
            String value = query_in_polish.remove(0);
            value = trim(value);
            String type = query_in_polish.remove(0);

            if(type.equals("year")){
                int value_to_int = Integer.parseInt(value);
                Filter<Car> filter = new RangeFilter<>(c -> c.year(), value_to_int, Integer.MAX_VALUE);
                return filter;
            }
        }
        else if (query_in_polish.get(0).equals("<")) {
            query_in_polish.remove(0);
            String value = query_in_polish.remove(0);
            value = trim(value);
            String type = query_in_polish.remove(0);

            if(type.equals("year")){
                int value_to_int = Integer.parseInt(value);
                Filter<Car> filter = new RangeFilter<>(c -> c.year(), 0, value_to_int);
                return filter;
            }
        }
        else if (query_in_polish.get(0).equals("&")) {
            query_in_polish.remove(0);
            Filter<Car> left_filter = extract_filter(query_in_polish);
            Filter<Car> right_filter = extract_filter(query_in_polish);
            Filter<Car> filter = new AndFilter<>(left_filter, right_filter);
            return filter;
        }
        else if (query_in_polish.get(0).equals("|")) {
            query_in_polish.remove(0);
            Filter<Car> left_filter = extract_filter(query_in_polish);
            Filter<Car> right_filter = extract_filter(query_in_polish);
            Filter<Car> filter = new OrFilter<>(left_filter, right_filter);
            return filter;
        }

        return null;
    }

    @Override
    public List<Listing> search(List<Listing> listings, String query) {
        List<Listing> filtered_listings = new ArrayList<Listing>();
        List<Filter<Car>> filters_to_search = new ArrayList<Filter<Car>>();

        QueryParser parser = new QueryParser();
        List<String> query_in_polish = parser.toPolishNotation(query);

        Filter<Car> filter = extract_filter(query_in_polish);

        filtered_listings = apply_filters(listings, filter);

        return filtered_listings;
    }
}
