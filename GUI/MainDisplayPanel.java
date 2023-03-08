import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainDisplayPanel {
    JPanel panel;
    Vector<Menu> menuItems = new Vector<Menu>();
    Vector<MyPair<Menu, Integer>> currOrderItems = new Vector<MyPair<Menu, Integer>>(); // menu items in order <id,
    // quantity>
    double totalCost = 0;
    Data db;
    JLabel totalCostLabel;

    MainDisplayPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        this.menuItems = db.getAllMenuItems();
        this.db = db;
        this.totalCost = 0;

        drawDisplay();
    }

    public void updateOrderTotal() {
        if (totalCost == 0) {
            totalCostLabel.setText("$00.00");
        } else {
            totalCostLabel.setText(String.format("$%.2f", totalCost));
        }
    }

    public void setOrderTotal() {
        double total = 0;
        for (int i = 0; i < currOrderItems.size(); i++) {
            total += currOrderItems.get(i).getFirst().price * currOrderItems.get(i).getSecond();
        }
        this.totalCost = total;
        System.out.println(this.totalCost);
        this.totalCostLabel.setText(String.format("Total cost: $%.2f", this.totalCost));
        // updating the label in basicControlPanel
        updateOrderTotal();
    }

    public void itemAdded(String menuItemName) {
        // Update the orderItems vector and totalCost
        System.out.println("called itemAdded");
        Menu menuItem = db.getMenuByName(menuItemName);
        boolean added = false;

        for (int i = 0; i < currOrderItems.size(); i++) {
            if (currOrderItems.get(i).getFirst().menu_id == menuItem.menu_id) {
                currOrderItems.get(i).setSecond(currOrderItems.get(i).getSecond() + 1);
                added = true;
            }
        }
        if (!added) {
            currOrderItems.add(new MyPair<Menu, Integer>(menuItem, 1));
        }

        setOrderTotal();
    }

    public void itemRemoved(String menuItemName) {
        // Update the orderItems vector and totalCost
        System.out.println("called itemRemoved");
        Menu menuItem = db.getMenuByName(menuItemName);

        for (int i = 0; i < currOrderItems.size(); i++) {
            if (currOrderItems.get(i).getFirst().menu_id == menuItem.menu_id) {
                int newQuantity = currOrderItems.get(i).getSecond() - 1;
                if (newQuantity <= 0) {
                    currOrderItems.remove(i);
                } else {
                    System.out.println("Decreased the order quantity for\"" + menuItemName + "\"!");
                    currOrderItems.set(i, new MyPair<Menu, Integer>(menuItem, newQuantity));
                }
                if (newQuantity < 0) {
                    System.out.println("Unable to decrease quantity for \"" + menuItemName + "\"! Quantity already 0!");
                }
                break;
            }
        }
        this.setOrderTotal();
    }

    public void orderCompleted() {
        // Clear the orderItems vector and totalCost
        this.currOrderItems = new Vector<MyPair<Menu, Integer>>();
        this.totalCost = 0;

        // Update the totalCostLabel text
        this.totalCostLabel.setText("Total cost: $00.00");
    }

    public Vector<MyPair<Integer, Integer>> convertOrderToReturnVector() {
        Vector<MyPair<Integer, Integer>> out = new Vector<MyPair<Integer, Integer>>();

        for (int i = 0; i < this.currOrderItems.size(); i++) {
            out.add(new MyPair<Integer, Integer>(
                    this.currOrderItems.get(i).getFirst().menu_id,
                    this.currOrderItems.get(i).getSecond()));
        }

        return out;
    }

    public void finalizeOrder() {
        if (currOrderItems.size() != 0) {
            Random rand = new Random();

            int customer_id = rand.nextInt(1000);
            int staff_id = rand.nextInt(16);

            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

            Vector<MyPair<Integer, Integer>> finalOrder = convertOrderToReturnVector();
            db.makeOrder(totalCost, date, customer_id, staff_id, finalOrder);

            orderCompleted();
        }
    }

    public void updateServerDisplay(String type) {
        System.out.println("Testing from mainDisplayPanel! With Button: " + type);
        if (type == "All") {
            menuItems = db.getAllMenuItems();
        } else {
            menuItems = db.getMenuByType(type);
        }
        drawDisplay();
    }

    public void drawDisplay() {
        int rows = menuItems.size() / 3 + 1;
        JPanel btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
        btnPanel.setPreferredSize(new Dimension(450, 500));

        // create buttons (up to )
        ActionListener addToOrder = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton temp = (JButton) e.getSource();
                System.out.println("Clicked on and added \"" + temp.getText() + "\" to order!");
            }
        };
        ActionListener decreaseQty = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton temp = (JButton) e.getSource();
                String menuItemName = (String) temp.getClientProperty("menuItemName");
                itemRemoved(menuItemName);
            }
        };
        ActionListener increaseQty = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton temp = (JButton) e.getSource();
                String menuItemName = (String) temp.getClientProperty("menuItemName");
                System.out.println("Increased the order quantity for \"" + menuItemName + "\"!");
                itemAdded(menuItemName);
            }
        };
        for (int i = 0; i < menuItems.size(); i++) {
            // panel with two rows and second row is split into two columns
            JPanel qtyBtnPanel = new JPanel(new BorderLayout());
            Dimension panelSize = qtyBtnPanel.getSize();
            qtyBtnPanel.setPreferredSize(new Dimension(panelSize.width, (int) (panelSize.height * 0.85)));

            // button for selecting item to add
            JButton btn = new JButton();
            btn.setText(menuItems.get(i).name);
            btn.addActionListener(addToOrder);
            qtyBtnPanel.add(btn, BorderLayout.NORTH);

            // add buttons to handle quantity edits
            JPanel qtyPanel = new JPanel(new GridLayout(1, 2));
            JButton decreaseQtyBtn = new JButton("-");
            decreaseQtyBtn.putClientProperty("menuItemName", menuItems.get(i).name);
            decreaseQtyBtn.addActionListener(decreaseQty);
            JButton increaseQtyBtn = new JButton("+");
            increaseQtyBtn.putClientProperty("menuItemName", menuItems.get(i).name);
            increaseQtyBtn.addActionListener(increaseQty);

            qtyPanel.add(decreaseQtyBtn);
            qtyPanel.add(increaseQtyBtn);
            qtyBtnPanel.add(qtyPanel, BorderLayout.SOUTH);

            btnPanel.add(qtyBtnPanel);
        }

        // Create some buttons and add them to the panel
        JButton completeOrderBtn = new JButton("Complete Order");

        completeOrderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton temp = (JButton) e.getSource();
                String menuItemName = (String) temp.getClientProperty("menuItemName");
                System.out.println("Completing order for \"" + menuItemName + "\"!");
                finalizeOrder();
            }
        });
        JPanel orderDetail = new JPanel(new FlowLayout());
        JLabel orderTotalLabel = new JLabel("Order Total: ");
        totalCostLabel = new JLabel();
        totalCostLabel.setText("$00.00");
        orderDetail.add(orderTotalLabel);
        orderDetail.add(totalCostLabel);

        panel.removeAll();

        this.panel.add(btnPanel);

        this.panel.add(completeOrderBtn);
        this.panel.add(orderDetail);

        this.panel.validate();
        this.panel.repaint();
    }
}