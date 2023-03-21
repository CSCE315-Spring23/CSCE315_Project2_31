import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ManagerControlPanel {
    JPanel panel;

    ManagerControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // X Report generation
        JButton getXReport = new JButton("X Report");
        getXReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double salesTotal = db.generateXReport(1);
                JOptionPane.showMessageDialog(panel, String.format("Sales total: $%.2f", salesTotal));
            }
        });

        // Z Report generation
        JButton getZReport = new JButton("Z Report");
        getZReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = db.generateZReport(1);
                if (success) {
                    JOptionPane.showMessageDialog(panel, "Z Report generated successfully!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Z Report generation failed. Please try again.");
                }
            }
        });

        // Sales Report generation
        JButton getSalesReport = new JButton("Sales Report");
        getSalesReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Excess Report generation
        JButton getExcessReport = new JButton("Excess Report");
        getExcessReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<MyPair<Inventory, Float>> result = db.getLowSellingInventorySinceTimestamp(java.sql.Date.valueOf("2023-03-20"));
                if (result != null) {
                    String res = "Low sales since 2023-03-20\n";
                    for (int i = 0; i < result.size(); i++) {
                        res += result.get(i).getFirst().name + " | " + result.get(i).getSecond() + "%\n";
                    }
                    JOptionPane.showMessageDialog(panel, res);
                } else {
                    JOptionPane.showMessageDialog(panel, "Excess Report generation failed. Please try again.");
                }
            }
        });

        // Restock Report generation
        JButton getRestockReport = new JButton("Restock Report");
        getRestockReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // TODO: Implement the functionality of the get_Report buttons
        this.panel.add(getXReport);
        this.panel.add(getZReport);
        this.panel.add(getSalesReport);
        this.panel.add(getExcessReport);
        this.panel.add(getRestockReport);

    }
}