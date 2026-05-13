public class Staff extends User {
    public Staff(int userId, String userName, String password) {
        super(userId, userName, password, "Staff");
    }

    @Override
    public void login() {
        System.out.println("Staff login successful.");
    }

    public void recordPayment() { System.out.println("Payment recorded."); }
    public void checkInMember() { System.out.println("Member check-in completed."); }
}
