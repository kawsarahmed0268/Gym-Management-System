import java.time.LocalDate;

public class MemberShip {
    public int planId;
    public String planName;
    public int duration;
    public double fee;
    public String expiryDate;

    public MemberShip(int planId, String planName, int duration, double fee, String expiryDate) {
        this.planId = planId;
        this.planName = planName;
        this.duration = duration;
        this.fee = fee;
        this.expiryDate = expiryDate;
        this.expiryDate = LocalDate.now().plusMonths(duration).toString();
    }

    public void createPlan() {
        System.out.println("Plan '" + planName + "' created.");
    }

    public void checkExpiry() {
        System.out.println("Expiry Date: " + expiryDate);
    }

    public void renewMembership() {
        System.out.println("Membership renewed for " + planName);
    }
}