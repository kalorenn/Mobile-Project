package mobile.listings;

import java.util.ArrayList;
import java.util.List;

public class ListingHistory {
    private final List<Listing> history = new ArrayList<>();

    public void addListing(Listing listing) {
        history.add(listing);
    }

    public List<Listing> getHistory() {
        return history;
    }
}
