
import java.util.HashMap;

public class AuthManager {

    private HashMap<String, String> userCredentials = new HashMap<>();
    private String loggedInUser = null;

    public AuthManager() {
        userCredentials.put("admin", "1234");
    }

    public boolean signup(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("ERROR: Username cannot be empty!");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("ERROR: Password cannot be empty!");
            return false;
        }
        if (userCredentials.containsKey(username)) {
            System.out.println("ERROR: Username '" + username + "' already exists! Choose another.");
            return false;
        }
        userCredentials.put(username, password);
        System.out.println("SUCCESS: Account created for '" + username + "'. You can now log in.");
        return true;
    }

    public boolean login(String username, String password) {
        if (!userCredentials.containsKey(username)) {
            System.out.println("ERROR: Username not found! Please sign up first.");
            return false;
        }
        if (!userCredentials.get(username).equals(password)) {
            System.out.println("ERROR: Wrong password! Access Denied.");
            return false;
        }
        loggedInUser = username;
        System.out.println("SUCCESS: Welcome, " + username + "! Access Granted.");
        return true;
    }

    public void logout() {
        if (loggedInUser != null) {
            System.out.println("Logged out: " + loggedInUser);
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public boolean userExists(String username) {
        return userCredentials.containsKey(username);
    }
}
