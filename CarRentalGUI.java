import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CarRentalGUI extends JFrame {
    ArrayList<Car> cars = new ArrayList<>();
    JTextField hoursField;
    JLabel totalLabel;

    public CarRentalGUI() {
        setTitle("Car Rental System");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("logo.jpg").getImage());

        cars.add(new Car(1, "lamborghini Huracán", "lambo.jpg", 500.0));
        cars.add(new Car(2, "BMW M5 comp", "m5.jpg", 250.0));
        cars.add(new Car(3, "Koenigsegg Regera", "regera.jpg", 700.0));
        cars.add(new Car(4, "Lexus", "toyo lex.jpg", 170.0));
        cars.add(new Car(5, "Porsche 911", "porsche.jpg", 450.0));

        class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}


        BackgroundPanel mainPanel = new BackgroundPanel("background.jpg");
         mainPanel.setLayout(new BorderLayout());
         


         mainPanel.setBackground(Color.PINK);  
         JPanel carPanel = new JPanel(new FlowLayout());
           carPanel.setBackground(Color.PINK);  
           
           carPanel.setOpaque(false); 
           mainPanel.add(carPanel, BorderLayout.WEST); 

 

         JPanel bottomPanel = new JPanel();
          bottomPanel.setBackground(Color.PINK);

          carPanel.setOpaque(false);
          bottomPanel.setOpaque(false);


        


        ButtonGroup group = new ButtonGroup();
        ArrayList<JRadioButton> carButtons = new ArrayList<>();

        for (Car car : cars) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            
            JLabel imageLabel = new JLabel(car.getImageIcon());
            JRadioButton radio = new JRadioButton(car.toString());
            group.add(radio);
            carButtons.add(radio);
            card.add(imageLabel);
            card.add(radio);
            carPanel.add(card);
        }

        mainPanel.add(carPanel, BorderLayout.CENTER);

        
        JLabel rentLabel = new JLabel("Hours to Rent:");
           rentLabel.setForeground(new Color(100, 149, 237));
           bottomPanel.add(rentLabel);
           rentLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
   

        hoursField = new JTextField(4);
        bottomPanel.add(hoursField);

        JButton calcButton = new JButton("Calculate Total");
        bottomPanel.add(calcButton);

        totalLabel = new JLabel("Total: $0.00");
          totalLabel.setOpaque(false); 
           totalLabel.setBorder(null);  
           totalLabel.setForeground(new Color(100, 149, 237));
            totalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
         bottomPanel.add(totalLabel);

        calcButton.addActionListener(_ -> {
            try {
                double hours = Double.parseDouble(hoursField.getText());
                Car selectedCar = null;

                for (int i = 0; i < carButtons.size(); i++) {
                    if (carButtons.get(i).isSelected()) {
                        selectedCar = cars.get(i);
                        break;
                    }
                }

                if (selectedCar != null) {
                    double total = selectedCar.pricePerHour * hours;
                    totalLabel.setText("Total: $" + String.format("%.2f", total));
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a car.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid rental hours.");
            }
        });

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CarRentalGUI();
    }
}

