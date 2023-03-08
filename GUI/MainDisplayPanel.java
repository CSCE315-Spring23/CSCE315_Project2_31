import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainDisplayPanel implements OrderListener {
    JPanel panel;
    Vector<Menu> menuItems;
    Vector<MyPair<Integer, Integer>> orderItems;
    Data db;

    public void itemAdded(String menuItemName) {
        // Update the orderItems vector and totalCost
    }

    public void itemRemoved(String menuItemName) {
        // Update the orderItems vector and totalCost
    }

    public void orderCompleted() {
        // Clear the orderItems vector and totalCost
    }

    MainDisplayPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        this.menuItems = db.getAllMenuItems();
        this.db = db;

        drawDisplay();
    }

    public void updateServerDisplay(String type){
        System.out.println("Testing from mainDisplayPanel! With Button: " + type);
        if (type == "All"){
            menuItems = db.getAllMenuItems();
        } else{
            menuItems = db.getMenuByType(type);
        }
        drawDisplay();
    }

    public void drawDisplay(){
        int rows = menuItems.size() / 3 + 1;
        JPanel btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
        btnPanel.setPreferredSize(new Dimension(450, 500));
        
        // create buttons (up to )
        ActionListener addToOrder = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JButton temp = (JButton) e.getSource();
                System.out.println("Clicked on and added \"" + temp.getText() + "\" to order!");
                OrderListener listener = (OrderListener) panel.getParent().getComponents()[1];
                listener.itemAdded(temp.getText());
            }
        };
        for (int i = 0; i < menuItems.size(); i++){
            JButton btn = new JButton();
            btn.setText(menuItems.get(i).name);
            btn.setPreferredSize(new Dimension(120, 40));
            btn.addActionListener(addToOrder);
            btnPanel.add(btn);
        }
        panel.removeAll();
        this.panel.add(btnPanel);
        this.panel.validate();
        this.panel.repaint();
    }
}