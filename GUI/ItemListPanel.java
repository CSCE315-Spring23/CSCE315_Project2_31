import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * A class that represents a panel containing a list of menu item categories.
 */
public class ItemListPanel {
    JPanel panel;

    /**
     * Constructs a new ItemListPanel object with the specified database and
     * MainDisplayPanel objects.
     * 
     * @param db  the Data object containing the restaurant data
     * 
     * @param mdp the MainDisplayPanel object to be updated with the selected menu
     *            item category
     */
    ItemListPanel(Data db, MainDisplayPanel mdp) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);
        // Create a panel to hold the category buttons
        JPanel btnPanel = new JPanel(new GridLayout(6, 1, 10, 20));
        // Create buttons for each menu item category
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        // Sandwiches, Other Entrees, Sides, Dessert, Drinks

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
        // ActionListener to update the MainDisplayPanel with the selected menu item
        // category
        ActionListener changeMenuItemsDisplay = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button Pressed");
                JButton pressedBtn = (JButton) e.getSource();
                mdp.updateServerDisplay(pressedBtn.getText());
            }
        };
        // Add the ActionListener to each button and add the buttons to the button panel
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(changeMenuItemsDisplay);
            buttons.get(i).setPreferredSize(new Dimension(150, 80));
            btnPanel.add(buttons.get(i));
        }
        // Add the button panel to the ItemListPanel
        this.panel.add(btnPanel);
    }
}