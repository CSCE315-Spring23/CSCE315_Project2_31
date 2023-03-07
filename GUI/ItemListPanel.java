import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class ItemListPanel {
    JPanel panel;
    ItemListPanel() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(220, 400));
        this.panel.setBackground(Color.gray);

        JPanel btnPanel = new JPanel(new GridLayout(6, 1, 10, 20));

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        // Sandwiches, Other Entrees, Sides, Dessert, Drinks

        JButton sandwichesButton = new JButton("Sandwiches");
        sandwichesButton.setPreferredSize(new Dimension(200, 80));
        sandwichesButton.setBackground(Color.yellow);
        buttons.add(sandwichesButton);

        JButton otherEntreesButton = new JButton("Other Entrees");
        otherEntreesButton.setPreferredSize(new Dimension(200, 80));
        otherEntreesButton.setBackground(Color.green);
        buttons.add(otherEntreesButton);

        JButton sidesButton = new JButton("Sides");
        sidesButton.setPreferredSize(new Dimension(200, 80));
        sidesButton.setBackground(Color.yellow);
        buttons.add(sidesButton);

        JButton dessertsButton = new JButton("Desserts");
        dessertsButton.setPreferredSize(new Dimension(200, 80));
        dessertsButton.setBackground(Color.green);
        buttons.add(dessertsButton);

        JButton drinksButton = new JButton("Drinks");
        drinksButton.setPreferredSize(new Dimension(200, 80));
        drinksButton.setBackground(Color.yellow);
        buttons.add(drinksButton);

        sandwichesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button 1 Pressed");
            }
        });

        otherEntreesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button 2 Pressed");
            }
        });

        sidesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button 3 Pressed");
            }
        });

        dessertsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button 4 Pressed");
            }
        });

        drinksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ItemListPanel: Button 5 Pressed");
            }
        });

        for(int i = 0; i < buttons.size(); i++){
            btnPanel.add(buttons.get(i));
        }

        this.panel.add(btnPanel);
    }
}