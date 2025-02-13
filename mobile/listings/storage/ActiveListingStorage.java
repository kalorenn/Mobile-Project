package mobile.listings.storage;

import mobile.listings.Listing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActiveListingStorage {
    private final List<Listing> activeListings = new ArrayList<>();

    public void addListing(Listing listing) {
        activeListings.add(listing);
    }

    public Optional<Listing> getListingById(int id) {
        return activeListings.stream()
                .filter(listing -> listing.index() == id)
                .findFirst();
    }

    public List<Listing> getActiveListings() {
        return activeListings;
    }

    public void removeListing(int id) {
        activeListings.removeIf(listing -> listing.index() == id);
    }
}
