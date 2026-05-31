import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

// Custom Core Structure Models (Class Diagram Context Check)
class Member {
    String id, name, age, phone;
    public Member(String id, String name, String age, String phone) {
        this.id = id; this.name = name; this.age = age; this.phone = phone;
    }
}

public class GymGUI extends JFrame {
    // Structural Storage Layer Context
    private ArrayList<Member> memberList = new ArrayList<>();
    
    // UI Cards/Layout Frames Components
    private JPanel mainContainer;
    private JPanel loginScreen;
    private JPanel dashboardScreen;
    private JTextArea outputBox;
    
    // Login Text Fields
    private JTextField usernameField;
    private JPasswordField passwordField;

    public GymGUI() {
        // App Frame Base Configurations
        setTitle("Gym Management System - Core Dashboard");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Core Container Initialization
        mainContainer = new JPanel(new CardLayout());
        
        // Screens Build Initialization
        initLoginScreen();
        initDashboardScreen();

        // Screen Mounting Controls
        mainContainer.add(loginScreen, "LOGIN_LAYER");
        mainContainer.add(dashboardScreen, "DASHBOARD_LAYER");
        
        add(mainContainer);
        showLayer("LOGIN_LAYER"); // Default UI Mount State
    }

    // 1. LOGIN SCREEN FRAME WORK
    private void initLoginScreen() {
        loginScreen = new JPanel(null);
        loginScreen.setBackground(new Color(0x121212));

        JPanel card = new JPanel(null);
        card.setBounds(275, 120, 400, 350);
        card.setBackground(new Color(0x1F1F1F));
        card.setBorder(BorderFactory.createLineBorder(new Color(0x2D2D2D), 1));

        JLabel title = new JLabel("Gym System Login", SwingConstants.CENTER);
        title.setBounds(20, 30, 360, 35);
        title.setForeground(new Color(0x00ADB5));
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        card.add(title);

        // Username Field Structure
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(40, 95, 320, 20);
        userLabel.setForeground(new Color(0xEEEEEE));
        card.add(userLabel);

        usernameField = new JTextField("admin");
        usernameField.setBounds(40, 120, 320, 40);
        usernameField.setBackground(new Color(0x2D2D2D));
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0x3D3D3D)));
        card.add(usernameField);

        // Password Field Structure
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(40, 175, 320, 20);
        passLabel.setForeground(new Color(0xEEEEEE));
        card.add(passLabel);

        passwordField = new JPasswordField("1234");
        passwordField.setBounds(40, 200, 320, 40);
        passwordField.setBackground(new Color(0x2D2D2D));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0x3D3D3D)));
        card.add(passwordField);

        // Login Submit Control Button
        JButton loginBtn = createStyledButton("Login System", new Color(0x00ADB5));
        loginBtn.setBounds(40, 270, 320, 42);
        loginBtn.addActionListener(e -> handleLogin());
        card.add(loginBtn);

        loginScreen.add(card);
    }

    // 2. MAIN SYSTEM DASHBOARD LAYER WORK
    private void initDashboardScreen() {
        dashboardScreen = new JPanel(new BorderLayout(15, 15));
        dashboardScreen.setBackground(new Color(0x121212));
        dashboardScreen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header Panel Components
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headerPanel.setBackground(new Color(0x1F1F1F));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(0x00ADB5)));
        
        JLabel h1 = new JLabel("GYM MANAGEMENT SYSTEM DASHBOARD", SwingConstants.CENTER);
        h1.setFont(new Font("Segoe UI", Font.BOLD, 26));
        h1.setForeground(new Color(0x00ADB5));
        
        JLabel sub = new JLabel("Logged in as Admin | Active Session", SwingConstants.CENTER);
        sub.setForeground(new Color(0xBBBBBB));
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        headerPanel.add(h1);
        headerPanel.add(sub);
        dashboardScreen.add(headerPanel, BorderLayout.NORTH);

        // Center Grid Functional Control Panel View
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        gridPanel.setBackground(new Color(0x121212));

        gridPanel.add(createActionCard("Member Control", "Add New Member", "Add Member"));
        gridPanel.add(createActionCard("Database View", "Show Members", "Show Members"));
        gridPanel.add(createActionCard("Trainers Setup", "Add Trainer", "Add Trainer"));
        gridPanel.add(createActionCard("Health Tools", "Calculate BMI", "BMI"));
        gridPanel.add(createActionCard("Accounting", "Record Payment", "Record Payment"));
        gridPanel.add(createActionCard("Log Book", "Member Check-In", "Member Check-In"));

        // Central Layout Console Panel Wrapper
        JPanel centerWrapper = new JPanel(new BorderLayout(10, 10));
        centerWrapper.setBackground(new Color(0x121212));
        centerWrapper.add(gridPanel, BorderLayout.CENTER);

        // Console Logging Output Window View UI
        JPanel logPanel = new JPanel(new BorderLayout(5, 5));
        logPanel.setBackground(new Color(0x121212));
        
        JLabel logLabel = new JLabel("Console Log Output Window:");
        logLabel.setForeground(new Color(0x00ADB5));
        logLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logPanel.add(logLabel, BorderLayout.NORTH);

        outputBox = new JTextArea("Welcome to the central command node. Select an option from above...");
        outputBox.setEditable(false);
        outputBox.setBackground(new Color(0x1F1F1F));
        outputBox.setForeground(Color.WHITE);
        outputBox.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(0x00ADB5)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        
        JScrollPane scroll = new JScrollPane(outputBox);
        scroll.setPreferredSize(new Dimension(900, 150));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0x2D2D2D)));
        logPanel.add(scroll, BorderLayout.CENTER);

        // Logout Processing Action Trigger
        JButton logoutBtn = createStyledButton("Logout System", new Color(0xFF2E63));
        logoutBtn.setPreferredSize(new Dimension(900, 40));
        logoutBtn.addActionListener(e -> handleLogout());
        logPanel.add(logoutBtn, BorderLayout.SOUTH);

        centerWrapper.add(logPanel, BorderLayout.SOUTH);
        dashboardScreen.add(centerWrapper, BorderLayout.CENTER);
    }

    // Helper: Build Component Action Cards inside grid Layout 
    private JPanel createActionCard(String category, String btnText, String actionCommand) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(new Color(0x1F1F1F));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x2D2D2D)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel title = new JLabel(category, SwingConstants.CENTER);
        title.setForeground(new Color(0xEEEEEE));
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        card.add(title, BorderLayout.CENTER);

        JButton actionBtn = createStyledButton(btnText, new Color(0x00ADB5));
        actionBtn.addActionListener((ActionEvent e) -> executeAction(actionCommand));
        card.add(actionBtn, BorderLayout.SOUTH);

        return card;
    }

    // Custom Flat Button UI Wrapper Generator
    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Core Logical Functions: View Layer Management
    private void showLayer(String name) {
        CardLayout cl = (CardLayout) mainContainer.getLayout();
        cl.show(mainContainer, name);
    }

    // Validation Action: Authentication Guard System 
    private void handleLogin() {
        String u = usernameField.getText();
        String p = new String(passwordField.getPassword());

        if (u.equals("admin") && p.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Access Granted! Welcome Boss.", "Security Pass", JOptionPane.INFORMATION_MESSAGE);
            showLayer("DASHBOARD_LAYER");
        } else {
            JOptionPane.showMessageDialog(this, "ERROR: Access Denied! Wrong Username or Password.", "Security Violation", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLogout() {
        usernameField.setText("");
        passwordField.setText("");
        outputBox.setText("Logged out successfully.");
        showLayer("LOGIN_LAYER");
    }

    // Logic Architecture Actions Routing Process
    private void executeAction(String command) {
        switch (command) {
            case "Add Member":
                String id = JOptionPane.showInputDialog(this, "Enter Member ID:");
                if (id == null || id.trim().isEmpty()) return;
                
                String name = JOptionPane.showInputDialog(this, "Enter Name:");
                String age = JOptionPane.showInputDialog(this, "Enter Age:");
                String phone = JOptionPane.showInputDialog(this, "Enter Phone:");

                memberList.add(new Member(id, name, age, phone));
                outputBox.setText(String.format("[SUCCESS] Member added successfully!\nID: %s\nName: %s\nAge: %s\nPhone: %s", id, name, age, phone));
                break;

            case "Show Members":
                if (memberList.isEmpty()) {
                    outputBox.setText("Database is empty! No records found.");
                    return;
                }
                StringBuilder sb = new StringBuilder("=== Gym Active Members List ===\n");
                for (Member m : memberList) {
                    sb.append(String.format("ID: %s | Name: %s | Age: %s | Phone: %s\n", m.id, m.name, m.age, m.phone));
                }
                outputBox.setText(sb.toString());
                break;

            case "BMI":
                String hStr = JOptionPane.showInputDialog(this, "Enter Height (cm):");
                String wStr = JOptionPane.showInputDialog(this, "Enter Weight (kg):");
                
                if (hStr != null && wStr != null) {
                    try {
                        double h = Double.parseDouble(hStr);
                        double w = Double.parseDouble(wStr);
                        double heightInMeters = h / 100.0;
                        double bmi = w / (heightInMeters * heightInMeters);
                        String cat;

                        if (bmi < 18.5) cat = "Underweight";
                        else if (bmi < 25) cat = "Normal";
                        else if (bmi < 30) cat = "Overweight";
                        else cat = "Obese";

                        outputBox.setText(String.format("--- BMI Result ---\nBMI Value: %.2f\nCategory : %s", bmi, cat));
                    } catch (NumberFormatException ex) {
                        outputBox.setText("[ERROR] Invalid values entered for height or weight calculation.");
                    }
                }
                break;

            default:
                outputBox.setText(String.format("[LOG] %s process triggered successfully inside Swing Graphical environment.", command));
                break;
        }
    }

    public static void main(String[] args) {
        // Safe Thread Initialization Framework Launcher
        SwingUtilities.invokeLater(() -> new GymGUI().setVisible(true));
    }
}
