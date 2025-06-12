import javax.swing.*;

public class Car {
    int id;
    String model;
    String imagePath;
    double pricePerHour;

    public Car(int id, String model, String imagePath, double pricePerHour) {
        this.id = id;
        this.model = model;
        this.imagePath = imagePath;
        this.pricePerHour = pricePerHour;
    }

    public ImageIcon getImageIcon() {
        return new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 140, java.awt.Image.SCALE_SMOOTH));
    }

    @Override
    public String toString() {
        return model + " ($" + pricePerHour + "/hr)";
    }
    
}

