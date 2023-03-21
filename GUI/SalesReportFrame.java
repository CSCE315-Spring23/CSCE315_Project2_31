
import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SalesReportFrame {
    JFrame salesFrame;
    java.sql.Date sDate;
    java.sql.Date eDate;
    Data db;

    public SalesReportFrame(Data db) {
        salesFrame = new JFrame("Sales Report");
        salesFrame.setSize(400, 600);
        salesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.db = db;

        JPanel dateInputPanel = new JPanel();
        JPanel salesPanel = new JPanel();

        DefaultListModel<String> model = new DefaultListModel<String>();

        HashMap<String, Integer> menuItemsWithQuantitySold = db.getSalesReport(sDate, eDate);
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
