import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ManagerMainDisplayPanel {
    JPanel panel;
    Vector<Menu> menuItems = new Vector<Menu>();
    Vector<Inventory> inventoryItems = new Vector<Inventory>();


    Data db;

    ManagerMainDisplayPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        this.menuItems = db.getAllMenuItems();
        this.inventoryItems = db.getAllInventoryItems();
        this.db = db;

        drawDisplay(false);
    }




    // public void itemAdded(String menuItemName) {
    //     // Update the orderItems vector and totalCost
    //     System.out.println("called itemAdded");
    //     Menu menuItem = db.getMenuByName(menuItemName);
    //     boolean added = false;

    //     for (int i = 0; i < currOrderItems.size(); i++) {
    //         if (currOrderItems.get(i).getFirst().menu_id == menuItem.menu_id) {
    //             currOrderItems.get(i).setSecond(currOrderItems.get(i).getSecond() + 1);
    //             added = true;
    //         }
    //     }
    //     if (!added) {
    //         currOrderItems.add(new MyPair<Menu, Integer>(menuItem, 1));
    //     }
    // }



    public void updateManagerDisplay(String type) {
        // TODO 
        System.out.println("Testing from mainDisplayPanel! With Button: " + type);
        if (type == "All") {
            menuItems = db.getAllMenuItems();
            drawDisplay(false);
        } else if (type == "Inventory") {
            inventoryItems = db.getAllInventoryItems();
            drawDisplay(true);
        } else {
            menuItems = db.getMenuByType(type);
            drawDisplay(false);
        }
    }

    public void drawDisplay(boolean isInventory) {
        JPanel btnPanel;
        if (isInventory){
            int rows = menuItems.size() / 3 + 1;
            btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
            btnPanel.setPreferredSize(new Dimension(450, 500));

            ActionListener decreaseQty = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton temp = (JButton) e.getSource();
                    String ItemName = (String) temp.getClientProperty("inventoryItemName");
                    System.out.println("Decreased the quantity for \"" + ItemName + "\"!");
                }
            };
            ActionListener increaseQty = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton temp = (JButton) e.getSource();
                    String ItemName = (String) temp.getClientProperty("inventoryItemName");
                    System.out.println("Increased the quantity for \"" + ItemName + "\"!");
                }
            };

            for (int i = 0; i < inventoryItems.size(); i++) {
                // panel with two rows and second row is split into two columns
                JPanel qtyBtnPanel = new JPanel(new BorderLayout());
                Dimension panelSize = qtyBtnPanel.getSize();
                qtyBtnPanel.setPreferredSize(new Dimension(panelSize.width, (int) (panelSize.height * 0.85)));

                // button for selecting item to add
                JButton btn = new JButton();
                btn.setText(menuItems.get(i).name);
                qtyBtnPanel.add(btn, BorderLayout.NORTH);

                // add buttons to handle quantity edits
                JPanel qtyPanel = new JPanel(new GridLayout(1, 2));
                JButton decreaseQtyBtn = new JButton("-");
                decreaseQtyBtn.putClientProperty("inventoryItemName", inventoryItems.get(i).name);
                decreaseQtyBtn.addActionListener(decreaseQty);
                JButton increaseQtyBtn = new JButton("+");
                increaseQtyBtn.putClientProperty("inventoryItemName", inventoryItems.get(i).name);
                increaseQtyBtn.addActionListener(increaseQty);

                qtyPanel.add(decreaseQtyBtn);
                qtyPanel.add(increaseQtyBtn);
                qtyBtnPanel.add(qtyPanel, BorderLayout.SOUTH);

                btnPanel.add(qtyBtnPanel);
            }

        } else {
            int rows = menuItems.size() / 3 + 1;
            btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
            btnPanel.setPreferredSize(new Dimension(450, 500));


            ActionListener decreaseQty = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton temp = (JButton) e.getSource();
                    String menuItemName = (String) temp.getClientProperty("menuItemName");
                }
            };
            ActionListener increaseQty = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton temp = (JButton) e.getSource();
                    String ItemName = (String) temp.getClientProperty("menuItemName");
                    System.out.println("Increased the order quantity for \"" + ItemName + "\"!");
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
        }
        
        

        // Create some buttons and add them to the panel

        panel.removeAll();
        this.panel.add(btnPanel);
        this.panel.validate();
        this.panel.repaint();
    }
}