import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class BasicControlPanel implements OrderListener {
    JPanel panel;
    double totalCost;
    Data db;
    boolean activeOrder;

    public void itemAdded(String menuItemName) {
        // Update the orderItems vector and totalCost
    }

    public void itemRemoved(String menuItemName) {
        // Update the orderItems vector and totalCost
    }

    public void orderCompleted() {
        // Clear the orderItems vector and totalCost
    }

    BasicControlPanel(Data db, MainDisplayPanel displayPanel) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.db = db;
        this.totalCost = 0;
        this.activeOrder = false;
        
        // TODO: Calculate the current order's total cost

        // Create some buttons and add them to the panel
        JButton checkInBtn = new JButton("Check In");
        JButton checkOutBtn = new JButton("Check Out");
        JButton completeOrderBtn = new JButton("Complete Order");

        completeOrderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderListener listener = (OrderListener) displayPanel;
                listener.orderCompleted();
            }
        });
        
        JLabel orderTotalLabel = new JLabel("<html>Order Total:<br><html>");
        orderTotalLabel.setAlignmentX(SwingConstants.CENTER);
        orderTotalLabel.setAlignmentY(SwingConstants.TOP);
        JLabel orderTotal = new JLabel();
        orderTotal.setText(Double.toString(totalCost));
        this.panel.add(checkInBtn);
        this.panel.add(checkOutBtn);
        this.panel.add(completeOrderBtn);
        this.panel.add(orderTotalLabel);
    }
}