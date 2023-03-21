
import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SalesReportFrame {
    JPanel panel;
    java.sql.Date sDate;
    java.sql.Date eDate;
    Data db;

    public SalesReportFrame(Data db) {
        this.panel = new JPanel(new GridLayout(3, 1));
        this.panel.setPreferredSize(new Dimension(400, 700));
        this.db = db;

        // START scrollPane implementation
        JPanel salesPanel = new JPanel();

        DefaultListModel<String> model = new DefaultListModel<String>();
        // query for sales report
        HashMap<String, Integer> menuItemsWithQuantitySold = db.getSalesReport(sDate, eDate);
        // populate report info into a list
        for (Map.Entry<String, Integer> mapElem : menuItemsWithQuantitySold.entrySet()) {
            int id = Integer.parseInt(mapElem.getKey());
            String name = db.getMenuName(id);
            int sales = mapElem.getValue();
            String content = name + ": " + Integer.toString(sales);
            System.out.println(content);
            model.addElement(content);
        }
        // populate list to be imported into scroll pane
        JList<String> menuItemList = new JList<>(model);
        menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuItemList.setLayoutOrientation(JList.VERTICAL);
        menuItemList.setVisibleRowCount(-1);
        // populate scroll pane with list
        JScrollPane scrollPane = new JScrollPane(menuItemList);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        salesPanel.add(scrollPane);
        // END scrollPane implementation
        // ---------------------------------------------------------------------------------------
        // START dateInputPanel implementation
        JPanel dateInputPanel = new JPanel(new GridLayout(2, 1));
        JPanel textFieldPanel = new JPanel(new GridLayout(1, 2));

        JTextField startDateTextField = new JTextField(10);
        JTextField endDateTextField = new JTextField(10);
        JButton submitDateButton = new JButton("Run Sales Report");

        textFieldPanel.add(startDateTextField);
        textFieldPanel.add(endDateTextField);
        dateInputPanel.add(textFieldPanel);
        dateInputPanel.add(submitDateButton);
        // END dateInputPanel implementation
    }
}
