/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMngtUI;

/**
 *
 * @author hp
 */
import EduaidUI.Init;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import Functions.DBConUserMngt;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDialog;

public class UserLogIn extends JDialog {

    public static JCheckBox ViewPWDChkBx;
    public static JLabel Namelbl, Passwordlbl, Selectionlbl, lblimg, X, lblmsg;
    public static JPasswordField PasswordField;
    public static JPanel login, MsgPanel;
    public static JButton BTNNEXT, CANCELButton, logInButton;
    public static Timer timer1, timer;
    private JProgressBar progbar;
    public DBConUserMngt func;

    Icon IMG;

    public UserLogIn() throws SQLException, IOException {
        func = new DBConUserMngt();
        //init = new Init();

        IMG = new ImageIcon("C:\\Users\\EliteBook 8460p\\Documents\\NetBeansProjects\\Academic "
                + "Informer\\src\\Resources\\Images\\logIn Img.JPG");
        try {
            setIconImage(ImageIO.read(new File("C:\\Users\\EliteBook 8460p\\Documents\\NetBeansProjects\\Academic "
                    + "Informer\\src\\Resources\\Images\\Register-icon.png")));
        } catch (IOException ex) {
            Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
        }

        MsgPanel = new JPanel();
        MsgPanel.setLayout(null);

        lblmsg = new JLabel();
       

        timer = new Timer(100, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MsgPanel.getHeight() != 50) {
                    MsgPanel.setBounds(0, 0, UserLogIn.this.getSize().width,
                            MsgPanel.getHeight() + 5);
                } else if (MsgPanel.getHeight() == 50) {
                    timer.stop();
                }

            }

        }));
        timer1 = new Timer(100, (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MsgPanel.getHeight() != 0) {
                    MsgPanel.setBounds(0, 0, UserLogIn.this.getSize().width,
                            MsgPanel.getHeight() - 5);
                    if (MsgPanel.getHeight() == 0) {
                        timer1.stop();
                    }

                }

            }

        }));

        //create label objects
        lblimg = new JLabel();
        lblimg.setIcon(IMG);
        lblimg.setBounds(10, 70, 150, 150);
        lblimg.setBorder(BorderFactory.createLineBorder(Color.cyan));

        Selectionlbl = new JLabel("  LOG IN");
        Selectionlbl.setFont(new Font("Arial", Font.PLAIN, 25));
        Selectionlbl.setForeground(Color.YELLOW);
        Selectionlbl.setBounds(300, 60, 180, 30);

        Namelbl = new JLabel("ENTER NAME");
        Namelbl.setForeground(Color.WHITE);
        Namelbl.setFont(new Font("Arial", Font.PLAIN, 12));
        Namelbl.setBounds(180, 100, 180, 30);

        Passwordlbl = new JLabel("ENTER PASSWORD");
        Passwordlbl.setForeground(Color.WHITE);
        Passwordlbl.setFont(new Font("Arial", Font.PLAIN, 12));
        Passwordlbl.setBounds(180, 150, 180, 30);

        //create password fields
        PasswordField = new JPasswordField();
        PasswordField.setEchoChar('*');
        PasswordField.setFont(new Font("", Font.PLAIN, 18));
        PasswordField.setBounds(300, 150, 150, 30);
        ViewPWDChkBx = new JCheckBox("VIEW PASSWORD");
        ViewPWDChkBx.setBounds(300, 195, 150, 10);
        ViewPWDChkBx.setFont(new Font("Arial", Font.PLAIN, 14));
        ViewPWDChkBx.setForeground(Color.YELLOW);
        ViewPWDChkBx.setBackground(Color.decode("#191970"));
        ViewPWDChkBx.addItemListener((ItemEvent e) -> {
            if (ViewPWDChkBx.isSelected()) {
                PasswordField.setEchoChar((char) 0);

            } else {
                PasswordField.setEchoChar('*');
            }
        });

        //create text field objects
        func.Nametxt = new JTextField();
        func.Nametxt.setBounds(300, 100, 150, 30);
        func.Nametxt.setFont(new Font("", Font.PLAIN, 14));
        func.Nametxt.addActionListener((ActionEvent e) -> {

        });

        // create button objects
        logInButton = new JButton("LOG IN");
        logInButton.setBounds(270, 220, 75, 30);
        logInButton.setFont(new Font("Arial", Font.PLAIN, 12));
        logInButton.addActionListener((ActionEvent e) -> {

            try {
                func.pswdchk();
            } catch (SQLException | IOException ex) {
                Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        BTNNEXT = new JButton(" NEXT ");
        BTNNEXT.setFont(new Font("Arial", Font.BOLD, 10));
        BTNNEXT.setBounds(350, 10, 80, 30);
        BTNNEXT.addActionListener((ActionEvent e) -> {
            timer1.start();
            SwingUtilities.invokeLater(() -> {

                try {
                    
                   new UserLogIn().dispose();
                    Init sc = new Init();

                } catch (SQLException ex) {
                    Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DBConUserMngt.class.getName()).log(Level.SEVERE, null, ex);
                } 

            });
           
            
        });
        //==========================================================================       
        CANCELButton = new JButton("CANCEL");
        CANCELButton.setBounds(380, 220, 80, 30);
        CANCELButton.addActionListener((ActionEvent e) -> {
            if (JOptionPane.showConfirmDialog(null, "Do you want to exit",
                    "EXIT", JOptionPane.OK_CANCEL_OPTION)
                    == JOptionPane.OK_OPTION) {
                System.exit(0);
            } else {
                System.gc();
            }
        });

        MsgPanel.add(lblmsg);
        MsgPanel.add(BTNNEXT);

        login = new JPanel();
        login.setBounds(0, 0, 500, 300);
        login.setBorder(BorderFactory.createBevelBorder(5));
        login.setBackground(Color.decode("#191970"));
        login.setLayout(null);

        login.add(Selectionlbl);
        login.add(Namelbl);
        login.add(Passwordlbl);
        login.add(func.Nametxt);
        login.add(PasswordField);
        login.add(logInButton);
        login.add(CANCELButton);
        login.add(lblimg);
        login.add(MsgPanel);
        login.add(ViewPWDChkBx);

        add(login);

        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);
        

        func.DBConnect();

    }//END OF CONSTRUCTOR LOGIN

    public static void main(String[] args) throws SQLException {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(() -> {
            try {
                try {
                    new UserLogIn().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(X.getWidth(), X.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

}// END OF CLASS LOGIN
