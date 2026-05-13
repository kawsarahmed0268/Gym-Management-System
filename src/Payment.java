public class Payment {
    private int paymentId, memberId;
    private double amount;
    private String paymentDate;
    private String memberName;

    public Payment(int paymentId, int memberId, double amount, String paymentDate) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.memberName = memberName;
    }

    public void recordPayment() {
        System.out.println("Payment of " + amount + " recorded for " + memberId + ".");
    }

    public void showPaymentHistory() {
        System.out.println("Member Name : " + memberName + "\nMember Id : " + memberId +
                "\nDate: " + paymentDate + " \nAmount: " + amount);
    }

    public void generateReceipt() {
        System.out.println("--- Receipt ---" + "\n ID: " + memberId +
                "\nAmount: " + amount + "\n----------------");
    }
}