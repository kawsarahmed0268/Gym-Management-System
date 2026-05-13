import java.util.*;

public class GymManagementSystem {
    static List<Member> memberList = new ArrayList<>();
    static List<Trainer> trainerList = new ArrayList<>();
    static List<Payment> paymentList = new ArrayList<>();
    static List<Attendance> attendanceList = new ArrayList<>();
    static List<MemberShip> planList = new ArrayList<>();

    public static boolean isIdUnique(int id) {
        for (Member m : memberList) {
            if (m.memberId == id) return false;
        }
        return true;
    }

    public static Member findMember(int id) {
        for (Member m : memberList) {
            if (m.memberId == id) return m;
        }
        return null;
    }

    public static void showMenu() {
        System.out.println("\n===== GYM MANAGEMENT SYSTEM MENU =====");
        System.out.println("1.  Add Member");
        System.out.println("2.  Show Members");
        System.out.println("3.  Edit Member");
        System.out.println("4.  Delete Member");
        System.out.println("5.  Add Trainer");
        System.out.println("6.  Show Trainers");
        System.out.println("7.  Assign Trainer");
        System.out.println("8.  Create Membership Plan");
        System.out.println("9.  Assign Membership");
        System.out.println("10. Check Membership Expiry");
        System.out.println("11. Renew Membership");
        System.out.println("12. Record Payment");
        System.out.println("13. Show Payment History");
        System.out.println("14. Generate Receipt");
        System.out.println("15. Member Check-In");
        System.out.println("16. Show Attendance History");
        System.out.println("17. Calculate BMI");
        System.out.println("18. Login System");
        System.out.println("19. Logout System");
        System.out.println("20. Exit");
        System.out.println("======================================");
        System.out.print("Enter Your Choice: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("[,\\s\\r\\n]+");
        Admin admin = new Admin(1, "admin", "1234");
        int choice = 0;

        do {
            showMenu();
            try {
                if (!sc.hasNextInt()) { sc.next(); continue; }
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\n--- Add Member ---");
                        System.out.print("Enter Member ID: "); int id = sc.nextInt();
                        if (!isIdUnique(id)) {
                            System.out.println("ERROR: This ID is already taken! Use a different ID.");
                            break;
                        }
                        System.out.print("Enter Name: "); String n = sc.next();
                        System.out.print("Enter Age: "); int a = sc.nextInt();
                        System.out.print("Enter Gender: "); String g = sc.next();
                        System.out.print("Enter Phone: "); String p = sc.next();
                        memberList.add(new Member(id, n, a, g, p));
                        admin.addMember();
                        System.out.println("Member added successfully!");
                        break;

                    case 2:
                        if (memberList.isEmpty()) System.out.println("Database is empty!");
                        else {
                            for (Member m : memberList) m.showMember();
                        }
                        break;

                    case 3:
                        System.out.print("Enter Member ID to edit: ");
                        int eid = sc.nextInt();
                        Member em = findMember(eid);

                        if (em != null) {
                            System.out.println("\n--- Editing Member: " + em.getName() + " ---");

                            System.out.print("Enter New Name: ");
                            em.setName(sc.next());

                            System.out.print("Enter New Age: ");
                            em.setAge(sc.nextInt());

                            System.out.print("Enter New Gender: ");
                            em.setGender(sc.next());

                            System.out.print("Enter New Phone: ");
                            em.setPhone(sc.next());

                            System.out.println("\nSUCCESS: All information updated line-by-line!");
                        } else {
                            System.out.println("ERROR: Member with ID " + eid + " not found!");
                        }
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: "); int did = sc.nextInt();
                        if (memberList.removeIf(m -> m.memberId == did)) {
                            admin.deleteMember();
                        } else System.out.println("ID not found!");
                        break;

                    case 5:
                        System.out.println("\n--- Add Trainer ---");
                        System.out.print("Trainer ID: "); int tid = sc.nextInt();
                        System.out.print("Name: "); String tn = sc.next();
                        System.out.print("Specialty: "); String ts = sc.next();
                        System.out.print("Phone: "); String tp = sc.next();
                        trainerList.add(new Trainer(tid, tn, ts, tp));
                        admin.addTrainer();
                        break;

                    case 6:
                        if (trainerList.isEmpty()) System.out.println("No trainers found.");
                        else {
                            for (Trainer t : trainerList) t.showTrainer();
                        }
                        break;

                    case 7:
                        System.out.print("Enter Member ID: ");
                        Member m7 = findMember(sc.nextInt());

                        if (m7 != null && !trainerList.isEmpty()) {

                            System.out.println("Available Trainers:");
                            for (Trainer t : trainerList) {
                                System.out.println("ID: " + t.getTrainerId() + " | Name: " + t.getName());
                            }

                            System.out.print("Enter Trainer ID to assign: ");
                            int selectedTid = sc.nextInt();
                            Trainer selectedTrainer = null;

                            for (Trainer t : trainerList) {
                                if (t.getTrainerId() == selectedTid) {
                                    selectedTrainer = t;
                                    break;
                                }
                            }

                            if (selectedTrainer != null) {
                                m7.assignTrainer(selectedTrainer);
                                System.out.println("Success: Trainer " + selectedTrainer.getName() + " assigned!");
                            } else {
                                System.out.println("Error: Trainer ID not found!");
                            }
                        } else {
                            System.out.println("Error: Member or Trainer list empty!");
                        }
                        break;

                    case 8:
                        try {
                            System.out.println("\n--- Create Plan ---");
                            System.out.print("Plan ID: ");
                            int pid = sc.nextInt();

                            System.out.print("Plan Name: ");
                            String pn = sc.next();

                            System.out.print("Duration (Months): ");
                            int dur = sc.nextInt();

                            System.out.print("Fee: ");

                            if (!sc.hasNextDouble()) {
                                System.out.println("ERROR: Please enter a valid number for Fee!");
                                sc.next(); // invalid input clear koro
                                break;
                            }
                            double fee = sc.nextDouble();

                            planList.add(new MemberShip(pid, pn, dur, fee, "2026-12-31"));
                            admin.createPlan();
                            System.out.println("SUCCESS: Plan '" + pn + "' created successfully.");
                        } catch (Exception e) {
                            System.out.println("ERROR: Invalid input format. Please try again.");
                            sc.nextLine();
                        }
                        break;

                    case 9:
                        System.out.print("Enter Member ID: ");
                        Member m9 = findMember(sc.nextInt());

                        if (m9 != null && !planList.isEmpty()) {

                            System.out.println("Available Membership Plans:");
                            for (MemberShip plan : planList) {
                                System.out.println("ID: " + plan.planId + " | Name: " + plan.planName + " | Fee: " + plan.fee);
                            }

                            System.out.print("Enter Plan ID to assign: ");
                            int selectedPid = sc.nextInt();
                            MemberShip selectedPlan = null;

                            for (MemberShip ms : planList) {
                                if (ms.planId == selectedPid) {
                                    selectedPlan = ms;
                                    break;
                                }
                            }

                            if (selectedPlan != null) {
                                m9.assignMembership(selectedPlan);
                                System.out.println("Success: Plan '" + selectedPlan.planName + "' assigned to " + m9.getName());
                            } else {
                                System.out.println("Error: Plan ID not found!");
                            }
                        } else {
                            System.out.println("Error: Member not found or Plan list is empty!");
                        }
                        break;

                    case 10:
                        System.out.print("Enter Member ID: "); Member m10 = findMember(sc.nextInt());
                        if (m10 != null && m10.membership != null) {
                            m10.membership.checkExpiry();
                        } else System.out.println("Member has no active plan.");
                        break;

                    case 11:
                        System.out.print("Enter Member ID: "); Member m11 = findMember(sc.nextInt());
                        if (m11 != null && m11.membership != null) {
                            m11.membership.renewMembership();
                        } else System.out.println("Nothing to renew.");
                        break;

                    case 12:
                        System.out.println("\n--- Record Payment ---");
                        System.out.print("Payment ID: "); int pyId = sc.nextInt();
                        System.out.print("Member ID: "); int pyMid = sc.nextInt();
                        System.out.print("Amount: "); double pyAmt = sc.nextDouble();
                        System.out.print("Date: "); String pyDate = sc.next();
                        Payment p12 = new Payment(pyId, pyMid, pyAmt, pyDate);
                        paymentList.add(p12);
                        p12.recordPayment();
                        break;

                    case 13:
                        if (paymentList.isEmpty()) System.out.println("No records.");
                        else for (Payment ph : paymentList) {
                            System.out.println("--- Payment Record ---");
                            ph.showPaymentHistory();
                        }
                        break;

                    case 14:
                        if (!paymentList.isEmpty()) {
                            paymentList.get(paymentList.size()-1).generateReceipt();
                        } else System.out.println("No payments made yet.");
                        break;

                    case 15:
                        System.out.println("\n--- Member Check-In ---");
                        System.out.print("Member ID: "); int attMid = sc.nextInt();
                        Attendance a15 = new Attendance(1, attMid, "2026-05-13", "09:00 AM");
                        attendanceList.add(a15);
                        a15.checkIn();
                        break;

                    case 16:
                        for (Attendance ah : attendanceList) ah.showAttendanceHistory();
                        break;

                    case 17:
                        System.out.println("\n--- BMI Calculator ---");
                        System.out.print("Height(cm): "); double h = sc.nextDouble();
                        System.out.print("Weight(kg): "); double w = sc.nextDouble();
                        double bmiVal = BMI.calculateBMI(h/100, w);
                        System.out.println("BMI Value: " + String.format("%.2f", bmiVal));
                        System.out.println("Category : " + BMI.getBMICategory(bmiVal));
                        break;

                    case 18:
                        System.out.print("User: "); String uInput = sc.next();
                        System.out.print("Pass: "); String pInput = sc.next();
                        if (admin.checkLogin(uInput, pInput)) admin.login();
                        else System.out.println("Access Denied!");
                        break;

                    case 19:
                        admin.logout();
                        break;

                    case 20:
                        System.out.println("Shutting down... Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("\n!!! SYSTEM ALERT: " + e.getMessage() + " !!!");
                sc.nextLine(); // Clear buffer
                choice = 0;
            }
        } while (choice != 20);
    }
}