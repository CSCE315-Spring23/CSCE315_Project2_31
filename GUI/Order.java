import java.util.*;

public class Order {
    public int order_id;
    public double cost_total;
    public java.sql.Date date;
    public int customer_id;
    public int staff_id;
    public Vector<MyPair<Integer, Integer>> menu_items; // menu_id, quantity

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
