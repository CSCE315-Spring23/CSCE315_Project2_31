import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 * <dl>
 * <dt><b>Manager Control Panel</b></dt>
 * <dd>
 * ManagerControlPanel class creates a panel for the manager to access different
 * reports such as X Report, Z Report, Sales Report, Excess Report and Restock
 * Report.
 * 
 * It uses the Data class to interact with the database and generate the
 * reports.
 * </dd>
 * </dl>
 * 
 * @author David Chi
 * @author Jeffrey Li
 * @author Art Young
 * @author Andrew Mao
 * @version 1.0
 * @since 2023-03-21
 */
public class ManagerControlPanel {
    JPanel panel;

    /**
     * Constructor method for ManagerControlPanel class.
     * 
     * @param db an instance of the Data class
     */
    ManagerControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Create some buttons and add them to the panel
        JButton getXReport = new JButton("X Report");
        getXReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XReport res = db.getXReport(1);
                if (res.report_date.equals(new Timestamp(-1))) {
                    JOptionPane.showMessageDialog(panel,
                            String.format("No Z reports exist. Sales total today: $%.2f", res.total_sales));
                } else {
                    JOptionPane.showMessageDialog(null,
                            String.format("Sales total since last Z Report (%s): $%.2f",
                                    res.report_date,
                                    res.total_sales));
                }
            }
        });

        // Z Report generation
        JButton getZReport = new JButton("Z Report");
        getZReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZReport report = db.getZReport(1);
                if (report.total_sales == -1) {
                    JOptionPane.showMessageDialog(null, "Z Report generation failed. Please try again.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            String.format("Z Report generated successfully!\nSales total today: $%.2f",
                                    report.total_sales));
                }
            }
        });

        // Sales Report generation
        JButton getSalesReport = new JButton("Sales Report");
        getSalesReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame salesFrame = new JFrame("Sales Report");
                salesFrame.setSize(500, 800);
                salesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                salesFrame.setLocationRelativeTo(null);
                salesFrame.setResizable(false);
                SalesReportPanel salesReportPanel = new SalesReportPanel(db);
                salesFrame.add(salesReportPanel.panel);
                salesFrame.setVisible(true);
            }
        });

        // Excess Report generation
        JButton getExcessReport = new JButton("Excess Report");
        getExcessReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame excessFrame = new JFrame("Excess Report");
                excessFrame.setSize(400, 600);
                excessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                excessFrame.setLocationRelativeTo(null);
                excessFrame.setResizable(false);
                ExcessReportPanel excessReportPanel = new ExcessReportPanel(db);
                excessFrame.add(excessReportPanel.panel);
                excessFrame.setVisible(true);
            }
        });

        // Restock Report generation
        JButton getRestockReport = new JButton("Restock Report");
        getRestockReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame restockFrame = new JFrame("Restock Report");
                restockFrame.setSize(400, 600);
                restockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                restockFrame.setLocationRelativeTo(null);
                restockFrame.setResizable(false);
                RestockReportPanel restockReportPanel = new RestockReportPanel(db);
                restockFrame.add(restockReportPanel.panel);
                restockFrame.setVisible(true);
            }
        });

        this.panel.add(getSalesReport);
        this.panel.add(getXReport);
        this.panel.add(getZReport);
        this.panel.add(getSalesReport);
        this.panel.add(getExcessReport);
        this.panel.add(getRestockReport);

    }
}