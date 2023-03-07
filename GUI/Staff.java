public class Staff {
    public int staff_id;
    public String name;
    public boolean is_manager;
    public int restaurant_id;

    public Staff(int staff_id, String name, boolean is_manager, int restaurant_id) {
        this.staff_id = staff_id;
        this.name = name;
        this.is_manager = is_manager;
        this.restaurant_id = restaurant_id;
    }
}
