import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Payment {
    private int paymentId, memberId;
    private double amount;
    private String paymentDate;
    private String paymentTime;
    private String memberName;

    public Payment(int paymentId, int memberId, double amount, String memberName) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.memberName = memberName;
        this.paymentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.paymentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public void recordPayment() {
        System.out.println("Payment of BDT " + amount + " recorded for Member ID: " + memberId + " on " + paymentDate + ".");
    }

    public void showPaymentHistory() {
        System.out.println("Payment ID  : " + paymentId);
        System.out.println("Member Name : " + (memberName != null ? memberName : "N/A"));
        System.out.println("Member ID   : " + memberId);
        System.out.println("Date        : " + paymentDate);
        System.out.println("Time        : " + paymentTime);
        System.out.println("Amount      : BDT " + amount);
    }

    public void generateReceipt() {
        System.out.println("========== PAYMENT RECEIPT ==========");
        System.out.println("Receipt for Member ID : " + memberId);
        System.out.println("Member Name           : " + (memberName != null ? memberName : "N/A"));
        System.out.println("Amount Paid           : BDT " + amount);
        System.out.println("Date                  : " + paymentDate);
        System.out.println("Time                  : " + paymentTime);
        System.out.println("Payment ID            : " + paymentId);
        System.out.println("=====================================");
    }
}