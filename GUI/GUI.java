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

    public static void main(String[] args)
    {
      //Building the connection
      Connection conn = null;
      //TODO STEP 1
      try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315331_team_31",
           "csce315331_team_31_master", "TEAM_31");
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
      }
      JOptionPane.showMessageDialog(null,"Opened database successfully");

      String name = "";
      try{
        //create a statement object
        Statement stmt = conn.createStatement();
        //create an SQL statement
        //TODO Step 2
        String sqlStatement = "SELECT * FROM staff LIMIT 5;";
        //send statement to DBMS
        ResultSet result = stmt.executeQuery(sqlStatement);
        while (result.next()) {
          name += result.getString("name")+"\n";
        }
      } catch (Exception e){
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }

      // create a new frame
      f = new JFrame("DB GUI");
      f.setLayout(new BorderLayout(15, 15));
      // create a object
      GUI s = new GUI();
      // create a panel
      JPanel top = new JPanel();
      JPanel bot = new JPanel();
      JButton b = new JButton("Close");
      // add actionlistener to button
      b.addActionListener(s);
      JTextArea text = new JTextArea(name);
      top.add(text);
      top.add(b);
      f.add(top, BorderLayout.NORTH);
      f.add(bot, BorderLayout.SOUTH);

      ItemListPanel itemListP = new ItemListPanel(); 
      MainDisplayPanel mainDisplayP = new MainDisplayPanel();
      OrderListPanel orderListP = new OrderListPanel(); 
      
      f.add(itemListP.panel, BorderLayout.WEST);
      f.add(mainDisplayP.panel, BorderLayout.CENTER);
      f.add(orderListP.panel, BorderLayout.EAST);

      // set the size of frame
      f.setSize(1000, 800);

      f.setVisible(true);

      //closing the connection
      try {
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection Closed.");
      } catch(Exception e) {
        JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
      }
    }

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        }
    }
}