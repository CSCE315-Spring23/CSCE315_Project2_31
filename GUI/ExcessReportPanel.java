import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ExcessReportPanel {
    JPanel panel;
    java.sql.Date input_date;
    Data db;

    public ExcessReportPanel(Data db) {
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(400, 600));
        this.db = db;
        // ---------------------------------------------------------------------------------------
        // START excessReportTitlePanel implementation
        JPanel excessReportTitlePanel = new JPanel();
        excessReportTitlePanel.setPreferredSize(new Dimension(400, 50));
        // excessReportTitlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // create label for report
        JLabel excessReportTitleLabel = new JLabel("Enter Date To Run Excess Report");
        excessReportTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        excessReportTitleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        excessReportTitlePanel.add(excessReportTitleLabel);
        // setup GridBagConstraints for ExcessReportPanel
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1.0;
        c1.weighty = 0.0;
        // END excessReportTitlePanel implentation
        // ---------------------------------------------------------------------------------------
        // START excessPanel implementation
        JPanel excessPanel = new JPanel();
        excessPanel.setPreferredSize(new Dimension(400, 450));
        // excessPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // populate scroll pane with list
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(350, 450));
        // setup GridBagConstraints for ExcessReportPanel
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.fill = GridBagConstraints.BOTH;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        // END excessPanel implementation
        // ---------------------------------------------------------------------------------------
        // START startDate implementation
        JPanel startDate = new JPanel(new GridLayout(2, 1));
        startDate.setPreferredSize(new Dimension(400, 100));
        // startDate.setBorder(BorderFactory.createLineBorder(Color.black));
        // create panel to hold the text fields
        JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // create text fields to input dates for query
        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateTextField = new JTextField(4);
        startDateLabel.setHorizontalAlignment(JLabel.CENTER);
        startDateTextField.setHorizontalAlignment(JTextField.CENTER);
        // create button to run sales report
        JButton submitDateButton = new JButton("Run Restock Report");
        submitDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (startDateTextField.getText().isEmpty()) {
                        throw new IllegalArgumentException("No Date Provided");
                    }
                    input_date = java.sql.Date.valueOf(startDateTextField.getText());
                    // query for sales report
                    Vector<MyPair<Inventory, Float>> inventoryItemsWithLowSales = db.getExcessReport(input_date);
                    // populate report info into a list
                    DefaultListModel<String> model = new DefaultListModel<String>();
                    System.out.println("---------------------------------------------------");
                    System.out.println("Excess Report:");
                    for (int i = 0; i < inventoryItemsWithLowSales.size(); i++) {
                        String name = inventoryItemsWithLowSales.get(i).getFirst().name;
                        float sales_percentages = inventoryItemsWithLowSales.get(i).getSecond();
                        String content = String.format("Item: %s --- %% Sold: %.2f%%", name, sales_percentages);
                        System.out.println(content);
                        model.addElement(content);
                    }
                    // populate list to be imported into scroll pane
                    JList<String> excessList = new JList<>(model);
                    excessList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    excessList.setLayoutOrientation(JList.VERTICAL);
                    excessList.setVisibleRowCount(-1);
                    // populate scroll pane with list using viewport
                    JViewport viewport = scrollPane.getViewport();
                    viewport.setView(excessList);
                    // update the panel
                    excessPanel.removeAll();
                    excessPanel.add(scrollPane);
                    excessPanel.validate();
                    excessPanel.repaint();
                    System.out.println("---------------------------------------------------");
                } catch (IllegalArgumentException err) {
                    if (err.getMessage() == null) {
                        System.out.println("Invalid date provided - [Invalid Date Format]");
                    } else {
                        System.out.println("Invalid date provided - [" + err.getMessage() + "]");
                    }
                } catch (Exception err) {
                    System.out.println("Unexpected Error - [" + err.getMessage() + "]");
                }
            }
        });
        textFieldPanel.add(startDateLabel);
        textFieldPanel.add(startDateTextField);
        startDate.add(textFieldPanel);
        startDate.add(submitDateButton);
        // setup GridBagConstraints for ExcessReportPanel
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.weightx = 1.0;
        c3.weighty = 0.0;
        // END startDate implementation
        // ---------------------------------------------------------------------------------------
        // START populating ExcessReportPanel
        this.panel.add(excessReportTitlePanel, c1);
        this.panel.add(excessPanel, c2);
        this.panel.add(startDate, c3);
        // END populating ExcessReportPanel
    }
}
