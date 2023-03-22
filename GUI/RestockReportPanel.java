import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class RestockReportPanel {
    JPanel panel;
    int minimumQuantity;
    Data db;

    public RestockReportPanel(Data db) {
        this.panel = new JPanel(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(400, 600));
        this.db = db;
        // ---------------------------------------------------------------------------------------
        // START restockReportTitlePanel implementation
        JPanel restockReportTitlePanel = new JPanel();
        restockReportTitlePanel.setPreferredSize(new Dimension(400, 50));
        // restockReportTitlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // create label for report
        JLabel restockReportTitleLabel = new JLabel("Enter Minimum Quantity To Run Restock Report");
        restockReportTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        restockReportTitleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        restockReportTitlePanel.add(restockReportTitleLabel);
        // setup GridBagConstraints for RestockReportPanel
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1.0;
        c1.weighty = 0.0;
        // END restockReportTitlePanel implentation
        // ---------------------------------------------------------------------------------------
        // START restockPanel implementation
        JPanel restockPanel = new JPanel();
        restockPanel.setPreferredSize(new Dimension(400, 450));
        // restockPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // populate scroll pane with list
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(350, 450));
        // setup GridBagConstraints for restockReportPanel
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.fill = GridBagConstraints.BOTH;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        // END restockPanel implementation
        // ---------------------------------------------------------------------------------------
        // START minimumQuantityPanel implementation
        JPanel minimumQuantityPanel = new JPanel(new GridLayout(2, 1));
        minimumQuantityPanel.setPreferredSize(new Dimension(400, 100));
        // minimumQuantityPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        // create panel to hold the text fields
        JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // create text fields to input dates for query
        JLabel minimumQuantityLabel = new JLabel("Minimum Quantity:");
        JTextField minimumQuantityTextField = new JTextField(4);
        minimumQuantityLabel.setHorizontalAlignment(JLabel.CENTER);
        minimumQuantityTextField.setHorizontalAlignment(JTextField.CENTER);
        // create button to run sales report
        JButton submitQuantityButton = new JButton("Run Restock Report");
        submitQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (minimumQuantityTextField.getText().isEmpty()) {
                        throw new IllegalArgumentException("No Quantity Provided");
                    }
                    minimumQuantity = Integer.parseInt(minimumQuantityTextField.getText());
                    // query for sales report
                    Vector<Inventory> restockVect = db.getRestockReport(minimumQuantity);
                    // populate report info into a list
                    DefaultListModel<String> model = new DefaultListModel<String>();
                    System.out.println("---------------------------------------------------");
                    System.out.println("Restock Report:");
                    for (int i = 0; i < restockVect.size(); i++) {
                        String content = String
                                .format("Item: %s --- Quantity: %d", restockVect.get(i).name,
                                        restockVect.get(i).quantity);
                        System.out.println(content);
                        model.addElement(content);
                    }
                    // populate list to be imported into scroll pane
                    JList<String> restockList = new JList<>(model);
                    restockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    restockList.setLayoutOrientation(JList.VERTICAL);
                    restockList.setVisibleRowCount(-1);
                    // populate scroll pane with list using viewport
                    JViewport viewport = scrollPane.getViewport();
                    viewport.setView(restockList);
                    // update the panel
                    restockPanel.removeAll();
                    restockPanel.add(scrollPane);
                    restockPanel.validate();
                    restockPanel.repaint();
                    System.out.println("---------------------------------------------------");
                } catch (IllegalArgumentException err) {
                    System.out.println("Invalid quantity provided - [" + err.getMessage() + "]");
                } catch (Exception err) {
                    System.out.println("Unexpected Error - [" + err.getMessage() + "]");
                }
            }
        });
        textFieldPanel.add(minimumQuantityLabel);
        textFieldPanel.add(minimumQuantityTextField);
        minimumQuantityPanel.add(textFieldPanel);
        minimumQuantityPanel.add(submitQuantityButton);
        // setup GridBagConstraints for restockReportPanel
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.weightx = 1.0;
        c3.weighty = 0.0;
        // END minimumQuantityPanel implementation
        // ---------------------------------------------------------------------------------------
        // START populating RestockReportPanel
        this.panel.add(restockReportTitlePanel, c1);
        this.panel.add(restockPanel, c2);
        this.panel.add(minimumQuantityPanel, c3);
        // END populating RestockReportPanel
    }
}
