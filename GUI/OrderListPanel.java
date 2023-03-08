import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class OrderListPanel {
    JPanel panel;

    OrderListPanel(Data db) {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(350, 400));
        this.panel.setBackground(Color.gray);

        JPanel orderListView = new JPanel(new GridLayout(10, 1, 10, 10)); 
        orderListView.setPreferredSize(new Dimension(340, 400));
        
        Vector<Order> orders = db.getRecentOrders();
        for (int i = orders.size()-9; i < orders.size(); i++){
            JPanel order = new JPanel();
            order.setPreferredSize(new Dimension(300, 50));
            JTextArea orderContent = new JTextArea();
            orderContent.setPreferredSize(new Dimension(300, 50));
            orderContent.append("Order #" + orders.get(i).order_id + " --- Total Cost: " + orders.get(i).cost_total);
            order.add(orderContent);
            orderListView.add(order);
        }

        this.panel.add(orderListView);
    }
}