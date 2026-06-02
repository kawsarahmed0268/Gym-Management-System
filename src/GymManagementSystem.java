import java.util.*;

public class GymManagementSystem {
    static List<Member> memberList = new ArrayList<>();
    static List<Trainer> trainerList = new ArrayList<>();
    static List<Payment> paymentList = new ArrayList<>();
    static List<Attendance> attendanceList = new ArrayList<>();
    static List<MemberShip> planList = new ArrayList<>();
    static AuthManager authManager = new AuthManager();

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
        System.out.println("18. Login");
        System.out.println("19. Logout");
        System.out.println("20. Signup (New User)");
        System.out.println("21. Exit");
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
                        try {
                            System.out.println("\n--- Add Member ---");
                            System.out.print("Enter Member ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            int id = sc.nextInt();
                            if (!isIdUnique(id)) {
                                System.out.println("ERROR: This ID is already taken! Use a different ID.");
                                break;
                            }
                            System.out.print("Enter Name: "); String n = sc.next();
                            System.out.print("Enter Age: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: Age must be a number!"); sc.next(); break; }
                            int a = sc.nextInt();
                            if (a <= 0 || a > 120) { System.out.println("ERROR: Enter a valid age (1-120)!"); break; }
                            System.out.print("Enter Gender: "); String g = sc.next();
                            System.out.print("Enter Phone: "); String p = sc.next();
                            memberList.add(new Member(id, n, a, g, p));
                            admin.addMember();
                            System.out.println("Member added successfully!");
                        } catch (Exception e) {
                            System.out.println("ERROR: Failed to add member - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 2:
                        if (memberList.isEmpty()) System.out.println("Database is empty!");
                        else { for (Member m : memberList) m.showMember(); }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter Member ID to edit: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            int eid = sc.nextInt();
                            Member em = findMember(eid);
                            if (em != null) {
                                System.out.println("\n--- Editing Member: " + em.getName() + " ---");
                                System.out.print("Enter New Name: "); em.setName(sc.next());
                                System.out.print("Enter New Age: ");
                                if (!sc.hasNextInt()) { System.out.println("ERROR: Age must be a number!"); sc.next(); break; }
                                int newAge = sc.nextInt();
                                if (newAge <= 0 || newAge > 120) { System.out.println("ERROR: Enter a valid age!"); break; }
                                em.setAge(newAge);
                                System.out.print("Enter New Gender: "); em.setGender(sc.next());
                                System.out.print("Enter New Phone: "); em.setPhone(sc.next());
                                System.out.println("\nSUCCESS: All information updated!");
                            } else {
                                System.out.println("ERROR: Member with ID " + eid + " not found!");
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR: Edit failed - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 4:
                        try {
                            System.out.print("Enter ID to delete: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            int did = sc.nextInt();
                            if (memberList.removeIf(m -> m.memberId == did)) {
                                admin.deleteMember();
                            } else System.out.println("ERROR: ID not found!");
                        } catch (Exception e) {
                            System.out.println("ERROR: Delete failed - " + e.getMessage());
                        }
                        break;

                    case 5:
                        try {
                            System.out.println("\n--- Add Trainer ---");
                            System.out.print("Trainer ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            int tid = sc.nextInt();
                            System.out.print("Name: "); String tn = sc.next();
                            System.out.print("Specialty: "); String ts = sc.next();
                            System.out.print("Phone: "); String tp = sc.next();
                            trainerList.add(new Trainer(tid, tn, ts, tp));
                            admin.addTrainer();
                            System.out.println("Trainer added successfully!");
                        } catch (Exception e) {
                            System.out.println("ERROR: Failed to add trainer - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 6:
                        if (trainerList.isEmpty()) System.out.println("No trainers found.");
                        else { for (Trainer t : trainerList) t.showTrainer(); }
                        break;

                    case 7:
                        try {
                            System.out.print("Enter Member ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            Member m7 = findMember(sc.nextInt());
                            if (m7 != null && !trainerList.isEmpty()) {
                                System.out.println("Available Trainers:");
                                for (Trainer t : trainerList)
                                    System.out.println("ID: " + t.getTrainerId() + " | Name: " + t.getName());
                                System.out.print("Enter Trainer ID to assign: ");
                                if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                                int selectedTid = sc.nextInt();
                                Trainer selectedTrainer = null;
                                for (Trainer t : trainerList) {
                                    if (t.getTrainerId() == selectedTid) { selectedTrainer = t; break; }
                                }
                                if (selectedTrainer != null) {
                                    m7.assignTrainer(selectedTrainer);
                                    System.out.println("Success: Trainer " + selectedTrainer.getName() + " assigned!");
                                } else System.out.println("ERROR: Trainer ID not found!");
                            } else System.out.println("ERROR: Member not found or Trainer list is empty!");
                        } catch (Exception e) {
                            System.out.println("ERROR: Trainer assignment failed - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 8:
                        try {
                            System.out.println("\n--- Create Plan ---");
                            System.out.print("Plan ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: Plan ID must be a number!"); sc.next(); break; }
                            int pid = sc.nextInt();
                            System.out.print("Plan Name: "); String pn = sc.next();
                            System.out.print("Duration (Months): ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: Duration must be a number!"); sc.next(); break; }
                            int dur = sc.nextInt();
                            if (dur <= 0) { System.out.println("ERROR: Duration must be greater than 0!"); break; }
                            System.out.print("Fee: ");
                            if (!sc.hasNextDouble()) { System.out.println("ERROR: Fee must be a valid number!"); sc.next(); break; }
                            double fee = sc.nextDouble();
                            if (fee < 0) { System.out.println("ERROR: Fee cannot be negative!"); break; }
                            planList.add(new MemberShip(pid, pn, dur, fee, ""));
                            admin.createPlan();
                            System.out.println("SUCCESS: Plan '" + pn + "' created successfully.");
                        } catch (Exception e) {
                            System.out.println("ERROR: Invalid input - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 9:
                        try {
                            System.out.print("Enter Member ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            Member m9 = findMember(sc.nextInt());
                            if (m9 != null && !planList.isEmpty()) {
                                System.out.println("Available Membership Plans:");
                                for (MemberShip plan : planList)
                                    System.out.println("ID: " + plan.planId + " | Name: " + plan.planName + " | Fee: BDT " + plan.fee);
                                System.out.print("Enter Plan ID to assign: ");
                                if (!sc.hasNextInt()) { System.out.println("ERROR: Plan ID must be a number!"); sc.next(); break; }
                                int selectedPid = sc.nextInt();
                                MemberShip selectedPlan = null;
                                for (MemberShip ms : planList) {
                                    if (ms.planId == selectedPid) { selectedPlan = ms; break; }
                                }
                                if (selectedPlan != null) {
                                    m9.assignMembership(selectedPlan);
                                    System.out.println("Success: Plan '" + selectedPlan.planName + "' assigned to " + m9.getName());
                                } else System.out.println("ERROR: Plan ID not found!");
                            } else System.out.println("ERROR: Member not found or Plan list is empty!");
                        } catch (Exception e) {
                            System.out.println("ERROR: Membership assignment failed - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 10:
                        try {
                            System.out.print("Enter Member ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            Member m10 = findMember(sc.nextInt());
                            if (m10 != null && m10.membership != null) {
                                m10.membership.checkExpiry();
                            } else System.out.println("ERROR: Member has no active plan.");
                        } catch (Exception e) {
                            System.out.println("ERROR: Expiry check failed - " + e.getMessage());
                        }
                        break;

                    case 11:
                        try {
                            System.out.print("Enter Member ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            Member m11 = findMember(sc.nextInt());
                            if (m11 != null && m11.membership != null) {
                                m11.membership.renewMembership();
                            } else System.out.println("ERROR: Nothing to renew. Member not found or no plan assigned.");
                        } catch (Exception e) {
                            System.out.println("ERROR: Renewal failed - " + e.getMessage());
                        }
                        break;

                    case 12:
                        try {
                            System.out.println("\n--- Record Payment ---");
                            System.out.print("Payment ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: Payment ID must be a number!"); sc.next(); break; }
                            int pyId = sc.nextInt();
                            System.out.print("Member ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: Member ID must be a number!"); sc.next(); break; }
                            int pyMid = sc.nextInt();
                            Member payMember = findMember(pyMid);
                            String memberName = (payMember != null) ? payMember.getName() : "Unknown";
                            System.out.print("Amount: ");
                            if (!sc.hasNextDouble()) { System.out.println("ERROR: Amount must be a valid number!"); sc.next(); break; }
                            double pyAmt = sc.nextDouble();
                            if (pyAmt <= 0) { System.out.println("ERROR: Amount must be greater than 0!"); break; }
                            Payment p12 = new Payment(pyId, pyMid, pyAmt, memberName);
                            paymentList.add(p12);
                            p12.recordPayment();
                        } catch (Exception e) {
                            System.out.println("ERROR: Payment recording failed - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 13:
                        if (paymentList.isEmpty()) System.out.println("No payment records found.");
                        else {
                            for (Payment ph : paymentList) {
                                System.out.println("--- Payment Record ---");
                                ph.showPaymentHistory();
                                System.out.println("----------------------");
                            }
                        }
                        break;

                    case 14:
                        if (!paymentList.isEmpty()) {
                            paymentList.get(paymentList.size() - 1).generateReceipt();
                        } else System.out.println("ERROR: No payments made yet.");
                        break;

                    case 15:
                        try {
                            System.out.println("\n--- Member Check-In ---");
                            System.out.print("Member ID: ");
                            if (!sc.hasNextInt()) { System.out.println("ERROR: ID must be a number!"); sc.next(); break; }
                            int attMid = sc.nextInt();
                            Member attMember = findMember(attMid);
                            if (attMember == null) {
                                System.out.println("ERROR: Member ID " + attMid + " not found!");
                                break;
                            }
                            Attendance a15 = new Attendance(attendanceList.size() + 1, attMid);
                            attendanceList.add(a15);
                            a15.checkIn();
                        } catch (Exception e) {
                            System.out.println("ERROR: Check-in failed - " + e.getMessage());
                        }
                        break;

                    case 16:
                        if (attendanceList.isEmpty()) System.out.println("No attendance records found.");
                        else { for (Attendance ah : attendanceList) ah.showAttendanceHistory(); }
                        break;

                    case 17:
                        try {
                            System.out.println("\n--- BMI Calculator ---");
                            System.out.print("Height (cm): ");
                            if (!sc.hasNextDouble()) { System.out.println("ERROR: Height must be a number!"); sc.next(); break; }
                            double h = sc.nextDouble();
                            if (h <= 0) { System.out.println("ERROR: Height must be greater than 0!"); break; }
                            System.out.print("Weight (kg): ");
                            if (!sc.hasNextDouble()) { System.out.println("ERROR: Weight must be a number!"); sc.next(); break; }
                            double w = sc.nextDouble();
                            if (w <= 0) { System.out.println("ERROR: Weight must be greater than 0!"); break; }
                            double bmiVal = BMI.calculateBMI(h / 100, w);
                            System.out.println("BMI Value : " + String.format("%.2f", bmiVal));
                            System.out.println("Category  : " + BMI.getBMICategory(bmiVal));
                        } catch (Exception e) {
                            System.out.println("ERROR: BMI calculation failed - " + e.getMessage());
                            sc.nextLine();
                        }
                        break;

                    case 18:
                        try {
                            System.out.print("Username: "); String uInput = sc.next();
                            System.out.print("Password: "); String pInput = sc.next();
                            if (authManager.login(uInput, pInput)) {
                                admin.login();
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR: Login failed - " + e.getMessage());
                        }
                        break;

                    case 19:
                        authManager.logout();
                        break;

                    case 20:
                        try {
                            System.out.println("\n--- New User Signup ---");
                            System.out.print("Set Username: "); String newUser = sc.next();
                            System.out.print("Set Password: "); String newPass = sc.next();
                            authManager.signup(newUser, newPass);
                        } catch (Exception e) {
                            System.out.println("ERROR: Signup failed - " + e.getMessage());
                        }
                        break;

                    case 21:
                        System.out.println("Shutting down... Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid Choice! Please enter 1-21.");
                }
            } catch (Exception e) {
                System.out.println("\n!!! SYSTEM ALERT: " + e.getMessage() + " !!!");
                sc.nextLine();
                choice = 0;
            }
        } while (choice != 21);

        sc.close();
    }
}
