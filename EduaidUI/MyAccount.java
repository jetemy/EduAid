/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EduaidUI;

import Functions.DBConUserMngt;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Elite Book 8460p
 */
public class MyAccount extends JFrame{

    public JPanel panel;
    private JProgressBar UploadPB;
    private int progressCounter,counter;
    private JLabel lblloadcounter, lblloadcounter1, lbluploadstatus, X, Y, lbluploadedfile;
    private File selectedFile;
    private String Fileselected;

    public MyAccount() throws SQLException {
        Init init=new Init();
         init.InitFrm.setVisible(false);
        //Set panel properties
        panel = new JPanel();
        panel.setBounds(0, 0, 750, 530);
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBackground(Color.decode("#4B0082"));
        panel.setVisible(true);
        panel.setLayout(null);

        //create DBConnect Object
        DBConUserMngt DB = new DBConUserMngt();
        //Create progressbar
        UploadPB = new JProgressBar();
        UploadPB.setBounds(30, 400, 250, 10);
        UploadPB.setBackground(Color.GREEN);
        UploadPB.setMaximum(99);
        UploadPB.setMinimum(0);
        UploadPB.setIndeterminate(false);
        UploadPB.setVisible(false);

        //Create JSeparator
        JSeparator sep3 = new JSeparator(SwingConstants.VERTICAL);
        sep3.setBackground(Color.decode("#8A2BE2"));
        sep3.setLocation(320, 60);
        sep3.setSize(2, 320);

        //Create Labels
        lbluploadedfile = new JLabel();
        lbluploadedfile.setVisible(false);

        lblloadcounter = new JLabel();
        lblloadcounter.setBounds(285, 385, 40, 40);
        lblloadcounter.setForeground(Color.yellow);
        lblloadcounter.setFont(new Font("Arial", Font.PLAIN, 12));

        lblloadcounter1 = new JLabel();
        lblloadcounter1.setBounds(285, 395, 15, 15);
        lblloadcounter1.setForeground(Color.yellow);
        lblloadcounter1.setFont(new Font("Arial", Font.PLAIN, 12));

        lbluploadstatus = new JLabel("Uploading Document.......");
        lbluploadstatus.setFont(new Font("Arial", Font.PLAIN, 12));
        lbluploadstatus.setBounds(50, 380, 220, 15);
        lbluploadstatus.setForeground(Color.WHITE);
        lbluploadstatus.setVisible(false);

        DB.MyAccountlbl = new JLabel("Personal Details");
        DB.MyAccountlbl.setBounds(480, 10, 250, 30);
        DB.MyAccountlbl.setFont(new Font("ARIAL", Font.PLAIN, 25));
        DB.MyAccountlbl.setBackground(Color.decode("#DA70D6"));
        DB.MyAccountlbl.setForeground(Color.YELLOW);

        DB.MyPhotolbl = new JLabel("My Photo Image");
        DB.MyPhotolbl.setBounds(70, 10, 250, 30);
        DB.MyPhotolbl.setFont(new Font("ARIAL", Font.PLAIN, 25));
        DB.MyPhotolbl.setBackground(Color.decode("#DA70D6"));
        DB.MyPhotolbl.setForeground(Color.YELLOW);

        DB.Imagelbl = new JLabel();
        DB.Imagelbl.setBounds(30, 60, 250, 250);
        DB.Imagelbl.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        DB.Imagelbl.setBackground(Color.decode("#DA70D6"));

        DB.FNamelbl = new JLabel("First Name");
        DB.FNamelbl.setBounds(350, 50, 100, 30);
        DB.FNamelbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.FNamelbl.setBackground(Color.decode("#DA70D6"));
        DB.FNamelbl.setForeground(Color.WHITE);
        
        DB.MNamelbl= new JLabel("Middle Name");
        DB.MNamelbl.setBounds(350, 95, 120, 30);
        DB.MNamelbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.MNamelbl.setBackground(Color.decode("#DA70D6"));
        DB.MNamelbl.setForeground(Color.WHITE);
        
        DB.LNamelbl= new JLabel("Last Name");
        DB.LNamelbl.setBounds(350, 140, 100, 30);
        DB.LNamelbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.LNamelbl.setBackground(Color.decode("#DA70D6"));
        DB.LNamelbl.setForeground(Color.WHITE);

        DB.RegEmplbl = new JLabel("Reg/EmpNo");
        DB.RegEmplbl.setBounds(350, 185, 100, 30);
        DB.RegEmplbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.RegEmplbl.setBackground(Color.decode("#DA70D6"));
        DB.RegEmplbl.setForeground(Color.WHITE);

        DB.Emaillbl = new JLabel("Email");
        DB.Emaillbl.setBounds(350, 230, 100, 30);
        DB.Emaillbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.Emaillbl.setBackground(Color.decode("#DA70D6"));
        DB.Emaillbl.setForeground(Color.WHITE);

        DB.Mobilelbl = new JLabel("Mobile");
        DB.Mobilelbl.setBounds(350, 275, 100, 30);
        DB.Mobilelbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.Mobilelbl.setBackground(Color.decode("#DA70D6"));
        DB.Mobilelbl.setForeground(Color.WHITE);

        DB.Deptlbl = new JLabel("Department");
        DB.Deptlbl.setBounds(350, 320, 100, 30);
        DB.Deptlbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.Deptlbl.setBackground(Color.decode("#DA70D6"));
        DB.Deptlbl.setForeground(Color.WHITE);

        DB.Desiglbl = new JLabel("Designation");
        DB.Desiglbl.setBounds(350, 365, 100, 30);
        DB.Desiglbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.Desiglbl.setBackground(Color.decode("#DA70D6"));
        DB.Desiglbl.setForeground(Color.WHITE);

        DB.UserLevellbl = new JLabel("User Level");
        DB.UserLevellbl.setBounds(350, 410, 100, 30);
        DB.UserLevellbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        DB.UserLevellbl.setBackground(Color.decode("#DA70D6"));
        DB.UserLevellbl.setForeground(Color.WHITE);

        //Create TextFields
        DB.Fnametxt = new JTextField();
        DB.Fnametxt.setBounds(465, 50, 230, 30);
        DB.Fnametxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        DB.Fnametxt.setForeground(Color.BLACK);
        DB.Fnametxt.setEditable(false);
        
        DB.Mnametxt= new JTextField();
        DB.Mnametxt.setBounds(465, 95, 230, 30);
        DB.Mnametxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        DB.Mnametxt.setForeground(Color.BLACK);
        DB.Mnametxt.setEditable(false);
        
        DB.Lnametxt= new JTextField();
        DB.Lnametxt.setBounds(465, 140, 230, 30);
        DB.Lnametxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        DB.Lnametxt.setForeground(Color.BLACK);
        DB.Lnametxt.setEditable(false);
        DB.RegEmptxt = new JTextField();
        DB.RegEmptxt.setBounds(465, 185, 230, 30);
        DB.RegEmptxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        //DB.RegEmptxt.setEditable(false);
        DB.RegEmptxt.setForeground(Color.BLACK);

        DB.Emailtxt = new JTextField();
        DB.Emailtxt.setBounds(465, 230, 230, 30);
        DB.Emailtxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        DB.Emailtxt.setEditable(false);
        DB.Emailtxt.setForeground(Color.BLACK);

        DB.Mobiletxt = new JTextField();
        DB.Mobiletxt.setBounds(465, 275, 230, 30);
        DB.Mobiletxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        //DB.Mobiletxt.setEditable(false);
        DB.Mobiletxt.setForeground(Color.BLACK);

        DB.Depttxt = new JTextField();
        DB.Depttxt.setBounds(465, 320, 230, 30);
        DB.Depttxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        DB.Depttxt.setEditable(false);
        DB.Depttxt.setForeground(Color.BLACK);

        DB.Desigtxt = new JTextField();
        DB.Desigtxt.setBounds(465, 365, 230, 30);
        DB.Desigtxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        DB.Desigtxt.setEditable(false);
        DB.Desigtxt.setForeground(Color.BLACK);

        DB.AccessLeveltxt = new JTextField();
        DB.AccessLeveltxt.setBounds(465, 410, 230, 30);
        DB.AccessLeveltxt.setFont(new Font("ARIAL", Font.PLAIN, 14));
        DB.AccessLeveltxt.setEditable(false);
        DB.AccessLeveltxt.setForeground(Color.BLACK);

        //Create Buttons
        DB.RemovePhotobtn = new JButton("Capture Photo");
        DB.RemovePhotobtn.setBounds(160, 320, 120, 30);
        DB.RemovePhotobtn.setForeground(Color.WHITE);
        DB.RemovePhotobtn.setBackground(Color.decode("#8A2BE2"));

        DB.UpLoadbtn = new JButton("Change Photo");
        DB.UpLoadbtn.setBounds(30, 320, 120, 30);
        DB.UpLoadbtn.setForeground(Color.WHITE);
        DB.UpLoadbtn.setBackground(Color.decode("#8A2BE2"));
        DB.UpLoadbtn.addActionListener((ActionEvent e) -> {

            X = DB.Imagelbl;

            counter++;

            if (X.getIcon() != null && counter > 1) {

                if (JOptionPane.showConfirmDialog(null, "Change Photo iMAGE?", "CHANGE PHOTO",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    UploadPB.setVisible(false);
                    lbluploadstatus.setText(null);
                    lblloadcounter.setIcon(null);
                    X.setIcon(null);
                    progressCounter = 0;
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg", "JPG");
                    fileChooser.setFileFilter(filter);
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                    int result = fileChooser.showOpenDialog(panel);

                    if (result == JFileChooser.APPROVE_OPTION) {

                        selectedFile = fileChooser.getSelectedFile();

                        Fileselected = selectedFile.getAbsolutePath();
                        Progressbar();
                    }
                } else {

                    System.gc();
                }

            } else if (X.getIcon() == null && lbluploadstatus.getText().contains(" Upload Not Successful!")) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg", "JPG");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                int result = fileChooser.showOpenDialog(panel);

                if (result == JFileChooser.APPROVE_OPTION) {

                    selectedFile = fileChooser.getSelectedFile();

                    Fileselected = selectedFile.getAbsolutePath();
                    Progressbar();

                }
            } else {

                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg", "JPG");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                int result = fileChooser.showOpenDialog(panel);

                if (result == JFileChooser.APPROVE_OPTION) {

                    selectedFile = fileChooser.getSelectedFile();

                    Fileselected = selectedFile.getAbsolutePath();
                    Progressbar();
                }
            }

        });

        DB.Savebtn = new JButton("Save");
        DB.Savebtn.setBounds(220, 480, 80, 30);
        DB.Savebtn.setForeground(Color.WHITE);
        DB.Savebtn.setBackground(Color.decode("#8A2BE2"));
        DB.Savebtn.addActionListener((ActionEvent e) -> {
            String s=Fileselected;
            DB.UserDetailsUpdate(DB.Fnametxt.getText(), DB.Mnametxt.getText(), DB.Lnametxt.getText(),s,init.RegEmpDisplbl.getText()); 
            DB.UserContactsUpdate(DB.Emailtxt.getText(),Integer.parseInt(DB.Mobiletxt.getText()),init.RegEmpDisplbl.getText());

        });

        DB.EditButton = new JButton("Edit");
        DB.EditButton.setBounds(360, 480, 80, 30);
        DB.EditButton.setForeground(Color.WHITE);
        DB.EditButton.setBackground(Color.decode("#8A2BE2"));
        DB.EditButton.addActionListener((ActionEvent e) -> {
            DB.Fnametxt.setEditable(true);
            DB.Fnametxt.setText("  e.g JOHN");
            DB.Fnametxt.setForeground(Color.LIGHT_GRAY);
            DB.Fnametxt.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            
            DB.Mnametxt.setEditable(true);
            DB.Mnametxt.setText("  e.g JOHN");
            DB.Mnametxt.setForeground(Color.LIGHT_GRAY);
            DB.Mnametxt.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            
            DB.Lnametxt.setEditable(true);
            DB.Lnametxt.setText("  e.g JOHN");
            DB.Lnametxt.setForeground(Color.LIGHT_GRAY);
            DB.Lnametxt.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            
            DB.Emailtxt.setEditable(true);
            DB.Emailtxt.setText("  e.g JOHN@EMAIL.COM");
            DB.Emailtxt.setForeground(Color.LIGHT_GRAY);
            
            DB.Emailtxt.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            DB.Mobiletxt.setEditable(true);
            DB.Mobiletxt.setText("  e.g 07XX XXX XXX");
            DB.Mobiletxt.setForeground(Color.LIGHT_GRAY);
            
            DB.Mobiletxt.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        });

        DB.Exitbtn = new JButton("Exit");
        DB.Exitbtn.setBounds(490, 480, 80, 30);
        DB.Exitbtn.setForeground(Color.WHITE);
        DB.Exitbtn.setBackground(Color.decode("#8A2BE2"));
        DB.Exitbtn.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
               
                    //Init Init = new Init();
                    init.InitFrm.setVisible(true);
               
            });

        });
