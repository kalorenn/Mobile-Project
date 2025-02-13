package mobile.cli;

import java.util.*;
import java.time.Year;
import java.util.stream.Collectors;

import mobile.listings.*;
import mobile.notifications.*;
import mobile.languages.LanguageHandler;
import mobile.notifications.channels.*;
import mobile.notifications.external.*;
import mobile.users.*;
import mobile.parser.*;
import mobile.search.*;
import mobile.vehicles.Car;

public class CLI {
    private static int counter = 0;
    private final UserStorage userStorage;
    private final ListingService listingService;
    private final NotificationService notificationService;
    private User loggedInUser;
    private final Scanner scanner;
    private final LanguageHandler languageHandler;

    public CLI(UserStorage userStorage, ListingService listingService, NotificationService notificationService, LanguageHandler languageHandler) {
        this.userStorage = userStorage;
        this.listingService = listingService;
        this.notificationService = notificationService;
        this.scanner = new Scanner(System.in);
        this.languageHandler = languageHandler;
    }

    public void start() {
        while (true) {
            if (loggedInUser == null) {
                showLoginMenu();
            } else {
                showLoggedInMenu();
            }
        }
    }

    private void showLoginMenu() {
        languageHandler.fromDictionary(0);
        languageHandler.fromDictionary(3);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                createAccount();
                break;
            case 3:
                changeLanguage();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                languageHandler.fromDictionary(28);
        }
    }

    private void showLoggedInMenu() {
        languageHandler.fromDictionary(1);
        languageHandler.fromDictionary(3);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewListings();
                break;
            case 2:
                createListing();
                break;
            case 3:
                getFilteredListings();
                break;
            case 4:
                buyListing();
                break;
            case 5:
                priceAverage();
                break;
            case 6:
                editPreferences();
                break;
            case 7:
                logout();
                break;
            default:
                languageHandler.fromDictionary(28);
        }
    }

    private void login() {
        languageHandler.fromDictionary(4);
        String username = scanner.nextLine();
        languageHandler.fromDictionary(5);
        String password = scanner.nextLine();

        Optional<User> user = userStorage.getUserByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            loggedInUser = user.get();
            languageHandler.fromDictionary(17);
            System.out.println("   " + loggedInUser.getUsername());
        } else {
            languageHandler.fromDictionary(25);
        }
    }

    private void changeLanguage(){
        System.out.println(" 1. English   2. Български");
        languageHandler.fromDictionary(3);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                languageHandler.changeLanguage("mobile\\databases\\english.txt");
                break;
            case 2:
                languageHandler.changeLanguage("mobile\\databases\\bulgarian.txt");
                break;
            default:
                languageHandler.fromDictionary(28);
        }
    }

    private void createAccount() {
        languageHandler.fromDictionary(4);
        String username = scanner.nextLine();
        languageHandler.fromDictionary(6);
        String email = scanner.nextLine();
        languageHandler.fromDictionary(5);
        String password = scanner.nextLine();
        languageHandler.fromDictionary(7);
        String address = scanner.nextLine();
        languageHandler.fromDictionary(8);
        String phoneNumber = scanner.nextLine();

        User newUser = new User(username, address, email, password, phoneNumber);
        userStorage.addUser(newUser);
        languageHandler.fromDictionary(20);
    }

    private void viewListings() {
        languageHandler.fromDictionary(18);
        listingService.getActiveListings().forEach(listing ->
                System.out.println("   " + listing.index() + ": " + listing.car().brand() + " " + listing.car().model() + " (" + listing.car().year() + "), " + listing.price() + " BGN")
        );
    }

    private List<Listing> filterActiveListings() {
        languageHandler.fromDictionary(9);
        String query = scanner.nextLine();
        QuerySearcher searcher = new QuerySearcher();

        return searcher.search(listingService.getActiveListings(), query);
    }

    private List<Listing> filterHistoricalListings() {
        languageHandler.fromDictionary(9);
        String query = scanner.nextLine();
        QuerySearcher searcher = new QuerySearcher();

        return searcher.search(listingService.getHistoricalListings(), query);
    }

    private void getFilteredListings() {
        List<Listing> results = filterActiveListings();
        results.forEach(listing ->
                System.out.println("   " + listing.index() + ": " + listing.car().brand() + " " + listing.car().model() + " (" + listing.car().year() + "), " + listing.price() + " BGN")
        );
    }

    private void priceAverage(){
        List<Listing> results = filterHistoricalListings();
        languageHandler.fromDictionary(19);

        Map<Integer, Double> averagePriceByYear = results.stream()
                .collect(Collectors.groupingBy(
                        Listing::creationYear,
                        Collectors.averagingInt(Listing::price)
                ));

        averagePriceByYear.forEach((year, avgPrice) ->
                System.out.println("   " + year + ": " + avgPrice + " BGN")
        );
    }

    private void createListing() {
        languageHandler.fromDictionary(10);
        String brand = scanner.nextLine();
        languageHandler.fromDictionary(11);
        String model = scanner.nextLine();
        languageHandler.fromDictionary(12);
        int year = scanner.nextInt();
        scanner.nextLine();
        languageHandler.fromDictionary(13);
        int price = scanner.nextInt();
        scanner.nextLine();
        languageHandler.fromDictionary(14);
        boolean manual = scanner.nextBoolean();
        scanner.nextLine();

        int creationDate = Year.now().getValue();

        Car car = new Car(brand, model, year, manual );
        Listing listing = new Listing(car, price, ++counter, creationDate);
        listingService.addListing(listing);
        languageHandler.fromDictionary(21);
    }

    private void buyListing() {
        languageHandler.fromDictionary(15);
        int listingId = scanner.nextInt();

        listingService.buyListing(listingId, loggedInUser);
        languageHandler.fromDictionary(22);
    }

    private void editPreferences() {
        languageHandler.fromDictionary(2);
        languageHandler.fromDictionary(3);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            languageHandler.fromDictionary(9);
            String query = scanner.nextLine();
            languageHandler.fromDictionary(16);
            String channelType = scanner.nextLine();

            Filter<Car> filter = createFilter(query);
            NotificationChannel channel = createChannel(channelType, loggedInUser);

            if (filter != null && channel != null) {
                NotificationRule rule = new NotificationRule(List.of(filter), channel);
                notificationService.subscribe(rule);
                languageHandler.fromDictionary(23);
            } else {
                languageHandler.fromDictionary(26);
            }
        } else if (choice == 2) {
            // Implement removal logic if needed
            languageHandler.fromDictionary(27);
        } else {
            languageHandler.fromDictionary(28);
        }
    }

    private Filter<Car> createFilter(String query) {
        QuerySearcher searcher = new QuerySearcher();
        return searcher.parseFilter(query);
    }

    private NotificationChannel createChannel(String channelType, User user) {
        switch (channelType.toLowerCase()) {
            case "email":
                return new EmailNotificationChannel(new EmailNotifier(), user.getEmail());
            case "sms":
                return new SmsNotificationChannel(new SmsNotifier(), user.getPhoneNumber());
            case "pigeon":
                return new PigeonNotificationChannel(new PigeonNotifier(), user.getAddress());
            default:
                return null;
        }
    }

    private void logout() {
        loggedInUser = null;
        languageHandler.fromDictionary(24);
    }
}
