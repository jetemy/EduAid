/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import EduaidUI.Init;
import UserMngtUI.UserLogIn;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import UserMngtUI.UserRegistration;
import static UserMngtUI.UserLogIn.PasswordField;
import UserMngtUI.SignUp;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class DBConUserMngt {

    public JLabel UserNamelbl, UserNameDisplbl, RegEmplbl, RegEmpDisplbl, FNamelbl,
            MNamelbl, LNamelbl, Genderlbl, Passwordlbl, Passwordconflbl, Titlelbl,
            Emaillbl, Namelbl, NameDisplbl,
            Selectionlbl, Mobilelbl, Desiglbl, DesigDisplbl, Deptlbl, DeptDisplbl,
            Imagelbl, MyPhotolbl, AccessLevellbl, MyAccountlbl, UserLevellbl;
    public JButton Savebtn, Clrbtn, Exitbtn, EditButton, RemovePhotobtn, UpLoadbtn, OKButton,
            CANCELButton, CaptureBtn;
    public JTextField Nametxt, UserNametxt, RegEmptxt, Passwordtxt, Emailtxt,
            Fnametxt, Mnametxt, Lnametxt,
            Mobiletxt, Passwordconftxt, Desigtxt, Depttxt, AccessLeveltxt;
    public static JComboBox RegEmpcmb, Desigcmb, Deptcmb, AccessLevelcmb, Gendercmb;
    public static Object[] obj;
    private PreparedStatement ps;
    private Statement stmt;
    private ResultSet rs;
    private final String URL = "jdbc:mysql://localhost:3306/eduaid";
    private final String user = "root";
    private final String pw = "";
   // private final Connection con;
    private String name, password, RegNo;
    private UserRegistration ur;
    private UserLogIn in;
    private SignUp Signup;

    public DBConUserMngt() throws SQLException  { 
        
        

    }
    //Method to connect to the database
/*--------------------------------------------------------*/
    public void DBConnect()  {

        try {
            //REGISTER DRIVER
            Class.forName("com.mysql.jdbc.Driver");

            //ESTABLISH CONNECTION
        } catch (ClassNotFoundException exe) {
            JOptionPane.showMessageDialog(null, exe + " ",
                    "DB Connect", 1);
        }

    }
    
    Connection con= DriverManager.getConnection(URL, user, pw);
    //Method to check user login details from the database
/*----------------------------------------------------------------*/
    public void pswdchk() throws SQLException, IOException {

        String pswdchk = "SELECT UserName, Password , RegNo from Signup WHERE  BINARY UserName='"
                + Nametxt.getText() + "' AND BINARY Password='"
                + String.valueOf(PasswordField.getPassword()) + "'";

        ps = con.prepareStatement(pswdchk);
        rs = ps.executeQuery();
        if (rs.next()) {

            name = rs.getString("UserName");
            password = rs.getString("Password");
            RegNo = rs.getString("RegNo");
        } else if (Nametxt.getText().equals(name) && !String.valueOf(PasswordField.
                getPassword()).equals(password)) {
            JOptionPane.showMessageDialog(null, Nametxt.getText() + " 6  " + name + " "
                    + String.valueOf(UserLogIn.PasswordField.getPassword()) + " 7 " + password,
                    " Wrong Entry", 2);

            PasswordField.setBorder(BorderFactory.createLineBorder(Color.red));
            UserLogIn.lblmsg.setText("Invalid Password!");
            UserLogIn.lblmsg.setForeground(Color.RED);
            UserLogIn.MsgPanel.setBackground(Color.decode("#4169E1"));
            UserLogIn.lblmsg.setFont(new Font("Arial Black", Font.BOLD, 18));
            UserLogIn.lblmsg.setBounds(80, 10, 350, 20);
            UserLogIn.BTNNEXT.setVisible(false);
            UserLogIn.timer.start();

        }
        if (Nametxt.getText().equals(name) && String.valueOf(UserLogIn.
                PasswordField.getPassword()).equals(password)) {

            in.lblmsg.setText("Login Successful!");
            in.lblmsg.setForeground(Color.GREEN);
            in.MsgPanel.setBackground(Color.decode("#4169E1"));
            in.lblmsg.setFont(new Font("Arial Black", Font.BOLD, 16));
            in.lblmsg.setBounds(150, 10, 200, 20);
            UserLogIn.timer.start();
        } else {

            UserLogIn.lblmsg.setText("Invalid UserName or Password!");
            UserLogIn.lblmsg.setForeground(Color.RED);
            UserLogIn.MsgPanel.setBackground(Color.decode("#4169E1"));
            UserLogIn.lblmsg.setFont(new Font("Arial Black", Font.BOLD, 18));
            UserLogIn.lblmsg.setBounds(80, 10, 350, 20);
            UserLogIn.BTNNEXT.setVisible(false);
            UserLogIn.timer.start();

        }

    }
/*    //Method to get UserDetails from the DB for the User Account Form 
/*--------------------------------------------------------------------------*/
    public void getUserInfo() throws SQLException {

        String getUserInfo = "SELECT FName,Mname,Lname,Department from UserDetails, "
                + "UserContacts WHERE BINARY UserDetails.RegNo='" + RegNo + "' "
                + "and BINARY UserContacts.RegNo='" + RegNo + "' ";

        ps = con.prepareStatement(getUserInfo);
        rs = ps.executeQuery();
        if (rs.next()) {

            String fname = rs.getString("Fname");
            String mname = rs.getString("Mname");
            String lname = rs.getString("Lname");
            String Department = rs.getString("Department");

            Init init = new Init();
            init.NameDisplbl.setText(fname + " " + mname + " " + lname);
            init.RegEmpDisplbl.setText(RegNo);
            init.DeptDisplbl.setText(Department);
        }

    }
    //Method to Save User Signup values to the database Signup table
/*----------------------------------------------------------------------*/
    public void UserSignUp(String RegNo, String UserName, String Password,Date SignUpDate,Time SigupTime) {
        try {
            //prepare statement
            String SignUp = "Insert into SignUp values(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(SignUp);
            ps.setString(1, RegNo);
            ps.setString(2, UserName);
            ps.setString(3, Password);
            ps.setDate(4, SignUpDate);
            ps.setTime(5, SigupTime);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Details Saved Succesfully ", "Saved", 1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate", 2);

        }

    }
    //Method to save values in the userDetails table