//Fnametxt, Mnametxt, Lnametxt
        panel.add(DB.MyAccountlbl);
        panel.add(DB.MyPhotolbl);
        panel.add(DB.Imagelbl);
        panel.add(DB.FNamelbl);
        panel.add(DB.MNamelbl);
        panel.add(DB.LNamelbl);
        panel.add(sep3);
        panel.add(DB.RegEmplbl);
        panel.add(DB.Emaillbl);
        panel.add(DB.Mobilelbl);
        panel.add(DB.Deptlbl);
        panel.add(DB.Desiglbl);
        panel.add(DB.UserLevellbl);
        panel.add(DB.Fnametxt);
        panel.add(DB.Mnametxt);
        panel.add(DB.Lnametxt);
        panel.add(DB.RegEmptxt);
        panel.add(DB.Emailtxt);
        panel.add(DB.Mobiletxt);
        panel.add(DB.Depttxt);
        panel.add(DB.Desigtxt);
        panel.add(DB.AccessLeveltxt);
        panel.add(DB.UpLoadbtn);
        panel.add(DB.RemovePhotobtn);
        panel.add(DB.Savebtn);
        panel.add(DB.EditButton);
        panel.add(DB.Exitbtn);
        panel.add(lblloadcounter1);
        panel.add(lblloadcounter);
        panel.add(lbluploadstatus);
        panel.add(UploadPB);
        
        add(panel);
        setSize(1000,700);
        setLocation(10, 10);
        setResizable(false);
        setVisible(true);

        
        

    }
