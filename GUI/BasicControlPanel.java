import java.awt.*;
import javax.swing.*;

public class BasicControlPanel {
    JPanel panel;

    BasicControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Create some buttons and add them to the panel
        JButton checkInBtn = new JButton("Check In");
        JButton checkOutBtn = new JButton("Check Out");
        JButton CompleteOrderBtn = new JButton("Complete Order");
        this.panel.add(checkInBtn);
        this.panel.add(checkOutBtn);
        this.panel.add(CompleteOrderBtn);
    }
}