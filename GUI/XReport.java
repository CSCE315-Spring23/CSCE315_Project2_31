import java.sql.*;

/**
 * 
 * A class that represents a XReport object.
 */
public class XReport {

    // Public fields for XReport report_date, total_sales, restaurant_id
    public Timestamp report_date;
    public double total_sales;
    public int restaurant_id;

    /**
     * Constructs a new XReport object with the specified XReport parameters
     * 
     * @param report_date   the report_date of the XReport
     * @param total_sales   the total_sales of the XReport
     * @param restaurant_id the restaurant_id of the XReport
     */
    public XReport(Timestamp report_date, double total_sales, int restaurant_id) {
        this.report_date = report_date;
        this.total_sales = total_sales;
        this.restaurant_id = restaurant_id;
    }
}
