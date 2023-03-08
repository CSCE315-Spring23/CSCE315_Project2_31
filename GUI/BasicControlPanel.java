import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class BasicControlPanel {
    JPanel panel;
    double totalCost;
    Data db;
    JLabel orderTotal;

    BasicControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.db = db;
        this.totalCost = 0;

        // Create some buttons and add them to the panel
        JButton checkInBtn = new JButton("Check In");
        JButton checkOutBtn = new JButton("Check Out");
        panel.removeAll();

        this.panel.add(checkInBtn);
        this.panel.add(checkOutBtn);

        this.panel.validate();
        this.panel.repaint();
    }
}