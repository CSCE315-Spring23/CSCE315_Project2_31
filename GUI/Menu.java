// import java.sql.*;
import java.util.*;

public class Menu {
    public int menu_id;
    public String name;
    public double price;
    public String type;
    public Vector<MyPair<Integer, Integer>> inventory_items; // inventory_id, quantity

    public Menu(int menu_id, String name, double price, String type, Vector<MyPair<Integer, Integer>> inventory_items) {
        this.menu_id = menu_id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.inventory_items = inventory_items;
    }
}
