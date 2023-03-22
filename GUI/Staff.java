/**
 * <dl>
 * <dt><b>Staff</b></dt>
 * <dd>
 * A class that represents a staff member of a restaurant.
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
public class Staff {
    // Public fields for staff id, name, manager status, and associated restaurant
    // id
    public int staff_id;
    public String name;
    public boolean is_manager;
    public int restaurant_id;

    /**
     * 
     * Constructs a new Staff object with the specified staff id, name, manager
     * status, and associated restaurant id.
     * 
     * @param staff_id      the id of the staff member
     * @param name          the name of the staff member
     * @param is_manager    the manager status of the staff member
     * @param restaurant_id the id of the restaurant associated with the staff
     *                      member
     */
    public Staff(int staff_id, String name, boolean is_manager, int restaurant_id) {
        this.staff_id = staff_id;
        this.name = name;
        this.is_manager = is_manager;
        this.restaurant_id = restaurant_id;
    }
}
