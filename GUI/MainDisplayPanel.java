import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class MainDisplayPanel {
    JPanel panel;
    Vector<Menu> menuItems;

    MainDisplayPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(300, 400));
        this.panel.setBackground(Color.gray);

        menuItems = db.getAllMenuItems();
        

        int dims = ((int) Math.sqrt((double) menuItems.size())) + 1;
        JPanel btnPanel = new JPanel(new GridLayout(dims, dims, 10, 10));
        
        // create buttons (up to )
        ActionListener addToOrder = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JButton temp = (JButton) e.getSource();
                System.out.println("Clicked on and added: " + temp.getText() + " to order!");
            }
        };
        
        for (int i = 0; i < menuItems.size() - 15; i++){
            JButton btn = new JButton();
            btn.setText(menuItems.get(i).name);
            btn.addActionListener(addToOrder);
            btnPanel.add(btn);
        }
        
        this.panel.add(btnPanel);

    }

    public static void testingConnection(int buttonNum){
        System.out.println("Testing from mainDisplayPanel! With Button: " + buttonNum);

    }
}