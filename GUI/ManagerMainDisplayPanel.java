import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ManagerMainDisplayPanel {
    JPanel panel;
    Vector<Menu> menuItems = new Vector<Menu>();
    Vector<Inventory> inventoryItems = new Vector<Inventory>();
    // quantity>
    Data db;

    ManagerMainDisplayPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        this.menuItems = db.getAllMenuItems();
        this.inventoryItems = db.getAllInventoryItems();
        this.db = db;

        drawDisplay();
    }

    public void drawDisplay() {
        // int rows = menuItems.size() / 3 + 1;
        // JPanel btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
        // btnPanel.setPreferredSize(new Dimension(450, 500));

        // for (int i = 0; i < menuItems.size(); i++) {
        // // panel with two rows and second row is split into two columns
        // JPanel qtyBtnPanel = new JPanel(new BorderLayout());
        // Dimension panelSize = qtyBtnPanel.getSize();
        // qtyBtnPanel.setPreferredSize(new Dimension(panelSize.width, (int)
        // (panelSize.height * 0.85)));

        // // button for selecting item to add
        // JButton btn = new JButton();
        // btn.setText(menuItems.get(i).name);
        // btn.addActionListener(addToOrder);
        // qtyBtnPanel.add(btn, BorderLayout.NORTH);

        // // add buttons to handle quantity edits
        // J
        // JButton increaseQtyBtn = new JButton("+");
        // increaseQtyBtn.putClientProperty("menuItemName", menuItems.get(i).name);

        // qtyPanel.add(decreaseQtyBtn);
        // qtyPanel.add(increaseQtyBtn);
        // qtyBtnPanel.add(qtyPanel, BorderLayout.SOUTH);

        // btnPanel.add(qtyBtnPanel);
        // }

        // // Create some buttons and add them to the panel
        // JButton completeOrderBtn = new JButton("Complete Order");

        // completeOrderBtn.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // JButton temp = (JButton) e.getSource();
        // String menuItemName = (String) temp.getClientProperty("menuItemName");
        // System.out.println("Completing Order fo \"" + menuItemName + "\"!");

        // }
        // });
        // JPanel orderDetail = new JPanel(new FlowLayout());
        // JLabel orderTotalLabel = new JLabel("Order Total: ");

        // totalCostLabel.setText("$00.00");
        // orderDetail.add(orderTotalLabel);
        // orderDetail.add(totalCostLabel);

        // panel.removeAll();

        // this.panel.add(btnPanel);

        // this.panel.add(completeOrderBtn);
        // this.panel.add(orderDetail);

        // this.panel.validate();
        // this.panel.repaint();
    }
}
