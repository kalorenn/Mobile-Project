package mobile.listings;

import mobile.listings.storage.ActiveListingStorage;
import mobile.listings.storage.HistoricalListingStorage;
import mobile.notifications.NotificationService;
import mobile.users.*;

import java.util.List;

public class ListingService {
    private final ActiveListingStorage activeListingStorage;
    private final HistoricalListingStorage historicalListingStorage;
    private final NotificationService notificationService;

    public ListingService(ActiveListingStorage activeListingStorage, HistoricalListingStorage historicalListingStorage, NotificationService notificationService) {
        this.activeListingStorage = activeListingStorage;
        this.historicalListingStorage = historicalListingStorage;
        this.notificationService = notificationService;
    }

    public void addListing(Listing listing) {
        activeListingStorage.addListing(listing);
        historicalListingStorage.addListing(listing);
        notificationService.onNewListingAdded(listing);
    }

    public void buyListing(int listingId, User buyer) {
        Listing listing = activeListingStorage.getListingById(listingId)
                .orElseThrow(() -> new IllegalArgumentException("Listing not found: " + listingId));
        activeListingStorage.removeListing(listingId);
        System.out.println("User " + buyer.getUsername() + " bought listing " + listingId);
    }

    public List<Listing> getActiveListings() {
        return activeListingStorage.getActiveListings();
    }

    public List<Listing> getHistoricalListings() {
        return historicalListingStorage.getHistory();
    }
}
