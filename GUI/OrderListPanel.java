import java.awt.*;
import javax.swing.*;
import java.util.*;

public class OrderListPanel {
    JPanel panel;
    Data db;

    OrderListPanel(Data db) {
        this.panel = new JPanel();
        this.db = db;

        this.panel.setPreferredSize(new Dimension(350, 400));
        this.panel.setBackground(Color.gray);

        drawPanel();

    }

    public void drawPanel() {
        DefaultListModel<String> model = new DefaultListModel<String>();

        Vector<Order> orders = db.getRecentOrders();
        for (int i = 0; i < orders.size(); i++) {
            String orderContent = String
                    .format("Order #%d --- Total Cost: %.2f", orders.get(i).order_id, orders.get(i).cost_total);
            model.addElement(orderContent);
        }

        JList<String> orderList = new JList<>(model);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setLayoutOrientation(JList.VERTICAL);
        orderList.setVisibleRowCount(-1);

        JScrollPane scrollPane = new JScrollPane(orderList);
        scrollPane.setPreferredSize(new Dimension(340, 400));
        this.panel.removeAll();
        this.panel.add(scrollPane);
        this.panel.validate();
        this.panel.repaint();
    }

}