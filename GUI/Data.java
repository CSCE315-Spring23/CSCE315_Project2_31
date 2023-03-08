import java.sql.*;
import java.util.*;

public class Data {
    public String database_url;
    public String username;
    public String password;
    public Connection conn;

    Data(String database_url, String username, String password) {
        this.database_url = database_url;
        this.username = username;
        this.password = password;
        this.conn = null;
    }

    public boolean connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(this.database_url, this.username, this.password);
            return true; // SUCCESS
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false; // ERROR
    }

    public boolean disconnect() {
        try {
            this.conn.close();
            return true; // SUCCESS
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false; // ERROR
    }

    public ResultSet executeSQL(String sqlStatement) {
        try {
            // create a statement object
            Statement stmt = this.conn.createStatement();
            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            return result;
            // while (result.next()) {
            // resultString += result.getString(colName) + "\n";
            // }
            // return resultString;
        } catch (Exception e) {
            System.out.println("failed");
            // JOptionPane.showMessageDialog(null, "Error accessing Database.");
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Accessing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Order getOrder(int order_id) {
        String sqlStatement = "SELECT * FROM orders WHERE order_id = " + order_id + ";";
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            res.next();
            Vector<MyPair<Integer, Integer>> menu_items = this.getMenuItemsByOrderId(order_id);
            Order out = new Order(
                    res.getInt("order_id"), res.getDouble("cost_total"), res.getDate("date"),
                    res.getInt("customer_id"), res.getInt("staff_id"),
                    menu_items);
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Order> getOrdersByCustomerId(int customer_id) {
        String sqlStatement = "SELECT * FROM orders WHERE customer_id = " + customer_id + ";";
        Vector<Order> out = new Vector<Order>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                Vector<MyPair<Integer, Integer>> menu_items = this.getMenuItemsByOrderId(res.getInt("order_id"));
                Order order = new Order(
                        res.getInt("order_id"), res.getDouble("cost_total"), res.getDate("date"),
                        res.getInt("customer_id"), res.getInt("staff_id"),
                        menu_items);
                out.add(order);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Order> getOrdersByDate(java.sql.Date date) {
        String sqlStatement = "SELECT * FROM orders WHERE date = '" + date.toString() + "';";
        Vector<Order> out = new Vector<Order>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                Vector<MyPair<Integer, Integer>> menu_items = this.getMenuItemsByOrderId(res.getInt("order_id"));
                Order order = new Order(
                        res.getInt("order_id"), res.getDouble("cost_total"), res.getDate("date"),
                        res.getInt("customer_id"), res.getInt("staff_id"),
                        menu_items);
                out.add(order);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Order> getAllOrders() {
        String sqlStatement = "SELECT * FROM orders;";
        Vector<Order> out = new Vector<Order>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                Vector<MyPair<Integer, Integer>> menu_items = this.getMenuItemsByOrderId(res.getInt("order_id"));
                Order order = new Order(
                        res.getInt("order_id"), res.getDouble("cost_total"), res.getDate("date"),
                        res.getInt("customer_id"), res.getInt("staff_id"),
                        menu_items);
                out.add(order);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Order> getRecentOrders() {
        String sqlStatement = "SELECT * FROM orders LIMIT 10;";
        Vector<Order> out = new Vector<Order>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                Vector<MyPair<Integer, Integer>> menu_items = this.getMenuItemsByOrderId(res.getInt("order_id"));
                Order order = new Order(
                        res.getInt("order_id"), res.getDouble("cost_total"), res.getDate("date"),
                        res.getInt("customer_id"), res.getInt("staff_id"),
                        menu_items);
                out.add(order);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Menu getMenu(int menu_id) {
        String sqlStatement = "SELECT * FROM menu WHERE menu_id = " + menu_id + ";";
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            res.next();
            Vector<MyPair<Integer, Integer>> inventory_items = this.getInventoryItemsByMenuId(menu_id);
            Menu out = new Menu(
                    res.getInt("menu_id"), res.getString("name"), res.getDouble("price"), res.getString("type"),
                    inventory_items);
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Menu> getAllMenuItems() {
        String sqlStatement = "SELECT * FROM menu;";
        Vector<Menu> out = new Vector<Menu>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                Vector<MyPair<Integer, Integer>> inventory_items = this
                        .getInventoryItemsByMenuId(res.getInt("menu_id"));
                Menu item = new Menu(
                        res.getInt("menu_id"), res.getString("name"), res.getDouble("price"), res.getString("type"),
                        inventory_items);
                out.add(item);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Menu> getMenuByType(String foodType) {
        String sqlStatement = "SELECT * FROM menu WHERE type = '" + foodType + "';";
        Vector<Menu> out = new Vector<Menu>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                Vector<MyPair<Integer, Integer>> inventory_items = this
                        .getInventoryItemsByMenuId(res.getInt("menu_id"));
                Menu item = new Menu(
                        res.getInt("menu_id"), res.getString("name"), res.getDouble("price"), res.getString("type"),
                        inventory_items);
                out.add(item);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Inventory getInventory(int inventory_id) {
        String sqlStatement = "SELECT * FROM inventory WHERE inventory_id = " + inventory_id + ";";
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            res.next();
            Inventory out = new Inventory(
                    res.getInt("inventory_id"), res.getString("name"), res.getInt("quantity"));
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Inventory> getAllInventoryItems() {
        String sqlStatement = "SELECT * FROM inventory;";
        Vector<Inventory> out = new Vector<Inventory>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                Inventory item = new Inventory(
                        res.getInt("inventory_id"), res.getString("name"), res.getInt("quantity"));
                out.add(item);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Handling Junctions
    // * Inventory-Menu
    // * Menu-Order
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Vector<Menu> getMenuItemsByInventoryId(int inventory_id) {
        String sqlStatement = "SELECT * FROM inventory_to_menu WHERE inventory_id = " + inventory_id + ';';
        Vector<Menu> out = new Vector<Menu>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                // (inventory_id, menu_id, quantity)
                Menu item = this.getMenu(res.getInt("menu_id"));
                out.add(item);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<MyPair<Integer, Integer>> getInventoryItemsByMenuId(int menu_id) {
        String sqlStatement = "SELECT * FROM inventory_to_menu WHERE inventory_id = " + menu_id + ';';
        Vector<MyPair<Integer, Integer>> out = new Vector<MyPair<Integer, Integer>>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                // (inventory_id, menu_id, quantity)
                out.add(new MyPair<Integer, Integer>(res.getInt("inventory_id"), res.getInt("quantity")));
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<Order> getOrdersByMenuId(int menu_id) {
        String sqlStatement = "SELECT * FROM menu_to_order WHERE menu_id = " + menu_id + ';';
        Vector<Order> out = new Vector<Order>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                // (menu_id, order_id, quantity)
                Order item = this.getOrder(res.getInt("order_id"));
                out.add(item);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Vector<MyPair<Integer, Integer>> getMenuItemsByOrderId(int order_id) {
        String sqlStatement = "SELECT * FROM menu_to_order WHERE order_id = " + order_id + ';';
        Vector<MyPair<Integer, Integer>> out = new Vector<MyPair<Integer, Integer>>();
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            while (res.next()) {
                // (menu_id, order_id, quantity)
                out.add(new MyPair<Integer, Integer>(res.getInt("menu_id"), res.getInt("quantity")));
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Modifying (makeOrder, addItem, changeMenuPrice, changeOrderPrice,
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////// updateInventory,
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////// )
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean makeOrder(int order_id, double cost_total, String date, int customer_id, int staff_id) {
        String template = "INSERT INTO orders (order_id, cost_total, date, customer_id, staff_id) VALUES " +
                "(" + order_id + ", " + cost_total + ", '" + date + "', " + customer_id + ", " + staff_id + ");";

        try {
            this.executeSQL(template);
            return true; // SUCCESS
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false; // ERROR
    }

    public boolean addMenuItem() {
        return false;
    }

    public boolean addItemToOrder(String item_name) {
        return false;
    }

    public boolean removeItemFromOrder() {
        return false;
    }

    public boolean changeMenuPrice(int menu_id, double new_price) {

        return false;
    }

    public boolean changeOrderPrice() {
        return false;
    }
}