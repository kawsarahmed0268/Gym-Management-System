import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        this.expiryDate = LocalDate.now().plusMonths(duration)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void createPlan() {
        System.out.println("Plan '" + planName + "' created.");
    }

    public void checkExpiry() {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate expiry = LocalDate.parse(expiryDate, fmt);
            LocalDate today = LocalDate.now();

            System.out.println("Plan Name   : " + planName);
            System.out.println("Expiry Date : " + expiryDate);

            if (today.isAfter(expiry)) {
                System.out.println("Status      : *** EXPIRED *** (Expired on " + expiryDate + ")");
            } else {
                long daysLeft = today.until(expiry).toTotalMonths();
                System.out.println("Status      : ACTIVE (Approx " + daysLeft + " month(s) remaining)");
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not parse expiry date - " + e.getMessage());
        }
    }

    public void renewMembership() {
        try {
            LocalDate newExpiry = LocalDate.now().plusMonths(duration);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.expiryDate = newExpiry.format(fmt);
            System.out.println("Membership renewed for plan: " + planName);
            System.out.println("New Expiry Date: " + this.expiryDate);
        } catch (Exception e) {
            System.out.println("ERROR: Renewal failed - " + e.getMessage());
        }
    }
}