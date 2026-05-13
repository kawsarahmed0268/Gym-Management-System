import java.util.List;

public class Admin extends User {
    public Admin(int userId, String userName, String password) {
        super(userId, userName, password, "Admin");
    }

    @Override
    public void login() {
        System.out.println("Welcome : " + getUserName() + ". Access granted.");
    }

    public void addMember() { System.out.println("Member creation initiated."); }
    public void deleteMember() { System.out.println("Member record deleted."); }
    public void addTrainer() { System.out.println("Trainer record added."); }
    public void createPlan() { System.out.println("New membership plan created."); }
}