/*----------------------------------------------------*/
    public void UserDetails(String Reg_EmpNo, String Fname, String Mname, 
            String Lname, String Gender, String s) {
        try {
            //prepare statement
            String UserDetails = "Insert into userDetails values(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(UserDetails);
            ps.setString(1, Reg_EmpNo);
            ps.setString(2, Fname);
            ps.setString(3, Mname);
            ps.setString(4, Lname);
            ps.setString(5, Gender);
            InputStream is = new FileInputStream(s);
            ps.setBinaryStream(6, is);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Details Saved Succesfully ", "Saved", 1);
            is.reset();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate", 2);

        }

    }

         //Method to save values in the userContacts table
    /*-----------------------------------------------------------*/
    public void UserContacts(String Reg_EmpNo, String Email, int Mobile,
            String Department, String Designation, String User_Level) {
        try {
            //prepare statement
            String Contacts = "Insert into userContacts values(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(Contacts);
            ps.setString(1, Reg_EmpNo);
            ps.setString(2, Email);
            ps.setInt(3, Mobile);
            ps.setString(4, Department);
            ps.setString(5, Designation);
            ps.setString(6, User_Level);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Details Saved Succesfully ", "Saved", 1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate", 2);

        }

    }
    //Method to update UserDetails Table as an Admin level User
/*--------------------------------------------------------------------*/
    public void Admin_UserDetailsUpdate(String Reg_EmpNo, String Fname, String Mname,
            String Lname, String Gender, String s, String RegEmpNo) {
        {
            try {

                String AdminDetailsUpdate = "Update table userDetails Set Name=?,Email=?,Mobile=?,"
                        + "Department=?,Designation=?,where Reg_EmpNo=" + RegEmpNo + "";

                PreparedStatement ps = con.prepareStatement(AdminDetailsUpdate);
                ps.setString(1, Reg_EmpNo);
                ps.setString(2, Fname);
                ps.setString(3, Mname);
                ps.setString(4, Lname);
                ps.setString(5, Gender);
                InputStream is = new FileInputStream(s);
                ps.setBinaryStream(6, is);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Details Updated Succesfully "
                        + "", "Admin Update Account", 1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate", 2);

            }

        }
    }
    //Method to update UserContacts Table as an Admin level User
