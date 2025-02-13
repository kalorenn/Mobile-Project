package mobile.notifications;

import mobile.listings.Listing;
import mobile.notifications.channels.NotificationChannel;
import mobile.search.Filter;
import mobile.vehicles.Car;

import java.util.List;

public record NotificationRule(
        List<Filter<Car>> filters,  // changed Filter<Listing> to Filter<Car>
        NotificationChannel channel
) {}
