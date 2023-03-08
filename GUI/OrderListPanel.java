import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OrderListPanel {
    JPanel panel;

    OrderListPanel() {
        this.panel = new JPanel();

        this.panel.setBackground(Color.gray);
        JButton b1 = new JButton("Button 1");
        b1.setBounds(0, 0, 80, 30);
        b1.setBackground(Color.yellow);

        JButton b2 = new JButton("Button 2");
        b2.setBounds(50, 0, 80, 30);
        b2.setBackground(Color.green);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle button 1 click
                System.out.println("OrderListPanel: Button 1 Pressed");
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle button 2 click
                System.out.println("OrderListPanel: Button 2 Pressed");
            }
        });

        this.panel.add(b1);
        this.panel.add(b2);
    }
}