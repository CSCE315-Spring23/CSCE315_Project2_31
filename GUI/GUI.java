import java.sql.*;
import java.util.Vector;
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
    Vector<Menu> a = db.getMenuByType("Sandwiches");
    for (int i = 0; i < a.size(); i++) {
      System.out.println(a.get(i).name);
    }

    // create a new frame
    f = new JFrame("Chick-fil-A Order System");

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
    
    // create a object
    GUI s = new GUI();
    // button to close program
    JButton b = new JButton("Close");
    // add actionlistener to button
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (db.disconnect()) {
          JOptionPane.showMessageDialog(null, "Connection Closed.");
        } else {
          JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
          System.exit(0);
        }
      }
    });

    // Create the main panel with a FlowLayout and add three panels to it
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    Dimension frameSize = f.getSize();
    mainPanel.setPreferredSize(new Dimension(frameSize.width, 
                                       (int)(frameSize.height * 0.85)));
    
    // Create Individual Display Panels
    OrderListPanel orderListP = new OrderListPanel(db);
    MainDisplayPanel mainDisplayP = new MainDisplayPanel(db);
    ItemListPanel itemListP = new ItemListPanel(db, mainDisplayP);
    

    // Add the sub-panels to the main panel
    mainPanel.add(orderListP.panel, BorderLayout.WEST);
    mainPanel.add(itemListP.panel, BorderLayout.CENTER);
    mainPanel.add(mainDisplayP.panel, BorderLayout.EAST);

    // Set the size of sub-panels to have a 20-20-60 split
    orderListP.panel.setPreferredSize(new Dimension((int)(frameSize.width * 0.2), 
                                                    (int)(frameSize.height * 0.85)));
    itemListP.panel.setPreferredSize(new Dimension((int)(frameSize.width * 0.2), 
                                                   (int)(frameSize.height * 0.85)));
    mainDisplayP.panel.setPreferredSize(new Dimension((int)(frameSize.width * 0.6), 
                                                      (int)(frameSize.height * 0.85)));
    
    // Add the main panel to the top of the frame
    f.add(mainPanel, BorderLayout.NORTH);
    
    // Create the footer panel with a label and add it to the bottom of the frame
    JPanel footerPanel = new JPanel();
    footerPanel.add(b);
    f.add(footerPanel, BorderLayout.SOUTH);

    // Make the JFrame visible
    f.setVisible(true);

    // closing the connection
    

  }

  // if button is pressed
  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    if (s.equals("Close")) {
      f.dispose();
    }
  }
}