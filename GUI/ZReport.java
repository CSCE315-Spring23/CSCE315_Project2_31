import java.sql.*;

/**
 * 
 * A class that represents a ZReport object.
 */
public class ZReport {

    // Public fields for ZReport id and name
    public int report_id;
    public Timestamp report_date;
    public double total_sales;
    public int restaurant_id;

    /**
     * Constructs a new Customer object with the specified customer id and name.
     * 
     * @param report_id     the id of the ZReport
     * @param report_date   the report_date of the ZReport
     * @param total_sales   the total_sales of the ZReport
     * @param restaurant_id the restaurant_id of the ZReport
     */
    public ZReport(int report_id, Timestamp report_date, double total_sales, int restaurant_id) {
        this.report_id = report_id;
        this.report_date = report_date;
        this.total_sales = total_sales;
        this.restaurant_id = restaurant_id;
    }
}
