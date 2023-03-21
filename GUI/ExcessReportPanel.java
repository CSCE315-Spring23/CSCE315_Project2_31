
import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ExcessReportPanel {
    JPanel panel;
    java.sql.Date input_date;
    Data db;

    public ExcessReportPanel(Data db) {
        this.panel = new JPanel();
        this.db = db;
        JTextArea dateInputPanel = new JTextArea();
        JPanel excessPanel = new JPanel();

        input_date = java.sql.Date.valueOf("2023-03-20");

        this.panel.add(dateInputPanel);
        this.panel.add(excessPanel);

        DefaultListModel<String> model = new DefaultListModel<String>();

        System.out.println("Starting Query");
        Vector<MyPair<Inventory, Float>> inventoryItemsWithLowSales = db.getLowSellingInventorySinceTimestamp(input_date);
        System.out.println("Finished Query");
        for (int i = 0; i < inventoryItemsWithLowSales.size(); i++) {
            String name = inventoryItemsWithLowSales.get(i).getFirst().name;
            float sales_percentages = inventoryItemsWithLowSales.get(i).getSecond();
            String content = name + ": " + Float.toString(sales_percentages) + "%";
            System.out.println(content);
            model.addElement(content);
        }

        JList<String> itemList = new JList<>(model);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setLayoutOrientation(JList.VERTICAL);
        itemList.setVisibleRowCount(-1);

        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setPreferredSize(new Dimension(340, 400));
        this.panel.add(scrollPane);
    }
}
