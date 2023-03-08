import java.awt.*;
import javax.swing.*;

public class ManagerControlPanel {
    JPanel panel;

    ManagerControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Create some buttons and add them to the panel
        JButton button1 = new JButton("Bottom Control 1");
        JButton button2 = new JButton("Bottom Control 2");
        JButton button3 = new JButton("Bottom Control 3");
        this.panel.add(button1);
        this.panel.add(button2);
        this.panel.add(button3);
    }
}