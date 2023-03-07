import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ItemListPanel {
    JPanel panel;

    ItemListPanel() {
        this.panel = new JPanel();
        this.panel.setBounds(10, 10, 100, 400);
        this.panel.setBackground(Color.gray);

        JButton b1 = new JButton("Test 2");
        b1.setBounds(0, 0, 80, 30);
        b1.setBackground(Color.yellow);

        JButton b2 = new JButton("Test 2");
        b2.setBounds(50, 0, 80, 30);
        b2.setBackground(Color.green);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle button 1 click
                System.out.println("ItemListPanel: Button 1 Pressed");
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle button 2 click
                System.out.println("ItemListPanel: Button 2 Pressed");
            }
        });

        this.panel.add(b1);
        this.panel.add(b2);
    }
}