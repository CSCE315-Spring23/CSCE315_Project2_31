import java.util.*;

/**
 * <dl>
 * <dt><b>Order</b></dt>
 * <dd>
 * The Order class represents a class that takes in the parameters required for
 * an order.
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
public class Order {
    // Public fields for order id, total cost, date, customer id, staff id, and menu
    // items
    public int order_id;
    public double cost_total;
    public java.sql.Date date;
    public int customer_id;
    public int staff_id;
    public Vector<MyPair<Integer, Integer>> menu_items; // menu_id, quantity

    /**
     * 
     * Constructs a new Order object with the specified order id, total cost, date,
     * customer id, staff id, and menu items.
     * 
     * @param order_id    the id of the order
     * @param cost_total  the total cost of the order
     * @param date        the date the order was placed
     * @param customer_id the id of the customer who placed the order
     * @param staff_id    the id of the staff member who took the order
     * @param menu_items  a vector of MyPair objects representing the menu items in
     *                    the order
     */
    Order(int order_id, double cost_total, java.sql.Date date, int customer_id, int staff_id,
            Vector<MyPair<Integer, Integer>> menu_items) {
        this.order_id = order_id;
        this.cost_total = cost_total;
        this.date = date;
        this.customer_id = customer_id;
        this.staff_id = staff_id;
        this.menu_items = menu_items;
    }
}
