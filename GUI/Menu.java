// import java.sql.*;
import java.util.*;

public class Menu {
    public int menu_id;
    public String name;
    public double price;
    public Vector<MyPair<Inventory, Integer>> inventory_items;

    public Menu(int menu_id, String name, double price, Vector<MyPair<Inventory, Integer>> inventory_items) {
        this.menu_id = menu_id;
        this.name = name;
        this.price = price;
        this.inventory_items = inventory_items;
    }
}
