/**
 * <dl>
 * <dt><b>Restaurant</b></dt>
 * <dd>
 * The Restaurant class represents the Chick-fil-a restaurant, specifically
 * ours.
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
public class Restaurant {
    // Public fields for restaurant id, name, and revenue
    public int restaurant_id;
    public String name;
    public double revenue;

    /**
     * 
     * Constructs a new Restaurant object with the specified restaurant id, name,
     * and revenue.
     * 
     * @param restaurant_id the id of the restaurant
     * @param name          the name of the restaurant
     * @param revenue       the revenue of the restaurant
     */
    public Restaurant(int restaurant_id, String name, double revenue) {
        this.restaurant_id = restaurant_id;
        this.name = name;
        this.revenue = revenue;
    }
}
