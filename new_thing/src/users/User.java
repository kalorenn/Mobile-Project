package users;

public abstract class User {
    protected String username;
    protected Boolean is_preferential;

    public User(String username, Boolean is_preferential) {
        this.username = username;
        this.is_preferential = is_preferential;
    }

}
