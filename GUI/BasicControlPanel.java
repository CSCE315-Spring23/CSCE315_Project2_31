import java.awt.*;
import javax.swing.*;

/**
 * <dl>
 * <dt><b>Basic Control Panel</b></dt>
 * <dd>
 * BasicControlPanel is a class that creates a panel with two buttons for
 * checking in and checking out, respectively.
 * It also keeps track of the total cost and displays it on a label.
 * </dd>
 * </dl>
 * 
 * @author Jeffrey Li
 * @author David Chi
 * @author Andrew Mao
 * @author Art Young
 * @version 1.0
 * @since 2023-03-21
 */
public class BasicControlPanel {
    JPanel panel;
    double totalCost;
    Data db;
    JLabel orderTotal;

    /**
     * BasicControlPanel constructor creates a panel with check in and check out
     * buttons,
     * initializes total cost to zero, and sets the layout manager to FlowLayout
     * with left alignment.
     * 
     * @param db a reference to the database
     */
    BasicControlPanel(Data db) {
        // Set the layout manager to FlowLayout with left alignment
        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.db = db;
        this.totalCost = 0;

        // Create some buttons and add them to the panel
        JButton checkInBtn = new JButton("Check In");
        JButton checkOutBtn = new JButton("Check Out");
        panel.removeAll();

        this.panel.add(checkInBtn);
        this.panel.add(checkOutBtn);

        this.panel.validate();
        this.panel.repaint();
    }
}