import java.sql.*;
import java.util.*;

/**
 * <dl>
 * <dt><b>Chick-fil-A Database</b></dt>
 * <dd>
 * The Data class handles all communication between the Chick-fil-A point of
 * sales(POS) GUI and PostgreSQL database.
 * </dd>
 * </dl>
 * 
 * @author Jeffrey Li
 * @author David Chi
 * @version 1.0
 * @since 2023-03-08
 */
public class Data {

    /**
     * The url for connecting to the database
     */
    public String database_url;
    /**
     * The username for connecting to the database.
     */
    public String username;
    /**
     * The password for connecting to the database.
     */
    public String password;
    /**
     * The instance of the database connection.
     */
    public Connection conn;

    Data(String database_url, String username, String password) {
        this.database_url = database_url;
        this.username = username;
        this.password = password;
        this.conn = null;
    }

    /**
     * Connects to the PostgreSQL database.
     *
     * @return boolean for success (true) or failure (false)
     */
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

    /**
     * Disconnects from the PostgreSQL database.
     *
     * @return boolean for success (true) or failure (false)
     */
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

    /**
     * Executes SQL query that has a return value.
     *
     * @param sqlStatement an SQL statement with a return value
     * @return a ResultSet result from query
     */
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
            e.printStackTrace();
            System.out.println("failed");
            // JOptionPane.showMessageDialog(null, "Error accessing Database.");
        }
        return null;
    }

    /**
     * Executes SQL query that does not have return value.
     *
     * @param sqlStatement an SQL statement without a return value
     */
    public void executeUpdateSQL(String sqlStatement) {
        try {
            // create a statement object
            Statement stmt = this.conn.createStatement();
            // send statement to DBMS
            stmt.executeUpdate(sqlStatement);
            // while (result.next()) {
            // resultString += result.getString(colName) + "\n";
            // }
            // return resultString;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed");
            // JOptionPane.showMessageDialog(null, "Error accessing Database.");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Accessing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Returns the Order Object from the database with specified order_id
     *
     * @param order_id an order_id int
     * @return a Order object resulting from the query
     */
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

    /**
     * Gets all the orders from the database that match the customer id.
     *
     * @param customer_id an for the customer to search by
     * @return a vector of orders matching the customer id
     */
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

    /**
     * Gets all the orders from the database that match the date.
     *
     * @param date a date to search by
     * @return a vector of orders matching the date
     */
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

    /**
     * Gets all the orders from the database.
     *
     * @return a vector of orders
     */
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

    /**
     * Gets the 25 most recent orders ordered by order_id and returns a
     * {@code Vector<Order>}
     *
     * @return a vector of recent Order object
     */
    public Vector<Order> getRecentOrders() {
        String sqlStatement = "SELECT * FROM orders ORDER BY order_id DESC LIMIT 25;";
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

    /**
     * Gets the menu item from the database that matches the menu_id.
     *
     * @param menu_id an id for the menu item to search by
     * @return a menu item matching the menu_id
     */
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

    /**
     * Gets the menu item from the database that matches the name.
     *
     * @param name a name to search by
     * @return an order matching the name
     */
    public Menu getMenuByName(String name) {
        String sqlStatement = "SELECT * FROM menu WHERE name = '" + name + "';";
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            res.next();
            Vector<MyPair<Integer, Integer>> inventory_items = this.getInventoryItemsByMenuId(res.getInt("menu_id"));
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

    /**
     * Gets all the menu items from the database.
     *
     * @return a vector of menu items
     */
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

    /**
     * Gets all the orders from the database that match the specified foodType.
     *
     * @param foodType a String for foodtype to search by
     * @return a vector of orders matching the foodType
     */
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

    /**
     * Gets the inventory item from the database that matches the inventory_id.
     *
     * @param inventory_id an id for the inventory item to search by
     * @return an inventory id that matches the inventory_id
     */
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

    /**
     * Gets inventory from the database that match the specified name.
     *
     * @param name a String for name to search by
     * @return a Inventory object matching the name
     */
    public Inventory getInventoryByName(String name) {
        String sqlStatement = "SELECT * FROM inventory WHERE name = '" + name + "';";
        System.out.println(sqlStatement);
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            if (res.next()) {
                Inventory out = new Inventory(
                        res.getInt("inventory_id"), res.getString("name"), res.getInt("quantity"));
                return out;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Gets all the inventory items from the database.
     *
     * @return a vector of inventory items
     */
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

    /**
     * Get the restaurant entity.
     *
     * @return a Restaurant object
     */
    public Restaurant getRestaurant() {
        String sqlStatement = "SELECT * FROM restaurant;";
        ResultSet res = this.executeSQL(sqlStatement);
        try {
            res.next();
            Restaurant out = new Restaurant(
                    res.getInt("restaurant_id"), res.getString("name"), res.getInt("revenue"));
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

    /**
     * Gets all the menu items that make use of an inventory item. That is, returns
     * all menu items in the
     * inventory_to_menu junction table such that the inventory_id matches the
     * provided parameter.
     *
     * @param inventory_id an id for the inventory item to search by
     * @return a vector of menu items that have the inventory item
     */
    public Vector<Menu> getMenuItemsByInventoryId(int inventory_id) {
        String sqlStatement = "SELECT * FROM inventory_to_menu WHERE inventory_id = " + inventory_id + ";";
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

    /**
     * Gets all the inventory items that are in a menu item. That is, returns all
     * inventory items in the
     * inventory_to_menu junction table such that the menu_id matches the provided
     * parameter.
     *
     * @param menu_id an id for the menu item to search by
     * @return a {@code Vector<MyPair<Integer, Integer>>} of (inventory item,
     *         quantity)
     *         pairs that are in the menu item
     */
    public Vector<MyPair<Integer, Integer>> getInventoryItemsByMenuId(int menu_id) {
        String sqlStatement = "SELECT * FROM inventory_to_menu WHERE menu_id = " + menu_id + ";";
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

    /**
     * Gets all the order items that make use of a menu item. That is, returns all
     * order items in the
     * menu_to_order junction table such that the menu_id matches the provided
     * parameter.
     *
     * @param menu_id an id for the menu item to search by
     * @return a vector of order items that have the menu item
     */
    public Vector<Order> getOrdersByMenuId(int menu_id) {
        String sqlStatement = "SELECT * FROM menu_to_order WHERE menu_id = " + menu_id + ";";
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

    /**
     * Gets menu items that are in a order from the database given the order_id
     *
     * @param order_id a int for order_id to search by
     * @return a {@code Vector<MyPair<Integer, Integer>>} object with the consisting
     *         of
     *         pairs
     *         of the menu_id and quantity
     */
    public Vector<MyPair<Integer, Integer>> getMenuItemsByOrderId(int order_id) {
        String sqlStatement = "SELECT * FROM menu_to_order WHERE order_id = " + order_id + ";";
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

    /**
     * Makes order and make all necessary changes to database and returns the status
     * of the queries
     * - adds new order entry into orders table
     * - adds all menu_to_order entries
     * - decrements all inventory items by the amounts used
     * 
     *
     * @param cost_total  a int for cost_total of the order
     * @param date        a date for date of the order
     * @param customer_id a int for customer_id of the order
     * @param staff_id    a int for staff_id of the order
     * @param menu_items  a {@code Vector<MyPair<Integer, Integer>>} for menu_items
     *                    in the
     *                    order
     * @return newly generated order_id
     */
    public int makeOrder(
            double cost_total, java.sql.Date date, int customer_id, int staff_id,
            Vector<MyPair<Integer, Integer>> menu_items) {
        // Make Order Entry
        String sqlStatement1 = "INSERT INTO orders (cost_total, date, customer_id, staff_id) " +
                "VALUES (" + cost_total + ", '" + date.toString() + "', " + customer_id + ", " + staff_id + ") " +
                "RETURNING order_id;";
        int order_id = -1;
        try {
            ResultSet res = this.executeSQL(sqlStatement1);
            if (res.next()) {
                order_id = res.getInt("order_id");
                // use the order_id value as needed
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while creating order entry.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return -1;
        }

        // Make menu_to_order Entry(ies)
        for (int i = 0; i < menu_items.size(); i++) {
            String sqlStatement2 = "INSERT INTO menu_to_order (menu_id, order_id, quantity) VALUES " +
                    "(" + menu_items.get(i).getFirst() + ", " + order_id + ", " + menu_items.get(i).getSecond() + ");";
            try {
                this.executeUpdateSQL(sqlStatement2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Above error happened while creating menu_to_order entry.");
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return -1;
            }
        }

        // loop through all menu items, read inventory for each menu item from
        // inventory_to_menu,
        // decrement each inventory item by (quantity in inventory_to_menu * quantity of
        // menu item in order)
        for (int i = 0; i < menu_items.size(); i++) {
            Vector<MyPair<Integer, Integer>> inventory_items = this
                    .getInventoryItemsByMenuId(menu_items.get(i).getFirst());
            // System.out.println(inventory_items.size());
            for (int j = 0; j < inventory_items.size(); j++) {
                String sqlStatement3 = "UPDATE inventory SET quantity = quantity - "
                        + inventory_items.get(j).getSecond()
                        + " WHERE inventory_id = " + inventory_items.get(j).getFirst() + ";";
                // System.out.println("Calling SQL Command: " + sqlStatement3);
                try {
                    this.executeUpdateSQL(sqlStatement3);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Above error happened while decrementing inventory entry.");
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    return -1;
                }
            }
        }

        return order_id;
    }

    /**
     * Removes order and make all necessary changes to database and
     * - removes all corresponding menu_to_order entries
     * - removes order entry into orders table
     *
     * @param order_id a int for order_id to search by
     * @return a boolean value for success (true) or failure (false)
     */
    public boolean removeOrder(int order_id) {
        String sqlStatement1 = "DELETE * FROM menu_to_order WHERE order_id = " + order_id + ";";
        try {
            this.executeUpdateSQL(sqlStatement1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while deleting menu_to_order entry (called from removeOrder).");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false; // ERROR
        }

        String sqlStatement2 = "DELETE FROM order WHERE order_id = " + order_id + ";";
        try {
            this.executeUpdateSQL(sqlStatement2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while deleting order entry.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false; // ERROR
        }

        return true;
    }

    /**
     * Adds a menu item and make all necessary changes to database and
     * - adds new menu entry into menu table
     * - adds all inventory_to_menu entries
     *
     * @param name            a name for the new menu item
     * @param price           a double for price of the menu item
     * @param type            a string for type of the menu item
     * @param inventory_items a {@code Vector<MyPair<Integer, Integer>>} for
     *                        inventory_items
     *                        in the menu item
     * @return a boolean value for success (true) or failure (false)
     */
    public boolean addMenuItem(String name, double price, String type,
            Vector<MyPair<Integer, Integer>> inventory_items) {
        // Make Menu Entry
        String sqlStatement1 = "INSERT INTO menu (name, price, type) VALUES ('" + name + "', " + price + ", '" + type
                + "') RETURNING menu_id;";

        System.out.println(sqlStatement1);

        int menu_id = -1;
        try {
            ResultSet res = this.executeSQL(sqlStatement1);
            if (res.next()) {
                menu_id = res.getInt("menu_id");
                System.out.println("new menuItem with menuid: " + menu_id);
                // use the order_id value as needed
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while creating order entry.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        // Make inventory_to_menu Entry(ies)
        for (int i = 0; i < inventory_items.size(); i++) {
            String sqlStatement2 = "INSERT INTO inventory_to_menu (inventory_id, menu_id, quantity) VALUES "
                    + "(" + inventory_items.get(i).getFirst() + ", " + menu_id + ", "
                    + inventory_items.get(i).getSecond() + ");";
            try {
                this.executeUpdateSQL(sqlStatement2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Above error happened while creating inventory_to_menu entry.");
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * Removes a menu item and make all necessary changes to database and
     * - removes all corresponding inventory_to_menu entries
     * - removes all corresponding menu_to_order entries
     * - removes menu entry from menu table
     *
     * @param menu_id a menu_id to search by
     * @return a boolean value for success (true) or failure (false)
     */
    public boolean removeMenuItem(int menu_id) {
        String sqlStatement1 = "DELETE * FROM inventory_to_menu WHERE menu_id = " + menu_id + ";";
        try {
            this.executeUpdateSQL(sqlStatement1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(
                    "Above error happened while deleting inventory_to_menu entry (called from removeMenuItem).");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false; // ERROR
        }

        String sqlStatement2 = "DELETE * FROM menu_to_order WHERE menu_id = " + menu_id + ";";
        try {
            this.executeUpdateSQL(sqlStatement2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while deleting menu_to_order entry (called from removeMenuItem).");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false; // ERROR
        }

        String sqlStatement3 = "DELETE FROM menu WHERE menu_id = " + menu_id + ";";
        try {
            this.executeUpdateSQL(sqlStatement3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while deleting menu entry.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false; // ERROR
        }

        return true;
    }

    /**
     * Updates a menu item and sets its price.
     *
     * @param menu_id  a menu_id to search by
     * @param newPrice a new price to set
     * @return a boolean value for success (true) or failure (false)
     */
    public boolean updateMenuPriceById(int menu_id, double newPrice) {
        String query = "UPDATE menu SET price = " + newPrice + " WHERE menu_id = " + menu_id + ";";
        try {
            this.executeUpdateSQL(query);
            return true; // SUCCESS
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false; // ERROR
    }

    /**
     * Updates a menu item and sets its price.
     *
     * @param name     a name to search by
     * @param newPrice a new price to set
     * @return a boolean value for success (true) or failure (false)
     */
    public boolean updateMenuPriceByName(String name, double newPrice) {
        String query = "UPDATE menu SET price = " + newPrice + " WHERE name = '" + name + "';";
        try {
            this.executeUpdateSQL(query);
            return true; // SUCCESS
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false; // ERROR
    }

    /**
     * Adds a menu item to an order.
     *
     * @param order_id an order id to search by
     * @param menu_id  a menu_id to add
     * @param quantity a quantity for the menu item added
     * @return a boolean value for success (true) or failure (false)
     */
    public boolean addMenuItemToOrder(int order_id, int menu_id, int quantity) {
        // Get order
        Order original_order = this.getOrder(order_id);
        if (original_order == null) {
            System.out.println("addMenuItemToOrder error: order not found");
            return false;
        }
        int original_quantity = 0;

        for (int i = 0; i < original_order.menu_items.size(); i++) {
            MyPair<Integer, Integer> menu_item = original_order.menu_items.get(i);
            if (menu_item.getFirst() == menu_id) {
                original_quantity = menu_item.getSecond();
                break;
            }
        }
        int new_quantity = original_quantity + quantity;

        String sqlStatement;
        // If menu item was present
        if (original_quantity > 0) {
            sqlStatement = "UPDATE menu_to_order SET quantity = " + new_quantity + " WHERE "
                    + "(menu_id = " + menu_id + " AND order_id = " + order_id + ");";
        } else {
            sqlStatement = "INSERT INTO menu_to_order (menu_id, order_id, quantity) VALUES "
                    + "(" + menu_id + ", " + order_id + ", " + new_quantity + ");";
        }

        try {
            this.executeUpdateSQL(sqlStatement);
            System.out.println("new (updated) menu_to_order for order: " + order_id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while updating menu_to_order entry.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Removes a menu item from an order.
     *
     * @param order_id a order_id to search by
     * @param menu_id  a menu_id to search b
     * @return a boolean value for success (true) or failure (false)
     */
    public boolean removeMenuItemFromOrder(int order_id, int menu_id) {
        // Get order
        Order original_order = this.getOrder(order_id);
        if (original_order == null) {
            System.out.println("removeMenuItemFromOrder error: order not found");
            return false;
        }
        boolean present = false;

        for (int i = 0; i < original_order.menu_items.size(); i++) {
            MyPair<Integer, Integer> menu_item = original_order.menu_items.get(i);
            if (menu_item.getFirst() == menu_id) {
                present = true;
                break;
            }
        }
        if (present == false) {
            System.out.println("Error deleting item not present in order.");
        }

        String sqlStatement = "DELETE FROM menu_to_order WHERE "
                + "(menu_id = " + menu_id + " AND order_id = " + order_id + ");";

        try {
            this.executeUpdateSQL(sqlStatement);
            System.out.println("deleted menu_to_order for order: " + order_id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Above error happened while deleting menu_to_order entry.");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Updates the price of an order to a new cost from the user
     *
     * @param order_id     id of the order that is being updated
     * @param newCostTotal the new price of the order
     *                     o be set
     * @return A boolean that indicates success or failure of the update
     */

    public boolean updateOrderPriceById(int order_id, double newCostTotal) {
        String template = "UPDATE orders SET cost_total = " + newCostTotal + " WHERE order_id = " + order_id + ";";
        try {
            this.executeUpdateSQL(template);
            return true; // SUCCESS
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false; // ERROR
    }

    /**
     * Updates the revenue of a restaurnt by the restaurant id and summing order
     * totals
     *
     * @param restaurant_id id of the restaurant that is being updated
     * @return A boolean that indicates success or failure of the update
     */

    public boolean updateRevenue(int restaurant_id) {
        String sqlStatement1 = "SELECT SUM(cost_total) AS total_revenue FROM orders;";
        double revenue = -1;
        try {
            ResultSet res = this.executeSQL(sqlStatement1);
            res.next();
            revenue = res.getDouble("total_revenue");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        String sqlStatement2 = "UPDATE restaurant SET revenue = " + revenue + " WHERE restaurant_id = " + restaurant_id
                + ";";
        try {
            this.executeUpdateSQL(sqlStatement2);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Updates the quantity of an inventory item by the ID
     *
     * @param inventory_id id of the inventory item being updated
     * @param quantity     amount of Inventory being restocked/deleted
     * @return A boolean that indicates success or failure of the update
     */

    public boolean addInventoryQuantityById(int inventory_id, int quantity) {
        String sqlStatement = "UPDATE inventory SET quantity = (quantity + " + quantity
                + ") WHERE inventory_id = " + inventory_id + ";";
        try {
            this.executeUpdateSQL(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Updates the quantity of an inventory item by name
     *
     * @param name     name of the Inventory item to be restocked
     * @param quantity amount of Inventory being restocked/deleted
     * @return A boolean that indicates success or failure of the update
     */

    public boolean addInventoryQuantityByName(String name, int quantity) {
        String sqlStatement = "UPDATE inventory SET quantity = (quantity + " + quantity
                + ") WHERE name = '" + name + "';";
        try {
            this.executeUpdateSQL(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Updates and decreases the quantity of an inventory item by ID
     *
     * @param inventory_id id of the Inventory item to be updated
     * @param quantity     amount of Inventory being restocked/deleted
     * @return A boolean that indicates success or failure of the update
     */

    public boolean deleteInventoryQuantityById(int inventory_id, int quantity) {
        String sqlStatement = "UPDATE inventory SET quantity = (quantity - " + quantity
                + ") WHERE inventory_id = " + inventory_id + ";";
        try {
            this.executeUpdateSQL(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Creates a new Inventory entry in the Database
     *
     * @param name     name of the Inventory item to be created
     * @param quantity amount of Inventory being created
     * @return A boolean that indicates success or failure of the creation
     */

    public boolean addNewInventoryItem(String name, int quantity) {
        String sqlStatement = "INSERT INTO inventory (name, quantity) VALUES ('" + name + "', " + quantity + ");";
        try {
            this.executeUpdateSQL(sqlStatement);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

}