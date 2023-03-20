
import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
import java.util.*;
// import java.sql.*;

public class SalesReportPanel {
    JPanel panel;
    java.sql.Date sDate;
    java.sql.Date eDate;
    Data db;

    public SalesReportPanel(Data db) {
        this.panel = new JPanel();
        this.db = db;
        JPanel dateInputPanel = new JPanel();
        JPanel salesPanel = new JPanel();

        this.panel.add(dateInputPanel);
        this.panel.add(salesPanel);
        
        DefaultListModel<String> model = new DefaultListModel<String>();

        HashMap<String, Integer> menuItemsWithQuantitySold = db.getSalesReport(sDate, eDate);

        for (Map.Entry<String, Integer> mapElem : menuItemsWithQuantitySold.entrySet()) {
            String name = mapElem.getKey();
            int sales = mapElem.getValue();
            String content = name + ": " + Integer.toString(sales);
            model.addElement(content);
        }

        JList<String> menuItemList = new JList<>(model);
        menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuItemList.setLayoutOrientation(JList.VERTICAL);
        menuItemList.setVisibleRowCount(-1);

        JScrollPane scrollPane = new JScrollPane(menuItemList);
        scrollPane.setPreferredSize(new Dimension(340, 400));
    }
}
