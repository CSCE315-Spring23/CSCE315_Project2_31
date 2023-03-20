import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
                SalesReportPanel srp = new SalesReportPanel(db);
                JOptionPane.showMessageDialog(panel, srp.panel);
            }
        });

        // Excess Report generation
        JButton getExcessReport = new JButton("Excess Report");
        getExcessReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        this.panel.add(getSalesReport);
        this.panel.add(getXReport);
        this.panel.add(getZReport);
        this.panel.add(getSalesReport);
        this.panel.add(getExcessReport);
        this.panel.add(getRestockReport);

    }
}