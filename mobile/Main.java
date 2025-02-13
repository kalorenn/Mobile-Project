package mobile;

import mobile.listings.storage.ActiveListingStorage;
import mobile.listings.storage.HistoricalListingStorage;
import mobile.notifications.*;
import mobile.users.*;
import mobile.languages.*;
import mobile.cli.*;
import mobile.listings.*;

public class Main {
    public static void main(String[] args) {

        UserStorage userStorage = new UserStorage();
        ActiveListingStorage activeListingStorage = new ActiveListingStorage();
        HistoricalListingStorage historicalListingStorage = new HistoricalListingStorage();
        NotificationService notificationService = new NotificationService();
        ListingService listingService = new ListingService(activeListingStorage, historicalListingStorage, notificationService);

        //can preload existing users and past listings
        //userStorage.fillFromDatabase("mobile\\databases\\userDB.txt");
        //historicalListingStorage.fillFromDatabase("mobile\\databases\\listingDB.txt");

        LanguageHandler languageHandler = new LanguageHandler();
        languageHandler.changeLanguage("mobile\\databases\\english.txt");

        CLI cli = new CLI(userStorage, listingService, notificationService, languageHandler);
        cli.start();

    }

}