public static void main (String[] args) throws SQLException{
    MyAccount acc=new MyAccount();
    acc.setVisible(true);
}
    private ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(X.getWidth(), X.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private ImageIcon ResizeImage1(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(Y.getWidth(), Y.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private void Progressbar() {
        Thread scan = new Thread(
                new Runnable() {
            @Override
            public void run() {
                while (progressCounter <= 100) {

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            UploadPB.setValue(progressCounter++);
                            UploadPB.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
                            UploadPB.setForeground(Color.CYAN);
                            UploadPB.setVisible(true);
                            lbluploadstatus.setVisible(true);
                            lbluploadstatus.setForeground(Color.YELLOW);
                            lbluploadstatus.setText("Uploading file..... "
                                    + selectedFile.getName());
                            lblloadcounter.setFont(new Font("Arial", Font.BOLD, 18));
                            lblloadcounter.setForeground(Color.YELLOW);
                            lblloadcounter.setText(progressCounter + "%");
                            X.setIcon(ResizeImage(Fileselected));
                            X.setEnabled(false);
                            if (progressCounter >= 100 && X.getIcon().getIconHeight() > 0) {
                                System.gc();
                                lbluploadstatus.setText(selectedFile.getName()
                                        + " Upload Successful!");
                                lblloadcounter.setText(null);
                                String fileselected = "C:\\Users\\Elite Book 8460p\\Documents"
                                        + "\\NetBeansProjects\\Academic Informer\\"
                                        + "src\\Resources\\Images\\Ok-icon.png";
                                X.setEnabled(true);
                                X = lblloadcounter;
                                X.setIcon(ResizeImage(fileselected));

                            } else if (progressCounter >= 100 && X.getIcon().getIconHeight() < 0) {
                                lbluploadstatus.setText(selectedFile.getName()
                                        + " Upload Not Successful!");
                                lblloadcounter.setText(null);
                                String fileselected = "C:\\Users\\Elite Book 8460p\\Documents"
                                        + "\\NetBeansProjects\\Academic Informer\\"
                                        + "src\\Resources\\Images\\button_cancel.png";
                                X.setEnabled(true);
                                X = lblloadcounter;
                                X.setIcon(ResizeImage(fileselected));
                            }

                        }
                    }
                    );
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                    }
                }

            }
        }
        );
        scan.start();

    }

    private class ButtonListener implements ActionListener {

        //updatese the counter when the button is pushed
        @Override
        public void actionPerformed(ActionEvent Event) {
            // count++;

        }
    }

}
