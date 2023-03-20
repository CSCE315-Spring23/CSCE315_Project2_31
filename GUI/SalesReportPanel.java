
import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

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

        sDate = java.sql.Date.valueOf("2023-02-15");
        eDate = java.sql.Date.valueOf("2023-02-15");

        this.panel.add(dateInputPanel);
        this.panel.add(salesPanel);
        
        DefaultListModel<String> model = new DefaultListModel<String>();

        
        System.out.println("Starting Query");
        HashMap<String, Integer> menuItemsWithQuantitySold = db.getSalesReport(sDate, eDate);
        System.out.println("Finished Query");
        for (Map.Entry<String, Integer> mapElem : menuItemsWithQuantitySold.entrySet()) {
            int id = Integer.parseInt(mapElem.getKey());
            String name = db.getMenuName(id);
            int sales = mapElem.getValue();
            String content = name + ": " + Integer.toString(sales);
            System.out.println(content);
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
