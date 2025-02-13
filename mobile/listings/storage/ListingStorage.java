package mobile.listings.storage;

import mobile.listings.Listing;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

public class ListingStorage {
    private final List<Listing> listings = new ArrayList<>();

    public void addListing(Listing listing) {
        listings.add(listing);
    }

    public Optional<Listing> getListingById(int id) {
        return listings.stream()
                .filter(listing -> listing.index() == id)
                .findFirst();
    }

    public List<Listing> getListings() {
        return listings;
    }
}
