// import java.util.*;
/**
 * 
 * A class that represents an inventory item.
 */
public class Inventory {
    // Public fields for inventory id, name, and quantity
    public int inventory_id;
    public String name;
    public int quantity;

    /**
     * 
     * Constructs a new Inventory object with the specified inventory id, name, and
     * quantity.
     * 
     * @param inventory_id the id of the inventory item
     * @param name         the name of the inventory item
     * @param quantity     the quantity of the inventory item
     */
    public Inventory(int inventory_id, String name, int quantity) {
        this.inventory_id = inventory_id;
        this.name = name;
        this.quantity = quantity;
    }
}
