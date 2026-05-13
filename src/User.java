public abstract class User {
    private int userId;
    private String userName;
    private String password;
    private String role;

    public User(int userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }
    public String getRole() {
        return role;
    }
    public boolean checkLogin(String u, String p) {
        return this.userName.equals(u) && this.password.equals(p);
    }

    public abstract void login();

    public void logout() {
        System.out.println(role + " logged out successfully.");
    }
}
