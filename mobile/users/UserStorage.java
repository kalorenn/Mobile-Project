package mobile.users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserStorage {
    private final List<User> users = new ArrayList<>();

    public void fillFromDatabase(String filename) {
        users.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String name;
            while ((name = br.readLine()) != null) {
                String address = br.readLine();
                String email = br.readLine();
                String password = br.readLine();
                String phoneNumber = br.readLine();

                if (address == null || email == null || password == null || phoneNumber == null) {
                    System.out.println("Invalid data format, skipping entry for: " + name);
                    continue;
                }

                users.add(new User(name, address, email, password, phoneNumber));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }

    public void addUser(User user) {
        users.add(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public List<User> getAllUsers() {
        return users;
    }
}
