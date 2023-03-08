import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ManagerMainDisplayPanel {
    JPanel panel;
    Vector<Menu> menuItems = new Vector<Menu>();
    Vector<Inventory> inventoryItems = new Vector<Inventory>();
    String currentState;

    Data db;

    ManagerMainDisplayPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        this.menuItems = db.getAllMenuItems();
        this.inventoryItems = db.getAllInventoryItems();
        this.db = db;
        this.currentState = "Inventory";

        drawDisplay(false);
    }
    // public void itemAdded(String menuItemName) {
    // // Update the orderItems vector and totalCost
    // System.out.println("called itemAdded");
    // Menu menuItem = db.getMenuByName(menuItemName);
    // boolean added = false;

    // for (int i = 0; i < currOrderItems.size(); i++) {
    // if (currOrderItems.get(i).getFirst().menu_id == menuItem.menu_id) {
    // currOrderItems.get(i).setSecond(currOrderItems.get(i).getSecond() + 1);
    // added = true;
    // }
    // }
    // if (!added) {
    // currOrderItems.add(new MyPair<Menu, Integer>(menuItem, 1));
    // }
    // }

    public void updateManagerDisplay(String type) {
        System.out.println("Testing from mainDisplayPanel! With Button: " + type);
        currentState = type;
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

    public Vector<MyPair<Integer, Integer>> parseInventoryItems(String inventoryItemsString) {
        Vector<MyPair<Integer, Integer>> inventoryItems = new Vector<MyPair<Integer, Integer>>();

        String[] items = inventoryItemsString.split("\\|"); // escaping the pipe character
        for (String item : items) {
            String[] parts = item.trim().split(",");
            String name = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            int inventoryId = db.getInventoryByName(name).inventory_id;
            inventoryItems.add(new MyPair<Integer, Integer>(inventoryId, quantity));
        }

        return inventoryItems;
    }

    public void drawDisplay(boolean isInventory) {
        JPanel btnPanel;
        if (isInventory) {
            int rows = inventoryItems.size() / 3 + 1;
            btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
            btnPanel.setPreferredSize(new Dimension(600, 500));

            // Set up the labels
            JLabel nameLabel = new JLabel("Inventory Name:");
            JLabel quantityLabel = new JLabel("Quantity:");

            // Set up the text fields
            JTextField nameTextField = new JTextField(10);
            JTextField quantityTextField = new JTextField(10);

            ActionListener changeInventoryItemsDisplay = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText();
                    int quantity = Integer.parseInt(quantityTextField.getText());
                    Inventory currentStock = db.getInventoryByName(name);
                    if (currentStock.quantity + quantity < 0) {
                        System.out.println("Unable to remove " + quantity + " item(s) of \"" + name + "\" removed.");
                    } else {
                        db.addInventoryQuantityByName(name, quantity);
                        if (quantity > 0) {
                            System.out.println("Added " + quantity + " item(s) of \"" + name + "\"");
                        } else if (quantity < 0) {
                            System.out.println("Removed " + quantity + " item(s) of \"" + name + "\"");
                        } else {
                            System.out.println("None of \"" + name + "\" removed.");
                        }
                        updateManagerDisplay(currentState);
                    }
                }
            };

            JButton submitButton = new JButton("Add by Name to inventory");
            submitButton.addActionListener(changeInventoryItemsDisplay);

            btnPanel.add(nameLabel);
            btnPanel.add(nameTextField);
            btnPanel.add(quantityLabel);
            btnPanel.add(quantityTextField);
            btnPanel.add(submitButton);

            for (int i = 0; i < inventoryItems.size(); i++) {
                // button for selecting item to add
                JLabel btn = new JLabel();
                btn.setText(
                        inventoryItems.get(i).name + " | " + inventoryItems.get(i).quantity);
                btnPanel.add(btn);
            }

        } else {
            int rows = menuItems.size() / 3 + 1;
            btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
            btnPanel.setPreferredSize(new Dimension(550, 500));

            JLabel nameLabel = new JLabel("Menu Item Name: ");
            JLabel priceLabel = new JLabel("New Price: ");

            // Set up the text fields
            JTextField nameTextField = new JTextField(10);
            JTextField priceTextField = new JTextField(10);

            ActionListener changeMenuItemsDisplay = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText();
                    double price = Double.parseDouble(priceTextField.getText());
                    if (price < 0) {
                        System.out.println("Invalid price! New price must be non-negative!");
                    } else {
                        db.updateMenuPriceByName(name, price);
                        if (price >= 0) {
                            System.out.println(String.format("\"%s's\" price changed to $%.2f", name, price));
                        } else {
                            System.out.println("Invalid price! New price must be non-negative!");
                        }
                        updateManagerDisplay(currentState);
                    }
                }
            };

            JButton submitButton = new JButton("Change Price of Menu Item");
            submitButton.addActionListener(changeMenuItemsDisplay);

            btnPanel.add(nameLabel);
            btnPanel.add(nameTextField);
            btnPanel.add(priceLabel);
            btnPanel.add(priceTextField);
            btnPanel.add(submitButton);

            for (int i = 0; i < menuItems.size(); i++) {
                // panel with two rows and second row is split into two columns
                JPanel qtyBtnPanel = new JPanel(new BorderLayout());
                Dimension panelSize = qtyBtnPanel.getSize();
                qtyBtnPanel.setPreferredSize(new Dimension(panelSize.width, (int) (panelSize.height * 0.85)));

                // button for selecting item to add
                JLabel btn = new JLabel();
                btn.setText(
                        "$" + menuItems.get(i).price + " " + menuItems.get(i).name);
                btnPanel.add(btn);

                // add buttons to handle quantity edits
                btnPanel.add(qtyBtnPanel);
            }
        }

        // menu_id; name; price; type; inventory_items
        // (Vector<MyPair<inventory_id, quantity>>);

        JPanel itemCreationPanel = new JPanel(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Menu Item Name: ");
        JLabel priceLabel = new JLabel("Menu Item Price: ");
        JLabel typeLabel = new JLabel("Menu Item Type: ");
        JLabel ingredientsLabel = new JLabel("Menu Item Ingredients: ");

        // Set up the text fields
        JTextField nameTextField = new JTextField(10);
        JTextField priceTextField = new JTextField(10);
        JTextField typeTextField = new JTextField(10);
        JTextField ingredientsTextField = new JTextField(10);

        ActionListener createNewInventoryItem = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameTextField.getText();
                    double price = Double.parseDouble(priceTextField.getText());
                    String type = typeTextField.getText();
                    String ingredient = ingredientsTextField.getText();
                    Vector<MyPair<Integer, Integer>> ingredients = parseInventoryItems(ingredient);
                    db.addMenuItem(name, price, type, ingredients);
                    updateManagerDisplay(currentState);
                } catch (Exception err) {
                    System.out.println("Failed to add menu item: " + err.getMessage());
                }
            }
        };
        JButton addNewItemButton = new JButton("Create New Item");
        addNewItemButton.addActionListener(createNewInventoryItem);

        itemCreationPanel.add(nameLabel);
        itemCreationPanel.add(nameTextField);
        itemCreationPanel.add(priceLabel);
        itemCreationPanel.add(priceTextField);
        itemCreationPanel.add(typeLabel);
        itemCreationPanel.add(typeTextField);
        itemCreationPanel.add(ingredientsLabel);
        itemCreationPanel.add(ingredientsTextField);
        itemCreationPanel.add(addNewItemButton);

        panel.removeAll();
        this.panel.add(btnPanel);
        this.panel.add(itemCreationPanel);
        this.panel.validate();
        this.panel.repaint();
    }
}