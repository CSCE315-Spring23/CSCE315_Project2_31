import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/*
  TODO:
  1) Change credentials for your own team's database
  2) Change SQL command to a relevant query that retrieves a small amount of data
  3) Create a JTextArea object using the queried data
  4) Add the new object to the JPanel p
*/

public class GUI extends JFrame implements ActionListener {
  static JFrame f;

  public static void main(String[] args) {
    // Building the connection
    String database_url = "jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_31";
    String username = "csce315331_team_31_master";
    String password = "TEAM_31";
    Data db = new Data(database_url, username, password);

    if (db.connect()) {
      JOptionPane.showMessageDialog(null, "Opened database successfully");
    } else {
      JOptionPane.showMessageDialog(null, "Error accessing Database.");
      System.exit(0);
    }

    // for (int i = 0; i < 10; i++)
    //   System.out.println(db.getOrder(i+1));

    // create a new frame
    f = new JFrame("Chick-fil-A Order System");

    // create a object
    GUI s = new GUI();

    // Set the layout manager of the content pane to GridBagLayout
    f.getContentPane().setLayout(new GridBagLayout());

    // Create the first row panel
    JPanel rowPanel1 = new JPanel();
    rowPanel1.setPreferredSize(new Dimension(200, 50));
    rowPanel1.setBackground(Color.RED);

    // Create the second row panel
    JPanel rowPanel2 = new JPanel(new GridBagLayout());
    rowPanel2.setPreferredSize(new Dimension(200, 400));

    // Create the third row panel
    JPanel rowPanel3 = new JPanel();
    rowPanel3.setPreferredSize(new Dimension(200, 50));
    rowPanel3.setBackground(Color.BLUE);

    OrderListPanel orderListP = new OrderListPanel();
    ItemListPanel itemListP = new ItemListPanel();
    MainDisplayPanel mainDisplayP = new MainDisplayPanel(db);

    // Add the panels to the content pane using GridBagConstraints
    GridBagConstraints mainGrid = new GridBagConstraints();

    // First row
    mainGrid.gridx = 0;
    mainGrid.gridy = 0;
    mainGrid.gridwidth = 3;
    mainGrid.fill = GridBagConstraints.BOTH;
    mainGrid.anchor = GridBagConstraints.CENTER;
    mainGrid.weightx = 1.0;
    mainGrid.weighty = 0.1;
    f.getContentPane().add(rowPanel1, mainGrid);

    // Second row
    // Add the columns to the second row panel
    rowPanel2.add(orderListP.panel);
    rowPanel2.add(itemListP.panel);
    rowPanel2.add(mainDisplayP.panel);

    mainGrid.gridx = 0;
    mainGrid.gridy = 1;
    mainGrid.fill = GridBagConstraints.VERTICAL;
    mainGrid.anchor = GridBagConstraints.WEST;
    mainGrid.weightx = 0.25;
    mainGrid.weighty = 0.8;
    f.getContentPane().add(orderListP.panel, mainGrid);

    mainGrid.gridx = 1;
    mainGrid.gridy = 1;
    mainGrid.fill = GridBagConstraints.VERTICAL;
    mainGrid.anchor = GridBagConstraints.CENTER;
    mainGrid.weightx = 0.25;
    mainGrid.weighty = 0.8;
    f.getContentPane().add(itemListP.panel, mainGrid);

    mainGrid.gridx = 2;
    mainGrid.gridy = 1;
    mainGrid.fill = GridBagConstraints.VERTICAL;
    mainGrid.anchor = GridBagConstraints.EAST;
    mainGrid.weightx = 0.5;
    mainGrid.weighty = 0.8;
    f.getContentPane().add(mainDisplayP.panel, mainGrid);

    // Third row
    mainGrid.gridx = 0;
    mainGrid.gridy = 2;
    mainGrid.gridwidth = 3;
    mainGrid.fill = GridBagConstraints.BOTH;
    mainGrid.anchor = GridBagConstraints.CENTER;
    mainGrid.weightx = 1.0;
    mainGrid.weighty = 0.1;
    f.getContentPane().add(rowPanel3, mainGrid);

    // set the size of frame to default fullscreen
    // Get the default graphics device and set it to fullscreen mode
    // GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    // GraphicsDevice device = env.getDefaultScreenDevice();
    // device.setFullScreenWindow(f);

    // // Make sure the JFrame is properly sized
    // f.setSize(device.getDisplayMode().getWidth(),
    // device.getDisplayMode().getHeight());

    // // Make sure the JFrame is undecorated
    // f.setUndecorated(true);
    f.setSize(1000, 800);
    // Make the JFrame visible
    f.setVisible(true);

    // closing the connection
    if (db.disconnect()) {
      JOptionPane.showMessageDialog(null, "Connection Closed.");
    } else {
      JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
      System.exit(0);
    }

  }

  // if button is pressed
  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    if (s.equals("Close")) {
      f.dispose();
    }
  }
}