/**
 * 
 * A class that represents a customer object.
 */
public class Customer {

    // Public fields for customer id and name
    public int customer_id;
    public String name;

    /**
     * Constructs a new Customer object with the specified customer id and name.
     * 
     * @param customer_id the id of the customer
     * @param name        the name of the customer
     */
    public Customer(int customer_id, String name) {
        this.customer_id = customer_id;
        this.name = name;
    }
}
