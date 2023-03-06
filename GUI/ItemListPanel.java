import java.awt.*;  
import javax.swing.*;  
public class ItemListPanel {  
    JPanel panel; 
    ItemListPanel()  {  
        this.panel = new JPanel();  
        this.panel.setBounds(40,80,200,200);    
        this.panel.setBackground(Color.gray);  
        JButton b1=new JButton("Button 1");     
        b1.setBounds(50,100,80,30);    
        b1.setBackground(Color.yellow);   
        JButton b2=new JButton("Button 2");   
        b2.setBounds(100,100,80,30);    
        b2.setBackground(Color.green); 
        JButton b3=new JButton("Button 3");   
        b3.setBounds(100,100,80,30);    
        b3.setBackground(Color.green); 
        JButton b4=new JButton("Button 4");   
        b4.setBounds(100,100,80,30);    
        b4.setBackground(Color.green);   
        this.panel.add(b1); 
        this.panel.add(b2);  
        this.panel.add(b3); 
        this.panel.add(b4); 
    }
}