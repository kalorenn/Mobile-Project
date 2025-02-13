package mobile.listings.storage;

import mobile.listings.Listing;
import mobile.listings.ListingHistory;
import mobile.vehicles.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class HistoricalListingStorage {
    private final ListingHistory history = new ListingHistory();

    public void fillFromDatabase(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String brand;
            while ((brand = br.readLine()) != null) {
                String model = br.readLine();
                int year = Integer.parseInt(br.readLine());
                int price = Integer.parseInt(br.readLine());
                boolean isManual = Boolean.parseBoolean(br.readLine());
                int creationYear = Integer.parseInt(br.readLine());

                history.addListing(new Listing(new Car(brand, model, year, isManual), price, 0, creationYear));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void addListing(Listing listing) {
        history.addListing(listing);
    }

    public List<Listing> getHistory() {
        return history.getHistory();
    }
}