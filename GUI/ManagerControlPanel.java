import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ManagerControlPanel {
    JPanel panel;

    ManagerControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Create some buttons and add them to the panel
        JButton getXReport = new JButton("X Report");
        getXReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double salesTotal = db.generateXReport(1);
                JOptionPane.showMessageDialog(panel, "Sales total: " + salesTotal);
            }
        });

        JButton getYReport = new JButton("Z Report");

        getXReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double salesTotal = db.generateXReport(1);
                if (salesTotal >= 0) {
                    JOptionPane.showMessageDialog(panel, "Success");
                } else {
                    JOptionPane.showMessageDialog(panel, "Fail");
                }
            }
        });

        JButton getExcessReport = new JButton("Excess Report");
        JButton getRestockReport = new JButton("Restock Report");
        // TODO: Implement the functionality of the get_Report buttons
        this.panel.add(getXReport);
        this.panel.add(getYReport);
        this.panel.add(getExcessReport);
        this.panel.add(getRestockReport);

    }
}