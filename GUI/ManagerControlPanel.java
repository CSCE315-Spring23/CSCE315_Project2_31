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
                double salesTotal = db.getXReport(1);
                JOptionPane.showMessageDialog(panel, String.format("Sales total: $%.2f", salesTotal));
            }
        });

        // Z Report generation
        JButton getZReport = new JButton("Z Report");
        getZReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = db.getZReport(1);
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
                SalesReportPanel salesReportPanel = new SalesReportPanel(db);
                JFrame salesFrame = new JFrame("Sales Report");
                salesFrame.add(salesReportPanel.panel);
                salesFrame.setSize(400, 600);
                salesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                salesFrame.setVisible(true);
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
                System.out.println("Getting Restock Report...");
                JFrame restockFrame = new JFrame("Restock Report");
                Vector<Inventory> restockVect = db.getRestockReport(50);
                DefaultListModel<String> model = new DefaultListModel<String>();

                System.out.println("Restock Report:");
                for (int i = 0; i < restockVect.size(); i++) {
                    String itemContent = String
                            .format("Item Name: %s --- Quantity: %d", restockVect.get(i).name,
                                    restockVect.get(i).quantity);
                    System.out.println(itemContent);
                    model.addElement(itemContent);
                }

                JList<String> restockList = new JList<>(model);
                restockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                restockList.setLayoutOrientation(JList.VERTICAL);
                restockList.setVisibleRowCount(-1);

                JScrollPane scrollPane = new JScrollPane(restockList);
                scrollPane.setPreferredSize(new Dimension(400, 600));
                restockFrame.add(scrollPane);
                restockFrame.setSize(400, 600);
                restockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                restockFrame.setVisible(true);
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