/*----------------------------------------------------------------*/
    public void Admin_UserContactsUpdate(String Reg_EmpNo, String Email, int Mobile,
            String Department, String Designation, String UserLevel, String RegEmpNo) {
        try {
            //prepare statement
            String AdminContactsUpdate = "Update table userDetails Set RegNo=?,Email=?,Mobile=?,"
                    + "Department=?,Designation=?,UserLevel=?,where Reg_EmpNo=" + RegEmpNo + "";

            PreparedStatement ps = con.prepareStatement(AdminContactsUpdate);
            ps.setString(1, Reg_EmpNo);
            ps.setString(2, Email);
            ps.setInt(3, Mobile);
            ps.setString(4, Department);
            ps.setString(5, Designation);
            ps.setString(6, UserLevel);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "User Contacts Update Saved "
                    + "Succesfully ", "Admin Contacts Update", 1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate", 2);

        }

    }
    //Method to update UserDetails Table as an User level User
/*----------------------------------------------------------------*/
    public void UserDetailsUpdate(String Fname, String Mname, String Lname, 
            String s, String Reg_EmpNo) {
        try {
            //prepare stateme t
            String DetailsUpdate = "Update table userDetails Set Fname=?,Mname=?,Lname=?,"
                    + "Photo=? where Reg_EmpNo=" + Reg_EmpNo + "";

            PreparedStatement ps = con.prepareStatement(DetailsUpdate);
            ps.setString(2, Fname);
            ps.setString(3, Mname);
            ps.setString(4, Lname);
            InputStream is = new FileInputStream(s);
            ps.setBlob(6, is);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Details Updated Succesfully ", 
                    "User Update UserDetails", 1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate", 2);

        }

    }
       //Method to update UserContacts Table as a User level User
/*----------------------------------------------------------------------*/
    public void UserContactsUpdate(String Email, int Mobile, String Reg_EmpNo) {
        try {
            //prepare stateme t
            String ContactsUpdate = "Update table userContacts Set Email=?,Mobile=? where "
                    + "Reg_EmpNo=" + Reg_EmpNo + "";

            PreparedStatement ps = con.prepareStatement(ContactsUpdate);
            ps.setString(2, Email);
            ps.setInt(3, Mobile);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Contacts Updated Succesfully ",
                    "Update UserContacts", 1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate", 2);

        }

    }

          //METHOD ONE TO POPULATE COMBOBOX ITEMS FROM THE DATABASE 
    /*==================================================================*/
    public Object[] PopRegEmpNocmb() throws SQLException {

        //QUERY DATABASE RegNo/Emp
        //-------------------------------------------------
        String qryEmail = "SELECT RegNo from UserDetails";
        stmt = con.createStatement();
        rs = stmt.executeQuery(qryEmail);
        String[] cmbitems = null;
        //GET RegEmpNo LIST FROM DATABASE FOR REGEMPNO COMBOBOX
        //--------------------------------------------------------
        try {
            while (rs.next()) {
                ArrayList<String> RegEmp = new ArrayList<String>();
                RegEmp.add(rs.getString(1));
                
                Iterator<String> items=RegEmp.iterator();
                while(items.hasNext()){
                    cmbitems = new String[]{items.toString()};
                }
                System.out.print(cmbitems);
                JOptionPane.showMessageDialog(null, "" + RegEmp + " ", "Update Account", 1);
                
                //cmbitems = new Object[]{RegEmp};
                // Signup.RegNocmb.addItem(RegEmp);

                obj = Signup.RegNocmb.getSelectedObjects();

            }
        } catch (SQLException ex) {
            Logger.getLogger(InputVerification.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmbitems;
    }
}
