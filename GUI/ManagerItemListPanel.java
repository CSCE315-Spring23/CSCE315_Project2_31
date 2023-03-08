import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class ManagerItemListPanel {
    JPanel panel;
    ManagerItemListPanel(Data db, ManagerMainDisplayPanel mmdp) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        JPanel btnPanel = new JPanel(new GridLayout(6, 1, 10, 20));

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
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button Pressed");
                JButton pressedBtn = (JButton) e.getSource();
                mmdp.updateManagerDisplay(pressedBtn.getText());
            }
        };

        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).addActionListener(changeMenuItemsDisplay);
            buttons.get(i).setPreferredSize(new Dimension(150, 80));
            btnPanel.add(buttons.get(i));
        }

        this.panel.add(btnPanel);
    }
    
}