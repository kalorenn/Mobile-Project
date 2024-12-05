package listings;

import filters.Filter;

import java.util.ArrayList;
import java.util.List;

public class ListingHolder {
    private List<Listing> listings;

    public ListingHolder() {
        listings = new ArrayList<Listing>();
    }

    public void addListing(Listing listing) {
        listings.add(listing);
    }

    public List<Listing> getListings() {
        return listings;
    }

    public List<Listing> filterListing(List<Filter<Listing>> filters){
        return listings.stream()
                .filter(listing -> filters.stream().allMatch(filter -> filter.matches(listing)))
                .toList();
    }
}
