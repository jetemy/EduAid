/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EduaidUI;

/**
 *
 * @author USER
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import Functions.DBConUserMngt;
import UserMngtUI.UserRegistration;
import UserMngtUI.SignUp;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;

public class Init {

    public DigitalClock myClock;
    public JFrame InitFrm;
    private final JSeparator sep1, sep2, sep3, sep4, sep5, sep6, sep7;
    private JList list;
    public JPanel panel, Anncpanel;
    private final DBConUserMngt func;
    private final String[] listitems = {"  Mail Box", "  My Account", "  Time Table",
        "  Lectures", "  Class Attendance", " Content Upload/Download", "  Announcements",
        "  Chat Room", "  Suggestion Box", "  Activities", "  Administration",
        "  Syst", "  Search", "  Help"};
   
    private final JLabel menulbl, Annclbl, Annclbl1, X;
    private String msg;
    public Assignments ass;
    public MyAccount ma;
    private JMenu file, edit, admin, users, Tools, Syst, help;
    private JMenuItem about, contact, Licence, update, guide, Registration;
    public JLabel NameDisplbl,RegEmpDisplbl,DeptDisplbl;
    public JFrame f;

    public Init() throws SQLException {

         f = new JFrame();
         func = new DBConUserMngt();

        ass = new Assignments();
        //ma = new MyAccount();

        msg = "C:\\Users\\EliteBook 8460p\\Documents\\NetBeansProjects\\Academic "
                + "Informer\\src\\Resources\\Images\\Register-icon.png";

        try {
            f.setIconImage(ImageIO.read(new File("C:\\Users\\EliteBook 8460p\\Documents\\NetBeansProjects\\Academic "
                    + "Informer\\src\\Resources\\Images\\Register-icon.png")));
        } catch (IOException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Create Main Panel
        panel = new JPanel();
        panel.setBounds(230, 115, 750, 530);
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBackground(Color.decode("#4B0082"));
        panel.setLayout(null);

        Anncpanel = new JPanel();
        Anncpanel.setLayout(null);
        Anncpanel.setBounds(20, 510, 200, 135);
        Anncpanel.setBackground(Color.decode("#191970"));
        Anncpanel.setLayout(null);

        //Create JFrame
        InitFrm = new JFrame("  EduAid  ");

        //Create Labels
        menulbl = new JLabel("MENU");
        menulbl.setFont(new Font("ARIAL", Font.PLAIN, 20));
        menulbl.setForeground(Color.BLUE);
        menulbl.setBounds(20, 85, 150, 30);

        func.Namelbl = new JLabel("Name :");
        func.Namelbl.setFont(new Font("ARIAL", Font.BOLD, 15));
        func.Namelbl.setForeground(Color.red);
        func.Namelbl.setBounds(20, 20, 70, 30);

        NameDisplbl = new JLabel();
        NameDisplbl.setForeground(Color.BLUE);
        NameDisplbl.setFont(new Font("ARIAL", Font.PLAIN, 12));
        NameDisplbl.setBounds(295, 95, 160, 20);

        RegEmpDisplbl = new JLabel();
        RegEmpDisplbl.setFont(new Font("ARIAL", Font.BOLD, 12));
        RegEmpDisplbl.setBounds(540, 95, 120, 20);

        DeptDisplbl = new JLabel();
        DeptDisplbl.setFont(new Font("ARIAL", Font.PLAIN, 15));
        DeptDisplbl.setBounds(758, 95, 215, 20);

        Annclbl = new JLabel();
        Annclbl.setBounds(55, 1, 90, 110);
        Annclbl.setForeground(Color.YELLOW);
        Annclbl.setFont(new Font("ARIAL BLACK", Font.BOLD, 30));
        X = Annclbl;
        Annclbl.setIcon(ResizeImage(msg));

        Annclbl1 = new JLabel();
        Annclbl1.setBounds(0, 110, 200, 20);
        Annclbl1.setForeground(Color.YELLOW);
        Annclbl1.setFont(new Font("ARIAL ", Font.PLAIN, 12));

        //Add Announcement labels to announcement panel
        Anncpanel.add(Annclbl);
        Anncpanel.add(Annclbl1);

        //create buttons
        func.Exitbtn = new JButton("Exit");
        func.Exitbtn.setBackground(Color.BLACK);
        func.Exitbtn.setForeground(Color.WHITE);
        func.Exitbtn.setBounds(250, 720, 100, 30);
        func.Exitbtn.addActionListener((ActionEvent e) -> {

            System.exit(0);
        });

        //create a JList
        list = new JList(listitems);
        list.setBounds(20, 115, 200, 420);
        list.setBackground(Color.decode("#191970"));
        list.setForeground(Color.WHITE);
        list.setFixedCellHeight(30);
        list.setFont(new Font("ARIAL", Font.PLAIN, 15));
        list.addListSelectionListener((ListSelectionEvent e) -> {
            try {
                list.setSelectionBackground(Color.decode("#191970"));
                list.setSelectionForeground(Color.MAGENTA);
                menuItems();

            } catch (SQLException ex) {
                Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //create JMenuBar
        JMenuBar Bar = new JMenuBar();
        Bar.setSize(1000, 40);

        //CREATE MENU ITEMS        
        file = new JMenu("File");
        file.setMnemonic('F');
        file.setFont(new Font("Arial", Font.PLAIN, 15));

        edit = new JMenu("Edit");
        edit.setMnemonic('E');
        edit.setFont(new Font("Arial", Font.PLAIN, 15));

        admin = new JMenu("Admin");
        admin.setMnemonic('a');
        admin.setFont(new Font("Arial", Font.PLAIN, 15));

        users = new JMenu("Users");
        users.setMnemonic('u');
        users.setFont(new Font("Arial", Font.PLAIN, 15));

        Syst = new JMenu("System");
        Syst.setEnabled(true);
        Syst.setMnemonic('R');
        Syst.setFont(new Font("Arial", Font.PLAIN, 15));

        Tools = new JMenu("Tools");
        Tools.setMnemonic('t');
        Tools.setFont(new Font("Arial", Font.PLAIN, 15));

        help = new JMenu("Help");
        help.setMnemonic('h');
        help.setFont(new Font("Arial", Font.PLAIN, 15));

        //
        //Add items to the menu Bar
        Bar.add(file);
        Bar.add(edit);
        Bar.add(admin);
        Bar.add(users);
        Bar.add(Tools);
        Bar.add(help);
        Bar.add(Box.createVerticalStrut(150));

        //Create and Add menu Items 
        /*======FILE MENU======*/
        JMenuItem jmiOpen = new JMenuItem("Open");
        jmiOpen.setFont(new Font("Arial", Font.PLAIN, 15));
        jmiOpen.setMnemonic('o');
        JMenuItem jmiPrint = new JMenuItem("Print");
        jmiPrint.setFont(new Font("Arial", Font.PLAIN, 15));
        jmiPrint.setMnemonic('p');
        JMenuItem jmiClose = new JMenuItem("Close");
        jmiClose.setFont(new Font("Arial", Font.PLAIN, 15));
        jmiClose.setMnemonic('c');
        JMenuItem jmiSave = new JMenuItem("Save");
        jmiSave.setFont(new Font("Arial", Font.PLAIN, 15));
        jmiSave.setMnemonic('s');
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmiExit.setFont(new Font("Arial", Font.PLAIN, 15));
        jmiExit.setMnemonic('e');
        jmiExit.addActionListener((ActionEvent e) -> {
            System.exit(0);

        });
        //
        file.setBackground(Color.decode("#4B0082"));
        file.add(jmiOpen);
        file.add(jmiPrint);
        file.add(jmiClose);
        file.add(jmiSave);
        file.addSeparator();
        file.add(jmiExit);
        file.add(Box.createHorizontalStrut(150));

        /*======EDIT MENU======*/
 /*======ADMIN MENU======*/
        JMenuItem jaireguser = new JMenuItem("Register User");
        jaireguser.setFont(new Font("Arial", Font.PLAIN, 15));
        jaireguser.setMnemonic('u');
        jaireguser.addActionListener((ActionEvent e) -> {
            try {
                UserRegistration ur = new UserRegistration();
            } catch (SQLException ex) {
                Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //UserSignUpUI ur = new UserSignUpUI();
        //ur.UserSignUpDial.setVisible(true);
        JMenuItem jaiedituser = new JMenuItem("Edit User Account");
        jaiedituser.setFont(new Font("Arial", Font.PLAIN, 15));

        jaiedituser.setMnemonic('e');
        jaiedituser.addActionListener((ActionEvent e) -> {
            ma.panel.setVisible(true);

        });

        JMenuItem jaiUserSignUp = new JMenuItem("User SignUp");
        jaiUserSignUp.setFont(new Font("Arial", Font.PLAIN, 15));
        jaiUserSignUp.setMnemonic('U');
        jaiUserSignUp.addActionListener((ActionEvent e) -> {
            try {
                SignUp ur;
                try {
                    ur = new SignUp();
                    //ur.UserSignUpDial.setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JMenuItem jairegCourse = new JMenuItem("Register Program");
        jairegCourse.setFont(new Font("Arial", Font.PLAIN, 15));
        jairegCourse.setMnemonic('P');
        
        JMenuItem jaiBackup = new JMenuItem("System Backup");
        jaiBackup.setFont(new Font("Arial", Font.PLAIN, 15));
        JMenuItem jaiRestore = new JMenuItem("System Restore");
        jaiRestore.setFont(new Font("Arial", Font.PLAIN, 15));
        Syst.setMnemonic('s');
        Syst.add(jaiBackup);
        Syst.add(jaiRestore);
        admin.add(jaireguser);
        admin.add(jaiUserSignUp);
        admin.add(jairegCourse);
        admin.add(jaiedituser);
        admin.add(Syst);

        /*======USERS MENU======*/
 /*======SETTING MENU======*/
 /*======REPORTS MENU======*/
 /*======HELP MENU======*/
        about = new JMenuItem("About Eduaid");
        about.setFont(new Font("Arial", Font.PLAIN, 15));
        contact = new JMenuItem("Report Use");
        contact.setFont(new Font("Arial", Font.PLAIN, 15));
        Licence = new JMenu("Licence");
        Licence.setFont(new Font("Arial", Font.PLAIN, 15));
        update = new JMenuItem("Check for Updates");
        update.setFont(new Font("Arial", Font.PLAIN, 15));
        guide = new JMenuItem("Help Contents");
        guide.setFont(new Font("Arial", Font.PLAIN, 15));
        Registration = new JMenuItem("Registration");
        Registration.setFont(new Font("Arial", Font.PLAIN, 15));
        JMenuItem Agreement = new JMenuItem("Agreement");
        Agreement.setFont(new Font("Arial", Font.PLAIN, 15));

        Licence.add(Registration);
        Licence.add(Agreement);

        help.add(about);
        help.add(guide);
        help.addSeparator();
        help.add(Licence);
        help.addSeparator();
        help.add(update);
        help.add(contact);

        help.add(Box.createHorizontalStrut(150));

        //create aJSeparator
        sep1 = new JSeparator(SwingConstants.HORIZONTAL);
        sep1.setLocation(555, 75);
        sep1.setSize(120, 5);

        sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setLocation(328, 75);
        sep2.setSize(120, 5);

        sep3 = new JSeparator(SwingConstants.VERTICAL);
        sep3.setBackground(Color.GREEN);
        sep3.setLocation(225, 115);
        sep3.setSize(2, 530);

        sep4 = new JSeparator(SwingConstants.HORIZONTAL);
        sep4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        sep4.setLocation(2, 650);
        sep4.setSize(990, 2);

        sep5 = new JSeparator(SwingConstants.VERTICAL);
        sep5.setBorder(BorderFactory.createRaisedBevelBorder());
        sep5.setLocation(150, 653);
        sep5.setSize(2, 20);

        sep6 = new JSeparator(SwingConstants.VERTICAL);
        sep6.setBorder(BorderFactory.createRaisedBevelBorder());
        sep6.setLocation(600, 653);
        sep6.setSize(2, 20);

        sep7 = new JSeparator(SwingConstants.VERTICAL);
        sep7.setBorder(BorderFactory.createRaisedBevelBorder());
        sep7.setLocation(850, 653);
        sep7.setSize(2, 20);
        //Add Components to the Main Panel
       // panel.add(ma.panel);
        panel.add(ass.panel);

        myClock = new DigitalClock();
        myClock.setLayout(null);

        myClock.add(list);
        myClock.add(menulbl);
        myClock.add(Bar);
        myClock.add(sep1);
        myClock.add(sep2);
        myClock.add(sep3);
        myClock.add(sep4);
        myClock.add(sep5);
        myClock.add(sep6);
        myClock.add(sep7);
        myClock.add(panel);
        myClock.add(Anncpanel);
//        myClock.add(func.Exitbtn);
        //myClock.add(func.NameDisplbl);
        //myClock.add(func.DesigDisplbl);
      //  myClock.add(func.DeptDisplbl);
   //     myClock.add(func.RegEmpDisplbl);

        f.add(myClock);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(1000, 705);

        f.setLocation(10, 10);
        f.setResizable(false);
        f.setVisible(false);

    }

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
            java.util.logging.Logger.getLogger(Init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    Init sc = new Init();
                    sc.f.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    private void reSize() {
        int str = list.getSelectedValue().toString().length();

        if (str >= 13 && str < 15) {
            Annclbl.setFont(new Font("ARIAL BLACK", Font.BOLD, 22));
            Annclbl.setText(list.getSelectedValue().toString());
        } else if (str >= 15 && str < 18) {
            Annclbl.setFont(new Font("ARIAL BLACK", Font.BOLD, 19));
            Annclbl.setText(list.getSelectedValue().toString());
        } else if (str >= 18) {
            Annclbl.setFont(new Font("ARIAL BLACK", Font.BOLD, 17));
            Annclbl.setText(list.getSelectedValue().toString());
        } else {
            Annclbl.setFont(new Font("ARIAL BLACK", Font.BOLD, 25));
            Annclbl.setText(list.getSelectedValue().toString());
        }

    }

    private void menuItems() throws SQLException {

        int sel = list.getSelectedIndex();

        switch (sel) {

            case 0: {
                reSize();
                Annclbl1.setText("   View and send emails");
                Annclbl1.add(sep1);
                break;
            }

            case 1: {

                Annclbl1.setText(" View and edit personal details.");
                reSize();
                ass.panel.setVisible(false);
                ma.panel.setVisible(true);
                panel.repaint();

                break;
            }
            case 2: {
                Annclbl1.setText("   View teaching time table.");
                reSize();

                break;
            }
            case 3: {
                reSize();
                Annclbl1.setFont(new Font("ARIAL ", Font.BOLD, 12));
                Annclbl1.setText(" Lectures information & resources");

                break;
            }
            case 4: {
                reSize();
                Annclbl1.setText(" View class attendance lists.");

                break;
            }
            case 5: {
                reSize();
                Annclbl1.setFont(new Font("ARIAL ", Font.BOLD, 12));
                Annclbl1.setText(" Upload & download materials.");

                //ma.panel.setVisible(false);
                ass.panel.setVisible(true);

                break;
            }
            case 6:
                reSize();
                Annclbl1.setText("  View and post annoucements.");

                break;
            case 7:
                reSize();
                Annclbl1.setText("  Chat with other users");
                break;
            case 8:
                reSize();
                Annclbl1.setFont(new Font("ARIAL ", Font.BOLD, 12));
                Annclbl1.setText("  Drop annonymous suggestions");
                break;
            case 9:
                reSize();
                Annclbl1.setText(" Ongoing & Upcoming activities");
                break;
            case 10:
                reSize();
                Annclbl1.setText(" User & Syst administration");
                break;
            case 11:
                reSize();
                Annclbl1.setText(" Adminstrative & User Syst");

                break;
            case 12:
                reSize();
                Annclbl1.setText("  Item search funtions");

                break;
            case 13:
                reSize();
                Annclbl1.setText("  Syst help funtions");
                break;

        }

    }

    private class DigitalClock extends JPanel {

        String stringTime, stringTime2, stringTime3, ItemView, Hr, NameReg, SignIn;

        int hour, Hour, minute, second, millisecond, timeOfDay;

        String correctionHour = "";
        String correctionMinute = "";
        String correctionSecond = "";
        String correctionmillisecond = "";
        String stringDay = LocalDate.now().getDayOfWeek().toString();
        int stringMonth = LocalDate.now().getDayOfMonth();
        String stringYear = LocalDate.now().getMonth().toString();
        int stringDate = LocalDate.now().getYear();
        String Name = "Name:";
        String Reg_PFNo = "Reg/PF No:";
        String Dept_School = "Dept/School:";
        String Title = "Current User";

        //String setter methods for the the painters
        public void setNameReg(String vxy) {

            this.NameReg = vxy;

        }

        public void setTitle(String v) {

            this.SignIn = v;

        }

        public void setStringTime(String vxyz) {

            this.stringTime = vxyz;

        }

        public void setStringTime2(String xyz) {
            this.stringTime2 = xyz;
        }

        public void setItemView(String x) {
            this.ItemView = x;
        }

        public void setStringTime3(String vxyz) {

            this.stringTime3 = vxyz;

        }

        DigitalClock() {

            Timer t1 = new Timer(10, (ActionEvent e) -> {
                super.repaint();
            });
            t1.start();

        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            Calendar now = Calendar.getInstance();
            hour = now.get(Calendar.HOUR_OF_DAY);
            Hour = now.get(Calendar.HOUR);
            minute = now.get(Calendar.MINUTE);
            second = now.get(Calendar.SECOND);
            millisecond = now.get(Calendar.MILLISECOND);

            if (LocalTime.now().getHour() >= 12) {
                Hr = "PM";
            } else {
                Hr = "AM";
            }

            if (Hour < 10) {
                this.correctionHour = "0";
            }
            if (Hour >= 10) {
                this.correctionHour = "";
            }

            if (minute < 10) {
                this.correctionMinute = "0";
            }
            if (minute >= 10) {
                this.correctionMinute = "";
            }

            if (second < 10) {
                this.correctionSecond = "0";
            }
            if (second >= 10) {
                this.correctionSecond = "";
            }
            if (millisecond < 10) {
                this.correctionmillisecond = "0";
            }
            if (millisecond >= 10) {
                this.correctionmillisecond = "";
            }
            //paint time at the top
            setStringTime(correctionHour + Hour + ":" + correctionMinute + minute
                    + ":" + correctionSecond + second + " " + Hr);

            Font myFont = new Font("Arial", Font.PLAIN, 18);
            g.setFont(myFont);
            g.setColor(Color.decode("#191970"));
            g.drawString(stringTime, 820, 65);

            //paint time at the footer
            setStringTime2(hour + ":" + correctionMinute + minute
                    + ":" + correctionSecond + second + ":" + correctionmillisecond
                    + millisecond);

            Font myFont2 = new Font("Arial", Font.PLAIN, 12);
            g.setFont(myFont2);
            g.setColor(Color.decode("#191970"));
            g.drawString(stringTime2, 865, 670);

            //paint the date
            setStringTime3(stringDay + " " + stringMonth
                    + " " + stringYear + " " + stringDate);

            Font myFont3 = new Font("Arial", Font.PLAIN, 18);
            g.setFont(myFont3);
            g.setColor(Color.decode("#191970"));
            g.drawString(stringTime3, 20, 65);

            //Paint the Name,Reg and Dept
            setNameReg(Name + "                                          "
                    + "" + Reg_PFNo + "                                " + Dept_School);
            Font myFont4 = new Font("SansSerif", Font.BOLD, 14);
            g.setFont(myFont4);
            g.setColor(Color.decode("#FF0000"));
            g.drawString(NameReg, 250, 110);

            //Paint the signed in title
            setTitle(Title);
            Font myFont5 = new Font("Arial", Font.PLAIN, 18);
            g.setFont(myFont5);
            g.setColor(Color.BLUE);
            g.drawString(SignIn, 450, 80);
        }

    }
}
