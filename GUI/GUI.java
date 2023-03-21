import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
  static JFrame f;
  static boolean shouldSwitchToManager;

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

    // create a new frame
    f = new JFrame("Chick-fil-A Order System");
    // set the siz of frame to default fullscreen
    // Get the default graphics device and set it to fullscreen mode
    // GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    // GraphicsDevice device = env.getDefaultScreenDevice();
    // device.setFullScreenWindow(f);

    // // Make sure the JFrame is properly sized
    // f.setSize(device.getDisplayMode().getWidth(),
    // device.getDisplayMode().getHeight());

    // // Make sure the JFrame is undecorated
    // f.setUndecorated(true);
    f.setSize(1250, 750);
    f.setLocationRelativeTo(null);

    // Create the main panel with a FlowLayout and add three panels to it
    JPanel mainPanel1 = new JPanel();
    mainPanel1.setLayout(new BorderLayout());
    Dimension frameSize = f.getSize();
    mainPanel1.setPreferredSize(new Dimension(frameSize.width,
        (int) (frameSize.height * 0.875)));

    // Create Individual Display Panels
    OrderListPanel orderListP = new OrderListPanel(db);
    ManagerMainDisplayPanel managerMainDisplayP = new ManagerMainDisplayPanel(db);
    ManagerItemListPanel managerItemListP = new ManagerItemListPanel(db, managerMainDisplayP);

    // Add the sub-panels to the main panel
    mainPanel1.add(orderListP.panel, BorderLayout.WEST);
    mainPanel1.add(managerItemListP.panel, BorderLayout.CENTER);
    mainPanel1.add(managerMainDisplayP.panel, BorderLayout.EAST);

    // Create a new empty border with 10 pixels of padding on the left and right
    // edges
    managerItemListP.panel.setPreferredSize(new Dimension((int) (frameSize.width * 0.20),
        (int) (frameSize.height * 0.875)));
    managerMainDisplayP.panel.setPreferredSize(new Dimension((int) (frameSize.width * 0.70),
        (int) (frameSize.height * 0.875)));

    // pad panels
    EmptyBorder padding = new EmptyBorder(0, 10, 0, 10);
    orderListP.panel.setBorder(padding);
    managerItemListP.panel.setBorder(padding);
    managerMainDisplayP.panel.setBorder(padding);

    // Create the main panel with a FlowLayout and add three panels to it
    JPanel mainPanel2 = new JPanel();
    mainPanel2.setLayout(new BorderLayout());
    mainPanel2.setPreferredSize(new Dimension(frameSize.width,
        (int) (frameSize.height * 0.875)));

    MainDisplayPanel mainDisplayP = new MainDisplayPanel(db, orderListP);
    ItemListPanel itemListP = new ItemListPanel(db, mainDisplayP);

    // Add the sub-panels to the main panel
    mainPanel2.add(orderListP.panel, BorderLayout.WEST);
    mainPanel2.add(itemListP.panel, BorderLayout.CENTER);
    mainPanel2.add(mainDisplayP.panel, BorderLayout.EAST);

    // Create a new empty border with 10 pixels of padding on the left and right
    // edges
    orderListP.panel.setPreferredSize(new Dimension((int) (frameSize.width * 0.30),
        (int) (frameSize.height * 0.875)));
    itemListP.panel.setPreferredSize(new Dimension((int) (frameSize.width * 0.20),
        (int) (frameSize.height * 0.875)));
    mainDisplayP.panel.setPreferredSize(new Dimension((int) (frameSize.width * 0.50),
        (int) (frameSize.height * 0.875)));

    // pad panels
    itemListP.panel.setBorder(padding);
    managerMainDisplayP.panel.setBorder(padding);

    shouldSwitchToManager = true;
    // assigning visibility
    mainPanel1.setVisible(!shouldSwitchToManager);
    mainPanel2.setVisible(shouldSwitchToManager);

    // Create the footer panel with a label and add it to the bottom of the frame
    JPanel footerPanel = new JPanel();
    BasicControlPanel basicControlP = new BasicControlPanel(db);

    JButton switchServerToManagerButton = new JButton();
    if (shouldSwitchToManager) { // we are in the manager ready to go to server
      switchServerToManagerButton.setText("Manager View");
      footerPanel.add(basicControlP.panel);
      footerPanel.add(switchServerToManagerButton);
      // Add the main panel 1 to the top of the frame
      f.add(mainPanel2, BorderLayout.NORTH);
    } else {
      switchServerToManagerButton.setText("Server View");
      footerPanel.add(basicControlP.panel);
      // Add Manager controls to footer
      ManagerControlPanel managerControlP = new ManagerControlPanel(db);
      footerPanel.add(managerControlP.panel);
      footerPanel.add(switchServerToManagerButton);
      // Add the main panel 2 to the top of the frame
      f.add(mainPanel1, BorderLayout.NORTH);
    }

    switchServerToManagerButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // show manager view
        mainPanel1.setVisible(shouldSwitchToManager);
        // show server view
        mainPanel2.setVisible(!shouldSwitchToManager);
        if (shouldSwitchToManager) { // currently in the server
          System.out.println("Switching to manager");
          switchServerToManagerButton.setText("Server View");

          footerPanel.removeAll();

          footerPanel.add(basicControlP.panel);
          // Add Manager controls to footer
          ManagerControlPanel managerControlP = new ManagerControlPanel(db);
          footerPanel.add(managerControlP.panel);
          footerPanel.add(switchServerToManagerButton);

          footerPanel.validate();
          footerPanel.repaint();

          // Add the main panel 2 to the top of the frame
          f.add(mainPanel1, BorderLayout.NORTH);
        } else { // currently in the manager
          System.out.println("Switching to server");
          switchServerToManagerButton.setText("Manager View");

          footerPanel.removeAll();

          footerPanel.add(basicControlP.panel);
          footerPanel.add(switchServerToManagerButton);

          footerPanel.validate();
          footerPanel.repaint();

          // Add the main panel 1 to the top of the frame
          f.add(mainPanel2, BorderLayout.NORTH);
        }
        // flip boolean to match new state
        shouldSwitchToManager = !shouldSwitchToManager;
      }
    });

    // Add the footer panel to the bottom of the frame
    f.add(footerPanel, BorderLayout.SOUTH);
    // Make the JFrame visible
    f.setVisible(true);
    // closing the connection
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // add a WindowListener to the JFrame
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (db.disconnect()) {
          JOptionPane.showMessageDialog(null, "Connection Closed.");
        } else {
          JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
          System.exit(0);
        }
      }
    });
  }

  // if button is pressed
  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    if (s.equals("Close")) {
      f.dispose();
    }
  }
}