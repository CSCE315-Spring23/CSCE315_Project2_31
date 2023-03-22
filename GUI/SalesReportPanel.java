import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.Flow;

public class SalesReportPanel {
    JPanel panel;
    java.sql.Date sDate;
    java.sql.Date eDate;
    Data db;

    public SalesReportPanel(Data db) {
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(500, 800));
        this.db = db;
        // ---------------------------------------------------------------------------------------
        // START salesReportTitlePanel implementation
        JPanel salesReportTitlePanel = new JPanel();
        salesReportTitlePanel.setPreferredSize(new Dimension(500, 50));
        // salesReportTitlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // create label for report
        JLabel salesReportTitleLabel = new JLabel("Enter Start & End Date To Run Sales Report");
        salesReportTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salesReportTitleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        salesReportTitlePanel.add(salesReportTitleLabel);
        // setup GridBagConstraints for SalesReportPanel
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1.0;
        c1.weighty = 0.0;
        // END salesReportTitlePanel implentation
        // ---------------------------------------------------------------------------------------
        // START salesPanel implementation
        JPanel salesPanel = new JPanel();
        salesPanel.setPreferredSize(new Dimension(500, 650));
        // salesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // populate scroll pane with list
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(450, 650));
        // setup GridBagConstraints for SalesReportPanel
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.fill = GridBagConstraints.BOTH;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        // END salesPanel implementation
        // ---------------------------------------------------------------------------------------
        // START dateInputPanel implementation
        JPanel dateInputPanel = new JPanel(new GridLayout(2, 1));
        dateInputPanel.setPreferredSize(new Dimension(500, 100));
        // dateInputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // create panel to hold the text fields
        JPanel textFieldPanel = new JPanel(new GridLayout(1, 2));
        JPanel textFieldFlowLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel textFieldFlowRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // create text fields to input dates for query
        JLabel startDateLabel = new JLabel("Start Date [YYYY-MM-DD]:");
        JTextField startDateTextField = new JTextField(7);
        JLabel endDateLabel = new JLabel("End Date [YYYY-MM-DD]:");
        JTextField endDateTextField = new JTextField(7);
        startDateLabel.setHorizontalAlignment(JLabel.CENTER);
        startDateTextField.setHorizontalAlignment(JTextField.CENTER);
        endDateLabel.setHorizontalAlignment(JLabel.CENTER);
        endDateTextField.setHorizontalAlignment(JTextField.CENTER);
        // create button to run sales report
        JButton submitDateButton = new JButton("Run Sales Report");
        submitDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (startDateTextField.getText().isEmpty()) {
                        throw new IllegalArgumentException("No Start Date Provided");
                    } else if (endDateTextField.getText().isEmpty()) {
                        throw new IllegalArgumentException("No End Date Provided");
                    }
                    sDate = java.sql.Date.valueOf(startDateTextField.getText());
                    eDate = java.sql.Date.valueOf(endDateTextField.getText());
                    // query for sales report
                    HashMap<String, Integer> menuItemsWithQuantitySold = db.getSalesReport(sDate, eDate);
                    // populate report info into a list
                    DefaultListModel<String> model = new DefaultListModel<String>();
                    System.out.println("---------------------------------------------------");
                    System.out.println("Sales Report:");
                    for (Map.Entry<String, Integer> mapElem : menuItemsWithQuantitySold.entrySet()) {
                        int id = Integer.parseInt(mapElem.getKey());
                        String name = db.getMenuName(id);
                        int sales = mapElem.getValue();
                        String content = "Menu Item: " + name + " --- Number of Sales: " + sales;
                        System.out.println("Menu Item: " + name + " --- Number of Sales: " + sales);
                        model.addElement(content);
                    }
                    // populate list to be imported into scroll pane
                    JList<String> menuItemList = new JList<>(model);
                    menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    menuItemList.setLayoutOrientation(JList.VERTICAL);
                    menuItemList.setVisibleRowCount(-1);
                    // populate scroll pane with list using viewport
                    JViewport viewport = scrollPane.getViewport();
                    viewport.setView(menuItemList);
                    // update the panel
                    salesPanel.removeAll();
                    salesPanel.add(scrollPane);
                    salesPanel.validate();
                    salesPanel.repaint();
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
        textFieldFlowLeft.add(startDateLabel);
        textFieldFlowLeft.add(startDateTextField);
        textFieldFlowRight.add(endDateLabel);
        textFieldFlowRight.add(endDateTextField);
        textFieldPanel.add(textFieldFlowLeft);
        textFieldPanel.add(textFieldFlowRight);
        dateInputPanel.add(textFieldPanel);
        dateInputPanel.add(submitDateButton);
        // setup GridBagConstraints for SalesReportPanel
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.weightx = 1.0;
        c3.weighty = 0.0;
        // END dateInputPanel implementation
        // ---------------------------------------------------------------------------------------
        // START populating SalesReportPanel
        this.panel.add(salesReportTitlePanel, c1);
        this.panel.add(salesPanel, c2);
        this.panel.add(dateInputPanel, c3);
        // END populating SalesReportPanel
    }
}
