/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMngtUI;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jerry
 */
public class UserFileChooser extends JFrame {

    /**
     * @param args the command line arguments
     */
    JPanel panel=new JPanel();
     
   
    public UserFileChooser(){
        //Create label
        JLabel fclabel=new JLabel("Choose File");
        fclabel.setForeground(Color.red);
        fclabel.setBounds(20, 80,100,10);
        
        //Create File Chooser
         fileChooser();
        
        //Create JPanel properties and add componets
        panel.setBounds(0,0,700,400);
        panel.setLayout(null);
        panel.add(fclabel);
        
        //add JPanel to JFrame
        add(panel);
        
        //Set JFrame properties
        setSize(550,500);
        setLocation(200,200);
        setLayout(null);
        setBackground(Color.lightGray);
        setResizable(false);
    }     
     
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
      
        UserFileChooser ai=new UserFileChooser();
        ai.setVisible(true);
    }
     private void fileChooser() {
        // FileChooser

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "PDF Files","pdf","jpeg","png","Doc Files","docx","CSV Files", "csv");
        chooser.setFileFilter(filter);
        chooser.setBounds(20,105, 500, 300);
        panel.add(chooser);
       
    }
    
   
}