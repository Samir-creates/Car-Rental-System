
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RegistrationFrame extends JFrame {
    public RegistrationFrame() {
        super("Customer Registration");

        getContentPane().setBackground(new Color(179, 206, 229));

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("logo.jpg").getImage());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setOpaque(false);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 180, 30);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 100, 30);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 100, 180, 30);
        panel.add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 150, 100, 30);
        panel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 150, 180, 30);
        panel.add(passField);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 220, 100, 30);
        panel.add(registerBtn);

        registerBtn.addActionListener(_ -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,"All fields are required!");
                return;
            }

            try (FileWriter fw = new FileWriter("customers.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
                out.println(name + "," + email + "," + password);
                JOptionPane.showMessageDialog(this, "Registered successfully!");
                dispose(); // Close registration window
                new CarRent().setVisible(true); // Return to login
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
            }
        });

        this.add(panel);
        setVisible(true);
    }
}
