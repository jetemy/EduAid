/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMngtUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import Functions.DBConUserMngt;
import Functions.InputVerification;
import static UserMngtUI.UserLogIn.PasswordField;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SignUp extends JDialog {

   
    public static JPanel UserSignUpPanel;
    private static DBConUserMngt Usermngt;
    public static JComboBox RegNocmb;
    private JCheckBox viewChkBox;
    public JPasswordField PasswordConfField;
    public InputVerification Inv;

    public SignUp() throws SQLException, IOException {
        Usermngt = new DBConUserMngt();
        Inv = new InputVerification();

        //Create JDialog UserSignUpDia
      
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setSize(470, 370);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle(" SignUp");
        setVisible(true);

        try {
           setIconImage(ImageIO.read(new File("C:\\Users\\EliteBook 8460p\\Documents\\NetBeansProjects\\Academic "
                    + "Informer\\src\\Resources\\Images\\Register-icon.png")));
        } catch (IOException ex) {
            Logger.getLogger(UserRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        //create Labels 
        Usermngt.UserNamelbl = new JLabel("Name");
        Usermngt.UserNamelbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.UserNamelbl.setForeground(Color.WHITE);
        Usermngt.UserNamelbl.setBounds(05, 40, 120, 30);

        Usermngt.RegEmplbl = new JLabel("Reg/Emp No");
        Usermngt.RegEmplbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.RegEmplbl.setForeground(Color.WHITE);
        Usermngt.RegEmplbl.setBounds(05, 75, 120, 30);

        Usermngt.Passwordlbl = new JLabel("Password");
        Usermngt.Passwordlbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.Passwordlbl.setForeground(Color.WHITE);
        Usermngt.Passwordlbl.setBounds(05, 110, 120, 30);

        Usermngt.Passwordconflbl = new JLabel("Password Confirm");
        Usermngt.Passwordconflbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.Passwordconflbl.setForeground(Color.WHITE);
        Usermngt.Passwordconflbl.setBounds(05, 145, 120, 30);

        Usermngt.Titlelbl = new JLabel();
        Usermngt.Titlelbl.setText("User SignUp");
        Usermngt.Titlelbl.setFont(new Font("ARIAL ", Font.PLAIN, 25));
        Usermngt.Titlelbl.setForeground(Color.YELLOW);
        Usermngt.Titlelbl.setBounds(190, 15, 300, 30);

        //create text fields
        Usermngt.UserNametxt = new JTextField();
        Usermngt.UserNametxt.setBounds(130, 105, 220, 30);

        PasswordField = new JPasswordField(12);
        PasswordField.setEchoChar('*');
        PasswordField.setBounds(130, 155, 220, 30);

        PasswordConfField = new JPasswordField(12);
        PasswordConfField.setEchoChar('*');
        PasswordConfField.setBounds(130, 205, 220, 30);
        //Panel to hold check box
        JPanel chkBoxPanel = new JPanel();
        chkBoxPanel.setBackground(Color.decode("#4B0082"));
        chkBoxPanel.setBounds(380, 155, 50, 30);

        //Create CheckBox
        viewChkBox = new JCheckBox("View ");
        viewChkBox.setBackground(Color.decode("#4B0082"));
        viewChkBox.setForeground(Color.yellow);

        viewChkBox.addActionListener((ActionEvent ae) -> {
            if (viewChkBox.isSelected()) {
                PasswordField.setEchoChar((char) 0);
                PasswordConfField.setEchoChar((char) 0);
            } else {
                PasswordField.setEchoChar('*');
                PasswordConfField.setEchoChar('*');

            }

        });

        chkBoxPanel.add(viewChkBox);
        //Create JComboBox
        RegNocmb = new JComboBox();
        RegNocmb.setBounds(130, 60, 220, 30);
        SwingUtilities.invokeLater(() -> {
            try {
                AutoCompleteSupport.install(RegNocmb, GlazedLists.eventListOf(Usermngt.PopRegEmpNocmb()));
            } catch (SQLException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         });
        Usermngt.PopRegEmpNocmb();
       

        RegNocmb.setEditable(true);

        //Create Buttons
        Usermngt.Savebtn = new JButton("Save");
        Usermngt.Savebtn.setForeground(Color.WHITE);
        Usermngt.Savebtn.setBackground(Color.decode("#8A2BE2"));
        Usermngt.Savebtn.setBounds(130, 270, 70, 30);
        Usermngt.Savebtn.addActionListener((ActionEvent e) -> {

            boolean status = Inv.passwordConfirmation(String.valueOf(PasswordField.getPassword()),
                    String.valueOf(PasswordConfField.getPassword()));
            if (status == false) {
                PasswordConfField.setBorder(BorderFactory.createLineBorder(Color.RED));
                PasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));

            } else {
                Usermngt.UserSignUp(RegNocmb.getSelectedItem().toString(),
                        Usermngt.UserNametxt.getText(), String.valueOf(PasswordField.getPassword()),
                Date.valueOf(LocalDate.now().toString()),Time.valueOf(LocalTime.now().toString()));
                PasswordConfField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                PasswordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            }

        });

        Usermngt.Clrbtn = new JButton("Clear");
        Usermngt.Clrbtn.setForeground(Color.WHITE);
        Usermngt.Clrbtn.setBackground(Color.decode("#8A2BE2"));
        Usermngt.Clrbtn.setBounds(230, 270, 70, 30);
        Usermngt.Clrbtn.addActionListener((ActionEvent e) -> {
            if (JOptionPane.showConfirmDialog(null, "Clear Entries?", "Clear", 2) == JOptionPane.OK_OPTION) {
                Usermngt.UserNametxt.setText("");
                RegNocmb.setSelectedItem("Select Item");
                PasswordConfField.setEchoChar((char) 0);
                PasswordField.setEchoChar((char) 0);
                
            } else {
                System.gc();
            }

        });

        Usermngt.Exitbtn = new JButton("Exit");
        Usermngt.Exitbtn.setForeground(Color.WHITE);
        Usermngt.Exitbtn.setBackground(Color.decode("#8A2BE2"));
        Usermngt.Exitbtn.setBounds(305, 270, 70, 30);
        Usermngt.Exitbtn.addActionListener((ActionEvent e) -> {

            this.dispose();

        });

        //Create Panel 
        UserSignUpPanel = new JPanel();
        UserSignUpPanel.setBounds(0, 0, 470, 370);
        UserSignUpPanel.setBackground(Color.decode("#4B0082"));
        UserSignUpPanel.setLayout(null);
       

        //Add components to JPanel
        UserSignUpPanel.add(Usermngt.UserNamelbl);
        UserSignUpPanel.add(RegNocmb);
        UserSignUpPanel.add(Usermngt.RegEmplbl);
        UserSignUpPanel.add(Usermngt.Passwordconflbl);
        UserSignUpPanel.add(Usermngt.Passwordlbl);
        UserSignUpPanel.add(PasswordField);
        UserSignUpPanel.add(PasswordConfField);
        UserSignUpPanel.add(Usermngt.Titlelbl);
        UserSignUpPanel.add(Usermngt.UserNametxt);
        UserSignUpPanel.add(Usermngt.Savebtn);
        UserSignUpPanel.add(Usermngt.Clrbtn);
        UserSignUpPanel.add(Usermngt.Exitbtn);
        UserSignUpPanel.add(chkBoxPanel);

        add(UserSignUpPanel);

    }

    public static void main(String[] args) throws SQLException, IOException {
        SignUp ur = new SignUp();
        

    }

}
