
/**

    The ManagerItemListPanel class represents a JPanel that contains buttons to filter menu items by category.
    It uses a ManagerMainDisplayPanel to update the display when a button is pressed.
    */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ManagerItemListPanel {
    JPanel panel;

    /**
     * Constructs a ManagerItemListPanel with a specified Data object and
     * ManagerMainDisplayPanel.
     * The panel is initialized with a gray background and a button panel containing
     * buttons to filter menu items.
     * 
     * @param db   the Data object to retrieve menu items from
     * @param mmdp the ManagerMainDisplayPanel to update the display when a button
     *             is pressed
     */
    ManagerItemListPanel(Data db, ManagerMainDisplayPanel mmdp) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        JPanel btnPanel = new JPanel(new GridLayout(5, 1, 10, 20));

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        // Sandwiches, Other Entrees, Sides, Dessert, Drinks

        JButton inventoryButton = new JButton("Inventory");
        buttons.add(inventoryButton);

        JButton allbutton = new JButton("All");
        buttons.add(allbutton);

        JButton sandwichesButton = new JButton("Sandwiches");
        buttons.add(sandwichesButton);

        JButton otherEntreesButton = new JButton("Other");
        buttons.add(otherEntreesButton);

        JButton sidesButton = new JButton("Sides");
        buttons.add(sidesButton);

        JButton dessertsButton = new JButton("Desserts");
        buttons.add(dessertsButton);

        JButton drinksButton = new JButton("Drinks");
        buttons.add(drinksButton);

        ActionListener changeMenuItemsDisplay = new ActionListener() {
            /**
             * Updates the ManagerMainDisplayPanel when a button is pressed.
             * The method retrieves the text of the button that was pressed and calls the
             * updateManagerDisplay method on the ManagerMainDisplayPanel.
             */
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button Pressed");
                JButton pressedBtn = (JButton) e.getSource();
                mmdp.updateManagerDisplay(pressedBtn.getText());
            }
        };

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(changeMenuItemsDisplay);
            buttons.get(i).setPreferredSize(new Dimension(150, 80));
            btnPanel.add(buttons.get(i));
        }

        this.panel.add(btnPanel);
    }

}