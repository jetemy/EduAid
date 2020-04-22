/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMngtUI;

/**
 *
 * @author jerry
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import Functions.DBConUserMngt;
import Functions.InputVerification;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserRegistration {

    private static final String[] designation = {"Click to select", "Student",
        "Lecturer", "COD", "Dean", "Other"};
    private static final String[] department = {"Click to select",
        "Computer Science", "Information Technology"};
    private static final String[] accessLevel = {"Click to select",
        "System Admin Level", "Admin Level", "User Level"};
    private static final String Gender[] = {"Click to select", "Male", "Female"};
    private DBConUserMngt Usermngt;
    private InputVerification InVer;
    private JProgressBar UploadPB;
    private int progressCounter, counter = 0;
    private JLabel lblloadcounter, lbluploadstatus, X;
    private File selectedFile;
    private String Fileselected;
    private StringPaint UserRegPanel;
    public JDialog UserRegDial;

    public UserRegistration() throws SQLException, IOException {

        UserRegDial = new JDialog();
        UserRegDial.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        Usermngt = new DBConUserMngt();
        InVer = new InputVerification();

        try {
            UserRegDial.setIconImage(ImageIO.read(new File("C:\\Users\\EliteBook 8460p\\Documents\\NetBeansProjects\\Academic "
                    + "Informer\\src\\Resources\\Images\\Register-icon.png")));
        } catch (IOException ex) {
            Logger.getLogger(UserRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //create Labels 
        Usermngt.FNamelbl = new JLabel("First Name");
        Usermngt.FNamelbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.FNamelbl.setForeground(Color.WHITE);
        Usermngt.FNamelbl.setBounds(10, 80, 100, 30);

        Usermngt.MNamelbl = new JLabel("Middle Name");
        Usermngt.MNamelbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.MNamelbl.setForeground(Color.WHITE);
        Usermngt.MNamelbl.setBounds(10, 120, 110, 30);

        Usermngt.LNamelbl = new JLabel("Last Name");
        Usermngt.LNamelbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.LNamelbl.setForeground(Color.WHITE);
        Usermngt.LNamelbl.setBounds(10, 160, 100, 30);

        Usermngt.Genderlbl = new JLabel("Gender");
        Usermngt.Genderlbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.Genderlbl.setForeground(Color.WHITE);
        Usermngt.Genderlbl.setBounds(10, 200, 100, 30);

        Usermngt.RegEmplbl = new JLabel("Reg/Emp No");
        Usermngt.RegEmplbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.RegEmplbl.setForeground(Color.WHITE);
        Usermngt.RegEmplbl.setBounds(10, 240, 130, 30);

        Usermngt.Emaillbl = new JLabel("Email");
        Usermngt.Emaillbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.Emaillbl.setForeground(Color.WHITE);
        Usermngt.Emaillbl.setBounds(10, 280, 50, 30);

        Usermngt.Mobilelbl = new JLabel("Mobile");
        Usermngt.Mobilelbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.Mobilelbl.setForeground(Color.WHITE);
        Usermngt.Mobilelbl.setBounds(10, 320, 150, 30);

        Usermngt.Deptlbl = new JLabel("Department");
        Usermngt.Deptlbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.Deptlbl.setForeground(Color.WHITE);
        Usermngt.Deptlbl.setBounds(10, 360, 150, 30);

        Usermngt.Desiglbl = new JLabel("Designation");
        Usermngt.Desiglbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.Desiglbl.setForeground(Color.WHITE);
        Usermngt.Desiglbl.setBounds(10, 400, 150, 30);

        Usermngt.Titlelbl = new JLabel();
        Usermngt.Titlelbl.setText("Enter User Details");
        Usermngt.Titlelbl.setFont(new Font("ARIAL ", Font.PLAIN, 25));
        Usermngt.Titlelbl.setForeground(Color.YELLOW);
        Usermngt.Titlelbl.setBounds(200, 10, 300, 50);

        Usermngt.MyPhotolbl = new JLabel("USER PHOTO IMAGE");
        Usermngt.MyPhotolbl.setFont(new Font("ARIAL", Font.PLAIN, 18));
        Usermngt.MyPhotolbl.setForeground(Color.WHITE);
        Usermngt.MyPhotolbl.setBounds(355, 100, 250, 30);

        Usermngt.Imagelbl = new JLabel();
        Usermngt.Imagelbl.setBounds(320, 130, 250, 250);
        Usermngt.Imagelbl.setBorder(BorderFactory.createRaisedBevelBorder());

        Usermngt.AccessLevellbl = new JLabel("User Level");
        Usermngt.AccessLevellbl.setFont(new Font("ARIAL", Font.PLAIN, 14));
        Usermngt.AccessLevellbl.setForeground(Color.WHITE);
        Usermngt.AccessLevellbl.setBounds(10, 440, 150, 30);

        lbluploadstatus = new JLabel();
        lbluploadstatus.setBounds(350, 435, 300, 20);

        lblloadcounter = new JLabel();
        lblloadcounter.setBounds(420, 480, 40, 40);

        //progress bar
        UploadPB = new JProgressBar();
        UploadPB.setForeground(Color.CYAN);

        UploadPB.setBounds(320, 465, 260, 5);
        UploadPB.setVisible(false);

        //create text fields
        Usermngt.Fnametxt = new JTextField();
        Usermngt.Fnametxt.setBounds(100, 80, 170, 30);
        Usermngt.Fnametxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {

                Usermngt.Fnametxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));

            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });

        Usermngt.Fnametxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String txt = Usermngt.Fnametxt.getText().toUpperCase();

                Usermngt.Fnametxt.setText(txt);
                String FName = Usermngt.Fnametxt.getText();

                if (InVer.verifyTextOnlyFields(FName) == false) {
                    Graphics x = null;
                    UserRegPanel.setstringFname(UserRegPanel.text);
                    x.drawString(UserRegPanel.stringFname, 130, 172);
                    x.setColor(Color.ORANGE);
                    UserRegPanel.paintComponent(x);
                    Usermngt.Fnametxt.setBackground(Color.PINK);
                } else {
                    Usermngt.Fnametxt.setBackground(Color.WHITE);
                }

            }
        });
        Usermngt.Mnametxt = new JTextField();
        Usermngt.Mnametxt.setBounds(100, 120, 170, 30);
        Usermngt.Mnametxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {

                Usermngt.Mnametxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));

            }

            @Override
            public void focusLost(FocusEvent fe) {

            }
        });
        Usermngt.Mnametxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String txt = Usermngt.Mnametxt.getText().toUpperCase();

                Usermngt.Mnametxt.setText(txt);
                String MName = Usermngt.Mnametxt.getText();

                if (InVer.verifyTextOnlyFields(MName) == false) {

                    Usermngt.Mnametxt.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    Usermngt.Mnametxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));
                }

            }
        });

        Usermngt.Lnametxt = new JTextField();
        Usermngt.Lnametxt.setBounds(100, 160, 170, 30);
        Usermngt.Lnametxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Usermngt.Lnametxt.setText("");
                Usermngt.Lnametxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));

            }

            @Override
            public void focusLost(FocusEvent fe) {

            }
        });
        Usermngt.Lnametxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String txt = Usermngt.Lnametxt.getText().toUpperCase();

                Usermngt.Lnametxt.setText(txt);
                String LName = Usermngt.Lnametxt.getText();

                if (InVer.verifyTextOnlyFields(LName) == false) {
                    Usermngt.Lnametxt.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    Usermngt.Lnametxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));
                }

            }
        });

        Usermngt.RegEmptxt = new JTextField();
        Usermngt.RegEmptxt.setBounds(100, 240, 170, 30);
        Usermngt.RegEmptxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String txt = Usermngt.RegEmptxt.getText().toUpperCase();

                Usermngt.RegEmptxt.setText(txt);

            }
        });

        Usermngt.Emailtxt = new JTextField();
        Usermngt.Emailtxt.setBounds(100, 280, 170, 30);
        Usermngt.Emailtxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Usermngt.Emailtxt.setText("");
                Usermngt.Emailtxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));

            }

            @Override
            public void focusLost(FocusEvent fe) {

            }
        });
        Usermngt.Emailtxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String txt = Usermngt.Emailtxt.getText().toUpperCase();

                //Usermngt.Emailtxt.setText(txt);
                String Email = Usermngt.Emailtxt.getText();

                if (InVer.verifyEmails(Email) == false) {
                    Usermngt.Emailtxt.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    Usermngt.Emailtxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));
                }

            }
        });

        Usermngt.Mobiletxt = new JTextField();
        Usermngt.Mobiletxt.setBounds(100, 320, 170, 30);
        Usermngt.Mobiletxt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Usermngt.Mobiletxt.setText("");
                Usermngt.Mobiletxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));

            }

            @Override
            public void focusLost(FocusEvent fe) {

            }
        });
        Usermngt.Mobiletxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                String Email = Usermngt.Mobiletxt.getText();

                if (InVer.verifyNumbersOnlyFields(Email) == false) {
                    Usermngt.Mobiletxt.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    Usermngt.Mobiletxt.setBorder(BorderFactory.createLineBorder(Color.decode("#6E6E6E")));
                }

            }
        });
        //Create JComboBox
        Usermngt.Gendercmb = new JComboBox(Gender);
        Usermngt.Gendercmb.setBounds(100, 200, 170, 30);
        
        
        Usermngt.Deptcmb = new JComboBox(department);
        Usermngt.Deptcmb.setBounds(100, 360, 170, 30);
        Usermngt.Deptcmb.addActionListener((ActionEvent e) -> {
            if (Usermngt.Deptcmb.getSelectedIndex() > 0) {
                Usermngt.Deptcmb.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            }

        });

        Usermngt.AccessLevelcmb = new JComboBox(accessLevel);
        Usermngt.AccessLevelcmb.setBounds(100, 400, 170, 30);
        Usermngt.AccessLevelcmb.addActionListener((ActionEvent e) -> {
            if (Usermngt.AccessLevelcmb.getSelectedIndex() > 0) {
                Usermngt.AccessLevelcmb.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            }

        });

        Usermngt.Desigcmb = new JComboBox(designation);
        Usermngt.Desigcmb.setBounds(100, 440, 170, 30);
        Usermngt.Desigcmb.addActionListener((ActionEvent e) -> {
            if (Usermngt.Desigcmb.getSelectedIndex() > 0) {
                Usermngt.Desigcmb.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            }

        });

        //Create Buttons
        Usermngt.CaptureBtn = new JButton("Capture Image");
        Usermngt.CaptureBtn.setBounds(450, 390, 120, 30);
        Usermngt.CaptureBtn.setForeground(Color.WHITE);
        Usermngt.CaptureBtn.setBackground(Color.decode("#8A2BE2"));

        Usermngt.UpLoadbtn = new JButton("UpLoad Image");
        Usermngt.UpLoadbtn.setBounds(320, 390, 120, 30);
        Usermngt.UpLoadbtn.setForeground(Color.WHITE);
        Usermngt.UpLoadbtn.setBackground(Color.decode("#8A2BE2"));
        Usermngt.UpLoadbtn.addActionListener((ActionEvent e) -> {

            // new ButtonCounter();
            X = Usermngt.Imagelbl;
            UploadPB.setVisible(true);

            counter++;
            JOptionPane.showMessageDialog(null,X.getIcon() );
            if (X.getIcon() != null && counter > 1) {

                if (JOptionPane.showConfirmDialog(null, "Change Photo iMAGE?", "CHANGE PHOTO",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    UploadPB.setVisible(false);
                    lbluploadstatus.setText(null);
                    lblloadcounter.setIcon(null);
                    X.setIcon(null);
                    progressCounter = 0;
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg", "JPG", "png", "gif", "*.IMAGE");
                    fileChooser.setFileFilter(filter);
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                    int result = fileChooser.showOpenDialog(UserRegDial);

                    if (result == JFileChooser.APPROVE_OPTION) {

                        selectedFile = fileChooser.getSelectedFile();

                        Fileselected = selectedFile.getAbsolutePath();
                        Progressbar();
                    }
                } else {

                    System.gc();
                }

            } else if (X.getIcon() == null )//&& lbluploadstatus.getText().contains(" Upload Not Successful!")||
                    /*lbluploadstatus.getText() == null)*/ {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg", "JPG");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                int result = fileChooser.showOpenDialog(UserRegDial);

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

                int result = fileChooser.showOpenDialog(UserRegDial);

                if (result == JFileChooser.APPROVE_OPTION) {

                    selectedFile = fileChooser.getSelectedFile();

                    Fileselected = selectedFile.getAbsolutePath();
                    Progressbar();
                }
            }

        });

        Usermngt.Savebtn = new JButton("Save");
        Usermngt.Savebtn.setBounds(200, 540, 70, 30);
        Usermngt.Savebtn.setForeground(Color.WHITE);
        Usermngt.Savebtn.setBackground(Color.decode("#8A2BE2"));
        Usermngt.Savebtn.addActionListener((ActionEvent e) -> {

            if (Usermngt.Desigcmb.getSelectedIndex() < 1 && Usermngt.Deptcmb.getSelectedIndex() < 1
                    && Usermngt.AccessLevelcmb.getSelectedIndex() < 1) {
                Usermngt.Desigcmb.setBorder(BorderFactory.createLineBorder(Color.RED));
                Usermngt.Deptcmb.setBorder(BorderFactory.createLineBorder(Color.RED));
                Usermngt.AccessLevelcmb.setBorder(BorderFactory.createLineBorder(Color.RED));
                JOptionPane.showMessageDialog(null, "Check! Missing info ", "Missing", 2);
                System.gc();

            } else if (Usermngt.Deptcmb.getSelectedIndex() < 1) {
                JOptionPane.showMessageDialog(null, "Select Department ", "Error", 0);
                System.gc();
            } else if (Usermngt.Desigcmb.getSelectedIndex() < 1) {
                JOptionPane.showMessageDialog(null, "Select Designation ", "Error", 0);
                System.gc();
            } else if (Usermngt.AccessLevelcmb.getSelectedIndex() < 1) {
                JOptionPane.showMessageDialog(null, "Select UserLevel ", "Error", 0);
                System.gc();
            } else if (JOptionPane.showConfirmDialog(null, "Do want to save this information ",
                    "Save", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String photofilepath = Fileselected;
                JOptionPane.showMessageDialog(null, photofilepath, "Error", 0);
                Usermngt.UserDetails(Usermngt.RegEmptxt.getText(),Usermngt.Fnametxt.getText().toUpperCase(),
                        Usermngt.Mnametxt.getText().toUpperCase(), Usermngt.Lnametxt.
                                getText().toUpperCase(),Usermngt.Gendercmb.getSelectedItem().toString(),
                                photofilepath);

                 Usermngt.UserContacts(Usermngt.RegEmptxt.getText(), Usermngt.Emailtxt.getText().toLowerCase(),
                        Integer.valueOf(Usermngt.Mobiletxt.getText()),Usermngt.Deptcmb.getSelectedItem().toString(), Usermngt.Desigcmb.
                        getSelectedItem().toString(),  Usermngt.AccessLevelcmb.getSelectedItem().toString()
                );
                //clearEntries();
            } else {
                System.gc();
            }
        });

        //BTNSearch.setBackground(Color.decode("#2F4F4F"));
        //BTNSearch.setForeground(Color.decode("#00FFFF"));
        //CashPanel.setBackground(Color.decode("#2F4F4F"));
        Usermngt.Clrbtn = new JButton("Clear");
        Usermngt.Clrbtn.setBounds(290, 540, 70, 30);
        Usermngt.Clrbtn.setForeground(Color.WHITE);
        Usermngt.Clrbtn.setBackground(Color.decode("#8A2BE2"));
        Usermngt.Clrbtn.addActionListener((ActionEvent e) -> {
            if (JOptionPane.showConfirmDialog(null, "Clear All Entries?", "Clear", 2) == JOptionPane.OK_OPTION) {
                clearEntries();
            } else {
                System.gc();
            }

        });

        Usermngt.Exitbtn = new JButton("Exit");
        Usermngt.Exitbtn.setBounds(380, 540, 70, 30);
        Usermngt.Exitbtn.setForeground(Color.WHITE);
        Usermngt.Exitbtn.setBackground(Color.decode("#8A2BE2"));
        Usermngt.Exitbtn.addActionListener((ActionEvent e) -> {

            UserRegDial.dispose();

        });

        UserRegPanel = new StringPaint();
        UserRegPanel.setBackground(Color.decode("#4B0082"));
        UserRegPanel.setLayout(null);

        //Add components to JPanel
        UserRegPanel.add(Usermngt.FNamelbl);
        UserRegPanel.add(Usermngt.MNamelbl);
        UserRegPanel.add(Usermngt.LNamelbl);
        UserRegPanel.add(Usermngt.Imagelbl);
        UserRegPanel.add(Usermngt.Genderlbl);
        UserRegPanel.add(Usermngt.MyPhotolbl);
        UserRegPanel.add(Usermngt.UpLoadbtn);
        UserRegPanel.add(Usermngt.Desigcmb);
        UserRegPanel.add(Usermngt.Gendercmb);
        UserRegPanel.add(Usermngt.RegEmplbl);
        UserRegPanel.add(Usermngt.AccessLevellbl);
        UserRegPanel.add(Usermngt.AccessLevelcmb);
        UserRegPanel.add(Usermngt.Emaillbl);
        UserRegPanel.add(Usermngt.Mobilelbl);
        UserRegPanel.add(Usermngt.Deptlbl);
        UserRegPanel.add(Usermngt.Deptcmb);
        UserRegPanel.add(Usermngt.Desiglbl);
        UserRegPanel.add(Usermngt.Titlelbl);
        UserRegPanel.add(Usermngt.Fnametxt);
        UserRegPanel.add(Usermngt.Mnametxt);
        UserRegPanel.add(Usermngt.Lnametxt);
        UserRegPanel.add(Usermngt.RegEmptxt);
        UserRegPanel.add(Usermngt.Emailtxt);
        UserRegPanel.add(Usermngt.Mobiletxt);
        UserRegPanel.add(Usermngt.CaptureBtn);
        UserRegPanel.add(Usermngt.Savebtn);
        UserRegPanel.add(Usermngt.Clrbtn);
        UserRegPanel.add(Usermngt.Exitbtn);
        UserRegPanel.add(UploadPB);
        UserRegPanel.add(lblloadcounter);
        UserRegPanel.add(lbluploadstatus);

        UserRegDial.add(UserRegPanel);
        UserRegDial.setSize(620, 630);
        UserRegDial.setLocation(300, 70);
        UserRegDial.setTitle(" Register User");
        UserRegDial.setVisible(true);
    }

    public static void main(String[] args) throws SQLException, IOException {
        UserRegistration ur = new UserRegistration();

    }

    private void clearEntries() {
        progressCounter=0;
        lblloadcounter.setText(null);
        lblloadcounter.setIcon(null);
        Usermngt.Imagelbl.setIcon(null);
        lbluploadstatus.setText(null);
        Usermngt.Fnametxt.setText("");
        Usermngt.Mnametxt.setText("");
        Usermngt.Lnametxt.setText("");
        Usermngt.RegEmptxt.setText("");
        Usermngt.Emailtxt.setText("");
        Usermngt.Mobiletxt.setText("");
        Usermngt.Desigcmb.setSelectedItem("Click to select");
        Usermngt.Deptcmb.setSelectedItem("Click to select");
        Usermngt.AccessLevelcmb.setSelectedItem("Click to select");
        Usermngt.Gendercmb.setSelectedItem("Click to select");
    }

    private ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(X.getWidth(), X.getHeight(), Image.SCALE_SMOOTH);
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
                                String fileselected = "C:\\Users\\EliteBook 8460p\\Documents"
                                        + "\\NetBeansProjects\\Academic Informer\\"
                                        + "src\\Resources\\Images\\Ok-icon.png";
                                X.setEnabled(true);
                                X = lblloadcounter;
                                X.setIcon(ResizeImage(fileselected));

                            } else if (progressCounter >= 100 && X.getIcon().getIconHeight() < 0) {
                                lbluploadstatus.setText(selectedFile.getName()
                                        + " Upload Not Successful!");
                                lblloadcounter.setText(null);
                                String fileselected = "C:\\Users\\EliteBook 8460p\\Documents"
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

    public class StringPaint extends JPanel {

        String stringFname, stringMname, stringLname, stringInt, stringemail;

        String text = "Wrong charecter input";

        String integers = "Only figures accepted";

        String email = "Wrong Email format";

        //String setter methods for the the painters
        public void setstringFname(String x) {

            this.stringFname = x;

        }

        public void setstringMname(String u) {

            this.stringMname = u;

        }

        public void setstringLname(String v) {

            this.stringLname = v;

        }

        public void setstringemail(String x) {

            this.stringemail = x;

        }

        public void setstringInt(String y) {

            this.stringInt = y;

        }

        public StringPaint() {
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            //Paint error text for tex only inputs 
            setstringFname(text);
            Font myFont1 = new Font("Arial", Font.PLAIN, 12);
            g.setFont(myFont1);
            g.setColor(Color.decode("#4B0082"));
            g.drawString(stringFname, 130, 122);
            

            setstringMname(text);
            Font myFont2 = new Font("Arial", Font.PLAIN, 12);
            g.setFont(myFont2);
            g.setColor(Color.ORANGE);
            g.drawString(stringMname, 130, 172);

            setstringLname(text);
            Font myFont3 = new Font("Arial", Font.PLAIN, 12);
            g.setFont(myFont3);
            g.setColor(Color.ORANGE);
            g.drawString(stringLname, 130, 222);

            setstringemail(email);
            Font myFont4 = new Font("Arial", Font.PLAIN, 12);
            g.setFont(myFont4);
            g.setColor(Color.ORANGE);
            g.drawString(stringemail, 130, 322);
            

            //Paint error for integer only inputs
            setstringInt(integers);
            Font myFont5 = new Font("Arial", Font.PLAIN, 12);
            g.setFont(myFont5);
            g.setColor(Color.ORANGE);
            g.drawString(stringInt, 130, 372);

        }

    }
}
