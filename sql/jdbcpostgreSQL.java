import java.sql.*;

/*
CSCE 315
9-27-2021 Lab
 */
public class jdbcpostgreSQL {

  // Commands to run this script
  // This will compile all java files in this directory
  // javac *.java
  // This command tells the file where to find the postgres jar which it needs to
  // execute postgres commands, then executes the code
  // Windows: java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL
  // Mac/Linux: java -cp ".:postgresql-42.2.8.jar" jdbcpostgreSQL

  // MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE
  public static void main(String args[]) {

    String[] queries = {
        // List all customers
        "SELECT * FROM customers LIMIT 20;",

        // List all inventory items
        "SELECT * FROM inventory LIMIT 20;",

        // List all menu items
        "SELECT * FROM menu LIMIT 20;",

        // List all orders
        "SELECT * FROM orders LIMIT 20;",

        // List all staff members
        "SELECT * FROM staff LIMIT 20;",

        // List all restaurants
        "SELECT * FROM restaurant LIMIT 20;",

        // List all inventory items and their associated menu items
        "SELECT inventory.name, menu.name " +
            "FROM inventory_to_menu " +
            "INNER JOIN inventory ON inventory_to_menu.inventory_id = inventory.inventory_id " +
            "INNER JOIN menu ON inventory_to_menu.menu_id = menu.menu_id;",

        // List all menu items and their associated inventory items
        "SELECT menu.name, inventory.name " +
            "FROM inventory_to_menu " +
            "INNER JOIN menu ON inventory_to_menu.menu_id = menu.menu_id " +
            "INNER JOIN inventory ON inventory_to_menu.inventory_id = inventory.inventory_id;",

        // List all menu items and their associated orders
        "SELECT menu.name, orders.order_id " +
            "FROM menu_to_order " +
            "INNER JOIN menu ON menu_to_order.menu_id = menu.menu_id " +
            "INNER JOIN orders ON menu_to_order.order_id = orders.order_id;",

        // List all orders and their associated menu items
        "SELECT orders.order_id, menu.name " +
            "FROM menu_to_order " +
            "INNER JOIN orders ON menu_to_order.order_id = orders.order_id " +
            "INNER JOIN menu ON menu_to_order.menu_id = menu.menu_id;",

        // List all orders placed by a specific customer
        "SELECT * FROM orders WHERE customer_id = 3;",

        // List all inventory items with a quantity less than 10
        "SELECT * FROM inventory WHERE quantity < 10;",

        // List all staff members who are managers
        "SELECT * FROM staff WHERE is_manager = TRUE;",

        // List all orders and their associated customer and staff member information
        "SELECT orders.order_id, customers.name AS customer_name, staff.name AS staff_name " +
            "FROM orders " +
            "INNER JOIN customers ON orders.customer_id = customers.customer_id " +
            "INNER JOIN staff ON orders.staff_id = staff.staff_id;",

        // Get the count of each menu item
        "SELECT menu.name, COUNT(menu_to_order.menu_id) AS frequency " +
            "FROM menu_to_order " +
            "INNER JOIN menu ON menu_to_order.menu_id = menu.menu_id " +
            "GROUP BY menu.name;",

        // Get the top 20 dates with the most money
        "SELECT date, SUM(cost_total) AS total_cost " +
            "FROM orders " +
            "GROUP BY date " +
            "ORDER BY total_cost DESC " +
            "LIMIT 20;",

        // Get top 20 customers that spent the most
        "SELECT customers.name AS customer_name, SUM(orders.cost_total) AS total_cost " +
            "FROM customers " +
            "INNER JOIN orders ON customers.customer_id = orders.customer_id " +
            "GROUP BY customers.customer_id " +
            "ORDER BY total_cost DESC " +
            "LIMIT 20;",

        // Get the number of unique dates in the orders table
        "SELECT COUNT(DISTINCT date) AS num_dates " +
            "FROM orders;",

        // Get total revenue from orders
        "SELECT SUM(cost_total) AS total_revenue " +
            "FROM orders;",

        // Get all orders placed on a specific date
        "SELECT * FROM orders WHERE date = '2022/12/3';"
    };

    // Building the connection with your credentials
    Connection conn = null;
    String teamNumber = "team_31";
    String dbName = "csce315331_" + teamNumber;
    String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    dbSetup myCredentials = new dbSetup();

    // Connecting to the database
    try {
      conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }

    System.out.println("Opened database successfully");

    try {
      // create a statement object
      Statement stmt = conn.createStatement();

      // Running a query
      // TODO: update the sql command here
      String sqlStatement = queries[0];

      // send statement to DBMS
      // This executeQuery command is useful for data retrieval
      ResultSet result = stmt.executeQuery(sqlStatement);
      // OR
      // This executeUpdate command is useful for updating data
      // int result = stmt.executeUpdate(sqlStatement);

      // OUTPUT
      // You will need to output the results differently depeninding on which function
      // you use
      System.out.println("--------------------Query Results--------------------");
      ResultSetMetaData metadata = result.getMetaData();
      int numColumns = metadata.getColumnCount();
      for (int i = 1; i <= numColumns; i++) {
        System.out.print(metadata.getColumnName(i) + "\t");
      }
      System.out.println();

      // Print the results
      while (result.next()) {
        for (int i = 1; i <= numColumns; i++) {
          System.out.print(result.getString(i) + "\t");
        }
        System.out.println();
      }
      // OR
      // System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }

    // closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch (Exception e) {
      System.out.println("Connection NOT Closed.");
    } // end try catch
  }// end main
}// end Class
