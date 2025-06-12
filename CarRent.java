
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CarRent extends JFrame implements ActionListener, MouseListener {
    ImageIcon img;
    JLabel userLabel, passLable, imgLable, registerLabel;
    JTextField userTF;
    JPasswordField passPF;
    JButton loginBtn, exitBtn;
    JPanel panel;
    Color myColor;
    Font myFont;

    public CarRent() {
        super("Car Rental System - Login");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon("logo.jpg").getImage());
        getContentPane().setBackground(new Color(179, 206, 229));
       
        

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setOpaque(false);

        userLabel = new JLabel("Email:");
        userLabel.setBounds(50, 100, 100, 30);
        panel.add(userLabel);

        userTF = new JTextField();
        userTF.setBounds(150, 100, 150, 30);
        panel.add(userTF);

        passLable = new JLabel("Password:");
        passLable.setBounds(50, 150, 100, 30);
        panel.add(passLable);

        passPF = new JPasswordField();
        passPF.setBounds(150, 150, 150, 30);
        panel.add(passPF);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 200, 100, 30);
        loginBtn.addActionListener(this);
        panel.add(loginBtn);

        registerLabel = new JLabel("<html>No account? <a href=''>Register here</a></html>");
        registerLabel.setBounds(130, 260, 200, 30);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new RegistrationFrame();
            }
        });
        panel.add(registerLabel);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        String username = userTF.getText();
        String password = new String(passPF.getPassword());

        if(checkCredntials(username,password)) {
            JOptionPane.showMessageDialog(this,"Login Successful");
            this.dispose();
            new CarRentalGUI().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
        
    }
    private boolean checkCredntials(String username,String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("customers.txt"))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length>= 3 && parts[1].equals(username) && parts[2].equals(password)) {
                    return true;
                }
            }
        } catch(IOException e) {
            //File not found means no registered users yet
        }
        return false;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

   public static void main (String[] args) {
     CarRent frame = new CarRent();
        frame.setVisible(true);
   }
}
