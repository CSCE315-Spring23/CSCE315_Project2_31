import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * <dl>
 * <dt><b>OrderListPanel</b></dt>
 * <dd>
 * The OrderListPanel class represents a JPanel that displays a list of recent
 * orders.
 * It uses a Data object to retrieve the orders from a database and creates a
 * JList to display them.
 * The list is updated every time the drawPanel method is called.
 * </dd>
 * </dl>
 * 
 * @author David Chi
 * @author Jeffrey Li
 * @author Art Young
 * @author Andrew Mao
 * @version 1.0
 * @since 2023-03-21
 */
public class OrderListPanel {
    JPanel panel;
    Data db;

    /**
     * Constructs an OrderListPanel with a specified Data object.
     * The panel is initialized with a gray background and a preferred size of
     * 350x700.
     * The drawPanel method is called to populate the list with recent orders.
     * 
     * @param db the Data object to retrieve orders from
     */
    OrderListPanel(Data db) {
        this.panel = new JPanel();
        this.db = db;

        this.panel.setPreferredSize(new Dimension(350, 700));
        this.panel.setBackground(Color.gray);

        drawPanel();

    }

    /**
     * Populates the panel with a list of recent orders.
     * The method retrieves the orders from the Data object, formats them as
     * Strings, and adds them to a DefaultListModel.
     * The model is used to create a JList with a vertical layout and a scroll pane.
     * The panel is updated with the new JList.
     */
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
        scrollPane.setPreferredSize(new Dimension(340, 600));
        this.panel.removeAll();
        this.panel.add(scrollPane);
        this.panel.validate();
        this.panel.repaint();
    }

}