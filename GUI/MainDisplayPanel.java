import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainDisplayPanel {
    JPanel panel;
    Vector<Menu> menuItems;

    MainDisplayPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setBackground(Color.gray);

        menuItems = db.getAllMenuItems();
        

        int rows = menuItems.size() / 3 + 1;
        JPanel btnPanel = new JPanel(new GridLayout(rows, 3, 10, 10));
        
        // create buttons (up to )
        ActionListener addToOrder = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JButton temp = (JButton) e.getSource();
                System.out.println("Clicked on and added: " + temp.getText() + " to order!");
            }
        };
        
        for (int i = 0; i < menuItems.size(); i++){
            JButton btn = new JButton();
            btn.setText(menuItems.get(i).name);
            btn.setPreferredSize(new Dimension(80, 40));
            btn.addActionListener(addToOrder);
            btnPanel.add(btn);
        }
        this.panel.add(btnPanel);
    }

    public static void testingConnection(int buttonNum){
        System.out.println("Testing from mainDisplayPanel! With Button: " + buttonNum);
    }
}