import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GymGUI extends JFrame {

    private List<Member>     memberList     = new ArrayList<>();
    private List<Trainer>    trainerList    = new ArrayList<>();
    private List<Payment>    paymentList    = new ArrayList<>();
    private List<Attendance> attendanceList = new ArrayList<>();
    private List<MemberShip> planList       = new ArrayList<>();
    private AuthManager authManager = new AuthManager();
    private Admin admin = new Admin(1, "admin", "1234");
    private JPanel mainContainer;
    private JPanel loginScreen;
    private JPanel signupScreen;   
    private JPanel dashboardScreen;
    private JTextArea outputBox;

    private JTextField  usernameField;
    private JPasswordField passwordField;

    private JTextField  signupUsernameField;
    private JPasswordField signupPasswordField;

    private static final Color BG       = new Color(0x121212);
    private static final Color CARD     = new Color(0x1F1F1F);
    private static final Color BORDER   = new Color(0x2D2D2D);
    private static final Color ACCENT   = new Color(0x00ADB5);
    private static final Color DANGER   = new Color(0xFF2E63);
    private static final Color SUCCESS  = new Color(0x00C897);
    private static final Color FG       = new Color(0xEEEEEE);
    private static final Color FG_DIM   = new Color(0xBBBBBB);

    public GymGUI() {
        setTitle("Gym Management System");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainContainer = new JPanel(new CardLayout());
        initLoginScreen();
        initSignupScreen();
        initDashboardScreen();

        mainContainer.add(loginScreen,    "LOGIN_LAYER");
        mainContainer.add(signupScreen,   "SIGNUP_LAYER");
        mainContainer.add(dashboardScreen,"DASHBOARD_LAYER");

        add(mainContainer);
        showLayer("LOGIN_LAYER");
    }

    private void initLoginScreen() {
        loginScreen = new JPanel(null);
        loginScreen.setBackground(BG);

        JPanel card = new JPanel(null);
        card.setBounds(300, 110, 420, 420);
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createLineBorder(BORDER, 1));

        JLabel title = makeLabel("GYM SYSTEM LOGIN", 22, Font.BOLD, ACCENT, SwingConstants.CENTER);
        title.setBounds(20, 30, 380, 35);
        card.add(title);

        JLabel sub = makeLabel("Enter your credentials to continue", 12, Font.PLAIN, FG_DIM, SwingConstants.CENTER);
        sub.setBounds(20, 68, 380, 20);
        card.add(sub);

        card.add(makeLabel("Username", 12, Font.PLAIN, FG, -1)).setBounds(40, 110, 340, 18);

        usernameField = new JTextField();
        styleField(usernameField);
        usernameField.setBounds(40, 132, 340, 40);
        card.add(usernameField);

        card.add(makeLabel("Password", 12, Font.PLAIN, FG, -1)).setBounds(40, 185, 340, 18);

        passwordField = new JPasswordField();
        styleField(passwordField);
        passwordField.setBounds(40, 207, 340, 40);
        card.add(passwordField);

        JButton loginBtn = makeButton("Login", ACCENT);
        loginBtn.setBounds(40, 270, 340, 42);
        loginBtn.addActionListener(e -> handleLogin());
        card.add(loginBtn);

        JButton goSignup = makeButton("New User? Sign Up Here", new Color(0x333333));
        goSignup.setBounds(40, 325, 340, 38);
        goSignup.addActionListener(e -> showLayer("SIGNUP_LAYER"));
        card.add(goSignup);

        loginScreen.add(card);
    }


    private void initSignupScreen() {
        signupScreen = new JPanel(null);
        signupScreen.setBackground(BG);

        JPanel card = new JPanel(null);
        card.setBounds(300, 110, 420, 400);
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createLineBorder(BORDER, 1));

        JLabel title = makeLabel("CREATE NEW ACCOUNT", 22, Font.BOLD, SUCCESS, SwingConstants.CENTER);
        title.setBounds(20, 30, 380, 35);
        card.add(title);

        JLabel sub = makeLabel("Set your username and password", 12, Font.PLAIN, FG_DIM, SwingConstants.CENTER);
        sub.setBounds(20, 68, 380, 20);
        card.add(sub);

        card.add(makeLabel("Choose Username", 12, Font.PLAIN, FG, -1)).setBounds(40, 105, 340, 18);

        signupUsernameField = new JTextField();
        styleField(signupUsernameField);
        signupUsernameField.setBounds(40, 127, 340, 40);
        card.add(signupUsernameField);

        card.add(makeLabel("Choose Password", 12, Font.PLAIN, FG, -1)).setBounds(40, 180, 340, 18);

        signupPasswordField = new JPasswordField();
        styleField(signupPasswordField);
        signupPasswordField.setBounds(40, 202, 340, 40);
        card.add(signupPasswordField);

        JButton signupBtn = makeButton("Create Account", SUCCESS);
        signupBtn.setBounds(40, 263, 340, 42);
        signupBtn.addActionListener(e -> handleSignup());
        card.add(signupBtn);

        JButton backBtn = makeButton("Back to Login", new Color(0x333333));
        backBtn.setBounds(40, 318, 340, 38);
        backBtn.addActionListener(e -> showLayer("LOGIN_LAYER"));
        card.add(backBtn);

        signupScreen.add(card);
    }

    private void initDashboardScreen() {
        dashboardScreen = new JPanel(new BorderLayout(15, 15));
        dashboardScreen.setBackground(BG);
        dashboardScreen.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JPanel header = new JPanel(new GridLayout(2, 1, 4, 4));
        header.setBackground(CARD);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, ACCENT));
        JLabel h1 = makeLabel("GYM MANAGEMENT SYSTEM DASHBOARD", 24, Font.BOLD, ACCENT, SwingConstants.CENTER);
        JLabel h2 = makeLabel("Logged in | Active Session", 12, Font.PLAIN, FG_DIM, SwingConstants.CENTER);
        header.add(h1); header.add(h2);
        dashboardScreen.add(header, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(4, 5, 12, 12));
        gridPanel.setBackground(BG);

        gridPanel.add(makeCard("Member",      "Add Member",           "Add Member"));
        gridPanel.add(makeCard("Members",     "Show Members",         "Show Members"));
        gridPanel.add(makeCard("Member",      "Edit Member",          "Edit Member"));
        gridPanel.add(makeCard("Member",      "Delete Member",        "Delete Member"));
        gridPanel.add(makeCard("Trainer",     "Add Trainer",          "Add Trainer"));
        gridPanel.add(makeCard("Trainer",     "Show Trainers",        "Show Trainers"));
        gridPanel.add(makeCard("Trainer",     "Assign Trainer",       "Assign Trainer"));
        gridPanel.add(makeCard("Membership",  "Create Plan",          "Create Plan"));
        gridPanel.add(makeCard("Membership",  "Assign Membership",    "Assign Membership"));
        gridPanel.add(makeCard("Membership",  "Check Expiry",         "Check Expiry"));
        gridPanel.add(makeCard("Membership",  "Renew Membership",     "Renew Membership"));
        gridPanel.add(makeCard("Payment",     "Record Payment",       "Record Payment"));
        gridPanel.add(makeCard("Payment",     "Payment History",      "Payment History"));
        gridPanel.add(makeCard("Payment",     "Generate Receipt",     "Generate Receipt"));
        gridPanel.add(makeCard("Attendance",  "Member Check-In",      "Member Check-In"));
        gridPanel.add(makeCard("Attendance",  "Attendance History",   "Attendance History"));
        gridPanel.add(makeCard("Health",      "Calculate BMI",        "BMI"));
        gridPanel.add(makeCard("Account",     "Login",                "Login GUI"));
        gridPanel.add(makeCard("Account",     "Logout",               "Logout GUI"));
        gridPanel.add(makeCard("Account",     "Signup",               "Signup GUI"));

        JPanel logPanel = new JPanel(new BorderLayout(5, 5));
        logPanel.setBackground(BG);
        JLabel logLabel = makeLabel("Console Output", 13, Font.BOLD, ACCENT, -1);
        logPanel.add(logLabel, BorderLayout.NORTH);

        outputBox = new JTextArea("Welcome! Select an operation from the buttons above.");
        outputBox.setEditable(false);
        outputBox.setBackground(CARD);
        outputBox.setForeground(Color.WHITE);
        outputBox.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputBox.setLineWrap(true);
        outputBox.setWrapStyleWord(true);
        outputBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, ACCENT),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        JScrollPane scroll = new JScrollPane(outputBox);
        scroll.setPreferredSize(new Dimension(900, 145));
        scroll.setBorder(BorderFactory.createLineBorder(BORDER));
        logPanel.add(scroll, BorderLayout.CENTER);

        JButton logoutBtn = makeButton("Logout System", DANGER);
        logoutBtn.setPreferredSize(new Dimension(900, 38));
        logoutBtn.addActionListener(e -> handleLogoutBtn());
        logPanel.add(logoutBtn, BorderLayout.SOUTH);

        JPanel center = new JPanel(new BorderLayout(10, 12));
        center.setBackground(BG);
        center.add(gridPanel, BorderLayout.CENTER);
        center.add(logPanel,  BorderLayout.SOUTH);
        dashboardScreen.add(center, BorderLayout.CENTER);
    }

    private JLabel makeLabel(String text, int size, int style, Color color, int align) {
        JLabel l = align >= 0 ? new JLabel(text, align) : new JLabel(text);
        l.setFont(new Font("Segoe UI", style, size));
        l.setForeground(color);
        return l;
    }

    private JButton makeButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    private void styleField(JTextField f) {
        f.setBackground(BORDER);
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x3D3D3D)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
    }

    private JPanel makeCard(String category, String btnText, String command) {
        JPanel card = new JPanel(new BorderLayout(6, 6));
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        JLabel lbl = makeLabel(category, 11, Font.PLAIN, FG_DIM, SwingConstants.CENTER);
        card.add(lbl, BorderLayout.NORTH);
        JButton btn = makeButton(btnText, ACCENT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.addActionListener((ActionEvent e) -> executeAction(command));
        card.add(btn, BorderLayout.SOUTH);
        return card;
    }

    private void showLayer(String name) {
        ((CardLayout) mainContainer.getLayout()).show(mainContainer, name);
    }

    private void log(String msg) {
        outputBox.setText(msg);
    }

    private String nowDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private String nowTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    private String input(String prompt) {
        return JOptionPane.showInputDialog(this, prompt);
    }

    private void handleLogin() {
        try {
            String u = usernameField.getText().trim();
            String p = new String(passwordField.getPassword()).trim();
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (authManager.login(u, p)) {
                JOptionPane.showMessageDialog(this, "Welcome, " + u + "! Access Granted.", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                showLayer("DASHBOARD_LAYER");
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username or password!\nIf new user, please Sign Up first.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Login Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSignup() {
        try {
            String u = signupUsernameField.getText().trim();
            String p = new String(signupPasswordField.getPassword()).trim();
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (authManager.signup(u, p)) {
                JOptionPane.showMessageDialog(this, "Account created for '" + u + "'!\nYou can now log in.", "Signup Success", JOptionPane.INFORMATION_MESSAGE);
                signupUsernameField.setText("");
                signupPasswordField.setText("");
                showLayer("LOGIN_LAYER");
            } else {
                JOptionPane.showMessageDialog(this, "Username '" + u + "' already exists!\nChoose a different username.", "Signup Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Signup Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLogoutBtn() {
        authManager.logout();
        usernameField.setText("");
        passwordField.setText("");
        log("Logged out successfully.");
        showLayer("LOGIN_LAYER");
    }

    private void executeAction(String command) {
        try {
            switch (command) {

                case "Add Member": {
                    String idStr  = input("Enter Member ID (number):");
                    if (idStr == null) return;
                    String name   = input("Enter Name:");
                    String ageStr = input("Enter Age (number):");
                    String gender = input("Enter Gender (Male/Female):");
                    String phone  = input("Enter Phone Number:");
                    if (name == null || ageStr == null || gender == null || phone == null) return;
                    int id  = Integer.parseInt(idStr.trim());
                    int age = Integer.parseInt(ageStr.trim());
                    if (age <= 0 || age > 120) { log("[ERROR] Invalid age. Must be between 1 and 120."); return; }
                    for (Member m : memberList) {
                        if (m.memberId == id) { log("[ERROR] Member ID " + id + " already exists!"); return; }
                    }
                    memberList.add(new Member(id, name.trim(), age, gender.trim(), phone.trim()));
                    admin.addMember();
                    log("[SUCCESS] Member added!\nID     : " + id + "\nName   : " + name +
                            "\nAge    : " + age + "\nGender : " + gender + "\nPhone  : " + phone);
                    break;
                }

                case "Show Members": {
                    if (memberList.isEmpty()) { log("Database is empty! No members found."); return; }
                    StringBuilder sb = new StringBuilder("=== GYM MEMBER LIST ===\n");
                    for (Member m : memberList)
                        sb.append(String.format("ID: %-4d | Name: %-15s | Age: %-3d | Gender: %-7s | Phone: %s\n",
                                m.memberId, m.name, m.age, m.gender, m.phone));
                    log(sb.toString());
                    break;
                }

                case "Edit Member": {
                    String eidStr = input("Enter Member ID to edit:");
                    if (eidStr == null) return;
                    int eid = Integer.parseInt(eidStr.trim());
                    Member em = null;
                    for (Member m : memberList) if (m.memberId == eid) { em = m; break; }
                    if (em == null) { log("[ERROR] Member ID " + eid + " not found!"); return; }

                    String newName   = input("New Name (current: " + em.name + "):");
                    String newAgeStr = input("New Age (current: " + em.age + "):");
                    String newGender = input("New Gender (current: " + em.gender + "):");
                    String newPhone  = input("New Phone (current: " + em.phone + "):");
                    if (newName == null || newAgeStr == null || newGender == null || newPhone == null) return;

                    int newAge = Integer.parseInt(newAgeStr.trim());
                    if (newAge <= 0 || newAge > 120) { log("[ERROR] Invalid age!"); return; }
                    em.setName(newName.trim()); em.setAge(newAge);
                    em.setGender(newGender.trim()); em.setPhone(newPhone.trim());
                    log("[SUCCESS] Member updated!\nID     : " + em.memberId + "\nName   : " + em.name +
                            "\nAge    : " + em.age + "\nGender : " + em.gender + "\nPhone  : " + em.phone);
                    break;
                }

                case "Delete Member": {
                    String didStr = input("Enter Member ID to delete:");
                    if (didStr == null) return;
                    int did = Integer.parseInt(didStr.trim());
                    boolean removed = memberList.removeIf(m -> m.memberId == did);
                    if (removed) { admin.deleteMember(); log("[SUCCESS] Member ID " + did + " deleted."); }
                    else log("[ERROR] Member ID " + did + " not found!");
                    break;
                }

                case "Add Trainer": {
                    String tidStr = input("Trainer ID (number):");
                    String tname  = input("Trainer Name:");
                    String tspec  = input("Specialty:");
                    String tphone = input("Phone:");
                    if (tidStr == null || tname == null || tspec == null || tphone == null) return;
                    int tid = Integer.parseInt(tidStr.trim());
                    trainerList.add(new Trainer(tid, tname.trim(), tspec.trim(), tphone.trim()));
                    admin.addTrainer();
                    log("[SUCCESS] Trainer added!\nID        : " + tid + "\nName      : " + tname +
                            "\nSpecialty : " + tspec + "\nPhone     : " + tphone);
                    break;
                }

                case "Show Trainers": {
                    if (trainerList.isEmpty()) { log("No trainers found."); return; }
                    StringBuilder sb = new StringBuilder("=== TRAINER LIST ===\n");
                    for (Trainer t : trainerList)
                        sb.append(String.format("ID: %-4d | Name: %-15s | Specialty: %s\n",
                                t.getTrainerId(), t.getName(), t.getSpecialty()));
                    log(sb.toString());
                    break;
                }

                case "Assign Trainer": {
                    if (memberList.isEmpty())  { log("[ERROR] No members found!"); return; }
                    if (trainerList.isEmpty()) { log("[ERROR] No trainers found!"); return; }
                    String midStr = input("Enter Member ID:");
                    if (midStr == null) return;
                    int mid = Integer.parseInt(midStr.trim());
                    Member tm = null;
                    for (Member m : memberList) if (m.memberId == mid) { tm = m; break; }
                    if (tm == null) { log("[ERROR] Member ID " + mid + " not found!"); return; }

                    StringBuilder sb = new StringBuilder("Available Trainers:\n");
                    for (Trainer t : trainerList)
                        sb.append("ID: ").append(t.getTrainerId()).append(" | Name: ").append(t.getName()).append("\n");
                    String tidStr = input(sb + "\nEnter Trainer ID to assign:");
                    if (tidStr == null) return;
                    int tidAss = Integer.parseInt(tidStr.trim());
                    Trainer found = null;
                    for (Trainer t : trainerList) if (t.getTrainerId() == tidAss) { found = t; break; }
                    if (found != null) {
                        tm.assignTrainer(found);
                        log("[SUCCESS] Trainer '" + found.getName() + "' assigned to Member '" + tm.getName() + "'.");
                    } else log("[ERROR] Trainer ID " + tidAss + " not found!");
                    break;
                }

                case "Create Plan": {
                    String pidStr  = input("Plan ID (number):");
                    String pname   = input("Plan Name:");
                    String durStr  = input("Duration (months):");
                    String feeStr  = input("Fee (BDT):");
                    if (pidStr == null || pname == null || durStr == null || feeStr == null) return;
                    int planId = Integer.parseInt(pidStr.trim());
                    int dur    = Integer.parseInt(durStr.trim());
                    double fee = Double.parseDouble(feeStr.trim());
                    if (dur <= 0)  { log("[ERROR] Duration must be > 0!"); return; }
                    if (fee < 0)   { log("[ERROR] Fee cannot be negative!"); return; }
                    MemberShip newPlan = new MemberShip(planId, pname.trim(), dur, fee, "");
                    planList.add(newPlan);
                    admin.createPlan();
                    log("[SUCCESS] Plan created!\nID      : " + planId + "\nName    : " + pname +
                            "\nMonths  : " + dur + "\nFee     : BDT " + fee + "\nExpiry  : " + newPlan.expiryDate);
                    break;
                }

                case "Assign Membership": {
                    if (memberList.isEmpty()) { log("[ERROR] No members found!"); return; }
                    if (planList.isEmpty())   { log("[ERROR] No plans created yet!"); return; }
                    String midStr = input("Enter Member ID:");
                    if (midStr == null) return;
                    int mid = Integer.parseInt(midStr.trim());
                    Member am = null;
                    for (Member m : memberList) if (m.memberId == mid) { am = m; break; }
                    if (am == null) { log("[ERROR] Member ID " + mid + " not found!"); return; }

                    StringBuilder sb = new StringBuilder("Available Plans:\n");
                    for (MemberShip pl : planList)
                        sb.append("ID: ").append(pl.planId).append(" | ").append(pl.planName)
                                .append(" | BDT ").append(pl.fee).append("\n");
                    String pidStr = input(sb + "\nEnter Plan ID to assign:");
                    if (pidStr == null) return;
                    int planId = Integer.parseInt(pidStr.trim());
                    MemberShip chosen = null;
                    for (MemberShip pl : planList) if (pl.planId == planId) { chosen = pl; break; }
                    if (chosen != null) {
                        am.assignMembership(chosen);
                        log("[SUCCESS] Plan '" + chosen.planName + "' assigned to " + am.getName() +
                                "\nExpiry Date: " + chosen.expiryDate);
                    } else log("[ERROR] Plan ID " + planId + " not found!");
                    break;
                }

                case "Check Expiry": {
                    String midStr = input("Enter Member ID:");
                    if (midStr == null) return;
                    int mid = Integer.parseInt(midStr.trim());
                    Member cem = null;
                    for (Member m : memberList) if (m.memberId == mid) { cem = m; break; }
                    if (cem == null)             { log("[ERROR] Member ID " + mid + " not found!"); return; }
                    if (cem.membership == null)  { log("[ERROR] Member has no active plan!"); return; }
                    MemberShip ms = cem.membership;
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate expiry = LocalDate.parse(ms.expiryDate, fmt);
                    LocalDate today  = LocalDate.now();
                    String status = today.isAfter(expiry) ? "*** EXPIRED ***" : "ACTIVE";
                    log("Plan Name   : " + ms.planName + "\nExpiry Date : " + ms.expiryDate +
                            "\nToday       : " + today.format(fmt) + "\nStatus      : " + status);
                    break;
                }

                case "Renew Membership": {
                    String midStr = input("Enter Member ID:");
                    if (midStr == null) return;
                    int mid = Integer.parseInt(midStr.trim());
                    Member rm = null;
                    for (Member m : memberList) if (m.memberId == mid) { rm = m; break; }
                    if (rm == null)            { log("[ERROR] Member ID " + mid + " not found!"); return; }
                    if (rm.membership == null) { log("[ERROR] Member has no plan to renew!"); return; }
                    rm.membership.renewMembership();
                    log("[SUCCESS] Membership renewed!\nPlan       : " + rm.membership.planName +
                            "\nNew Expiry : " + rm.membership.expiryDate);
                    break;
                }

                case "Record Payment": {
                    String pyIdStr  = input("Payment ID (number):");
                    String pyMidStr = input("Member ID:");
                    String pyAmt    = input("Amount (BDT):");
                    if (pyIdStr == null || pyMidStr == null || pyAmt == null) return;
                    int pyId  = Integer.parseInt(pyIdStr.trim());
                    int pyMid = Integer.parseInt(pyMidStr.trim());
                    double amt = Double.parseDouble(pyAmt.trim());
                    if (amt <= 0) { log("[ERROR] Amount must be > 0!"); return; }
                    Member pm = null;
                    for (Member m : memberList) if (m.memberId == pyMid) { pm = m; break; }
                    String mName = (pm != null) ? pm.getName() : "Unknown";
                    Payment py = new Payment(pyId, pyMid, amt, mName);
                    paymentList.add(py);
                    py.recordPayment();
                    log("[SUCCESS] Payment recorded!\nPayment ID  : " + pyId + "\nMember      : " + mName +
                            "\nAmount      : BDT " + amt + "\nDate        : " + nowDate() +
                            "\nTime        : " + nowTime());
                    break;
                }

                case "Payment History": {
                    displayPaymentHistory();
                    break;
                }

                case "Generate Receipt": {
                    if (paymentList.isEmpty()) { log("[ERROR] No payments made yet."); return; }
                    Payment last = paymentList.get(paymentList.size() - 1);
                    last.generateReceipt();
                    log("========== PAYMENT RECEIPT ==========\n" +
                            "Latest payment receipt generated.\n" +
                            "Date : " + nowDate() + "\nTime : " + nowTime() +
                            "\n(See terminal/console for full details)");
                    break;
                }

                case "Member Check-In": {
                    String attStr = input("Enter Member ID:");
                    if (attStr == null) return;
                    int attMid = Integer.parseInt(attStr.trim());
                    Member attM = null;
                    for (Member m : memberList) if (m.memberId == attMid) { attM = m; break; }
                    if (attM == null) { log("[ERROR] Member ID " + attMid + " not found!"); return; }
                    Attendance att = new Attendance(attendanceList.size() + 1, attMid);
                    attendanceList.add(att);
                    att.checkIn();
                    log("[SUCCESS] Check-In Recorded!\nMember    : " + attM.getName() +
                            "\nMember ID : " + attMid + "\nDate      : " + nowDate() +
                            "\nTime      : " + nowTime());
                    break;
                }

                case "Attendance History": {
                    if (attendanceList.isEmpty()) { log("No attendance records found."); return; }
                    StringBuilder sb = new StringBuilder("=== ATTENDANCE HISTORY ===\n\n");
                    for (Attendance ah : attendanceList)
                        sb.append(String.format("Member ID : %-5d | Date : %-12s | Time : %s\n",
                                ah.getMemberId(), ah.getDate(), ah.getCheckInTime()));
                    log(sb.toString());
                    break;
                }

                case "BMI": {
                    String hStr = input("Enter Height (cm):");
                    String wStr = input("Enter Weight (kg):");
                    if (hStr == null || wStr == null) return;
                    double h = Double.parseDouble(hStr.trim());
                    double w = Double.parseDouble(wStr.trim());
                    if (h <= 0 || w <= 0) { log("[ERROR] Height and Weight must be positive!"); return; }
                    double bmi = BMI.calculateBMI(h / 100.0, w);
                    String cat = BMI.getBMICategory(bmi);
                    log("=== BMI RESULT ===\nHeight   : " + h + " cm\nWeight   : " + w + " kg\n" +
                            "BMI      : " + String.format("%.2f", bmi) + "\nCategory : " + cat);
                    break;
                }

                case "Login GUI": {
                    String u = input("Enter Username:");
                    String p = input("Enter Password:");
                    if (u == null || p == null) return;
                    if (authManager.login(u.trim(), p.trim())) {
                        log("[SUCCESS] Logged in as: " + u);
                        JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + u, "Login", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        log("[ERROR] Login failed. Wrong credentials or user not found.");
                    }
                    break;
                }

                case "Logout GUI": {
                    authManager.logout();
                    log("[LOGOUT] Session ended. Returning to login screen.");
                    usernameField.setText("");
                    passwordField.setText("");
                    showLayer("LOGIN_LAYER");
                    break;
                }

                case "Signup GUI": {
                    String nu = input("Choose Username:");
                    String np = input("Choose Password:");
                    if (nu == null || np == null) return;
                    if (authManager.signup(nu.trim(), np.trim())) {
                        log("[SUCCESS] Account created for '" + nu + "'. You can now log in.");
                        JOptionPane.showMessageDialog(this, "Signup successful!\nUsername: " + nu, "Signup", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        log("[ERROR] Username '" + nu + "' already taken. Choose another.");
                    }
                    break;
                }

                default:
                    log("[LOG] Action '" + command + "' triggered.");
            }
        } catch (NumberFormatException ex) {
            log("[ERROR] Invalid number entered! Please enter correct numeric values.\nDetails: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Invalid number format!\n" + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            log("[ERROR] Operation failed: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayPaymentHistory() {
        if (paymentList.isEmpty()) { log("No payment records found."); return; }
        StringBuilder sb = new StringBuilder("=== PAYMENT HISTORY ===\n\n");
        sb.append("Total Payments: ").append(paymentList.size()).append("\n\n");
        sb.append("(Each payment auto-stamped with real date & time at time of recording)\n");
        sb.append("Use 'Generate Receipt' to see the most recent payment detail.");
        log(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GymGUI().setVisible(true));
    }
}
