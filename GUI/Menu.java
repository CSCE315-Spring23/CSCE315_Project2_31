
// import java.sql.*;
import java.util.*;

/**
 * <dl>
 * <dt><b>Menu</b></dt>
 * <dd>
 * A class that represents a menu item.
 * </dd>
 * </dl>
 * 
 * @author Art Young
 * @author Jeffrey Li
 * @author Andrew Mao
 * @author David Chi
 * @version 1.0
 * @since 2023-03-21
 */
public class Menu {
    // Public fields for menu id, name, price, type, and inventory items
    public int menu_id;
    public String name;
    public double price;
    public String type;
    public Vector<MyPair<Integer, Integer>> inventory_items; // inventory_id, quantity

    /**
     * 
     * Constructs a new Menu object with the specified menu id, name, price, type,
     * and inventory items.
     * 
     * @param menu_id         the id of the menu item
     * @param name            the name of the menu item
     * @param price           the price of the menu item
     * @param type            the type of the menu item
     * @param inventory_items a vector of MyPair objects representing the inventory
     *                        items needed for the menu item
     */
    public Menu(int menu_id, String name, double price, String type, Vector<MyPair<Integer, Integer>> inventory_items) {
        this.menu_id = menu_id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.inventory_items = inventory_items;
    }
}
