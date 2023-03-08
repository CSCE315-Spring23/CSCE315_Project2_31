import java.awt.*;
import javax.swing.*;

public class ManagerControlPanel {
    JPanel panel;

    ManagerControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Create some buttons and add them to the panel
        JButton checkInBtn = new JButton("Check In");
        JButton checkOutBtn = new JButton("Check Out");
        JButton completeOrderBtn = new JButton("Complete Order");
        JButton viewInventoryBtn = new JButton("View Inventory");
        JButton employeeListBtn = new JButton("Employee List");
        JButton revenueBtn = new JButton("Revenue");
        JButton registerBtn = new JButton("Open Register");
        JButton couponBtn = new JButton("Apply Coupon");
        this.panel.add(checkInBtn);
        this.panel.add(checkOutBtn);
        this.panel.add(completeOrderBtn);
        this.panel.add(viewInventoryBtn);
        this.panel.add(employeeListBtn);
        this.panel.add(revenueBtn);
        this.panel.add(registerBtn);
        this.panel.add(couponBtn);
    }
}