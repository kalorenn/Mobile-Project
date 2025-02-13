package mobile.users;

import java.util.ArrayList;
import java.util.List;

import mobile.notifications.channels.NotificationChannel;
import mobile.vehicles.*;
import mobile.notifications.*;
import mobile.search.*;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final String address;
    private final String email;
    private final String password;
    private final String phoneNumber;
    private final List<NotificationRule> notificationRules;

    public User(String username, String address, String email, String password, String phoneNumber) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.notificationRules = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<NotificationRule> getNotificationRules() {
        return notificationRules;
    }

    public void addNotificationRule(NotificationRule notificationRule) {
        notificationRules.add(notificationRule);
    }
}
