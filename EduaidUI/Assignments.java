/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EduaidUI;

import Functions.DBConUserMngt;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Oscar
 */
public class Assignments {

    private JLabel selectlbl, LvlOStudy, YrOStdylbl, courselbl, schoollbl, Deptlbl, unitlbl, periodlbl,
            startlbl, SelSchoollbl, SubmissionDatelbl,SearchSlctnlbl,
            SelSchooldisplbl, SelCoursedisplbl;
    private JComboBox<String> coursecmb, coursecmb1, LvlOStdycmb, YrOStdycmb, Contentcmb,
            Deptcmb, unitcmb, schoolcmb,Num_of_Days;
    private JRadioButton Upload, Download, Date, Number_of_Days;
    private JSeparator sep1, sep2, sep3;
    private ButtonGroup bg, bg2;
    private JButton Searchbtn;
    private JDateChooser SubmissionDate, SearchByDate;
    private String Course[];
    private String Content[] = {"Select Upload Content", "Assignment 1", "Assignment 2",
        "CAT1", " CAT2", "Concept Paper", "Electronic Book", "Lecture notes ",
        "Project", "Proposal", "Report", "Revision Paper", "Term Paper", " Thesis"};

    private String Yos[] = {"Select Year of Study", "Year 1", "Year 2", "Year 3", "Year 4", "Year 5"};
    private String Los[] = {"Select Level of Study", "Certificate", "Diploma",
        "Undergraduate", "Masters", "Doctor of Philosophy "};
    private final String School[] = {"Select School/Faculty", "School Of Business & Economics", "School Of Education & Social Sciences",
        "Center For Disaster Management And Humanitarian Asssistance", "School of Nursing MidWifery",
        "School Of Public Health,Biomedical Sciences & Technology", "Faculty Of Science", "School of Computing & Informatics",
        "Faculty of Engineering", "School of Agriculture,Veterinary & Tenchology"};
    private final String[] numbers={"0-10","10-20","20-30","30-40","40-50","50-60",
        "60-70","70-80","80-90","90-100","100-110","110-120"};
    private String Unit, A1, A2, A3, A4;
    public JPanel panel, panel2, treepanel;
    private JTree asignTree;
    private JScrollPane scrlpane;
    private JDateChooser datePicker;

    public Assignments() throws SQLException {
        //Set panel properties
        panel = new JPanel();
        panel.setBounds(0, 0, 750, 530);
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBackground(Color.decode("#4B0082"));
        panel.setVisible(false);
        panel.setLayout(null);

        panel2 = new JPanel();
        panel2.setBounds(250, 95, 200, 90);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.
                createLineBorder(Color.decode("#8A2BE2"), 1), "Select Search Creterion", 1, 1,
                Font.decode(A1), Color.WHITE));
        panel2.setBackground(Color.decode("#4B0082"));
        panel2.setVisible(false);
        panel2.setLayout(null);

        treepanel = new JPanel();
        treepanel.setBounds(250, 210, 250, 240);
        treepanel.setBorder(BorderFactory.createLineBorder(Color.decode("#4B0082")));
        treepanel.setBackground(Color.decode("#8A2BE2"));
        treepanel.setVisible(false);
        treepanel.setLayout(null);
        //Create Course Collections
        ArrayList<String> BsnsCourses = new ArrayList<String>();
        BsnsCourses.add("PhD in Business Administration.Strategic Management");
        BsnsCourses.add("PhD in Business Administration.Entreprenuership");
        BsnsCourses.add("PhD in Business Administration.Human Resource Management");
        BsnsCourses.add("PhD in Business Administration.Finace");
        BsnsCourses.add("Master of Business Administration.Accounting");
        BsnsCourses.add("Master of Business Administration.Finance");
        BsnsCourses.add("Master of Business Administration.HRM");
        BsnsCourses.add("Master of Business Administration.Strategi Mgt");
        BsnsCourses.add("Master of Business Administration.Logistics & Supply Chain Mgt");
        BsnsCourses.add("Master of Science in Human Resource Management");
        BsnsCourses.add("Master of Arts in Economics");
        BsnsCourses.add("Bachelor of Science in Economics");
        BsnsCourses.add("Bachelor of Economics");
        BsnsCourses.add("Diploma in Human Resource Management");
        BsnsCourses.add("Diploma in Finance & Banking");
        BsnsCourses.add("Diploma in Business Administration");
        BsnsCourses.add("Diploma in Accounting");
        BsnsCourses.add("Diploma in Marketing");
        BsnsCourses.add("Diploma in Entreprenuership");
        BsnsCourses.add("Diploma in Purchasing & Supplies");
        BsnsCourses.add("Diploma in Risk & Insurance");
        BsnsCourses.add("Certificate in Human Resource Management");
        BsnsCourses.add("Certificate in Business Administration");
        BsnsCourses.add("Certificate in Accounting");
        BsnsCourses.add("Certificate in Marketing");
        BsnsCourses.add("Certificate in Entreprenuership");
        BsnsCourses.add("Certificate in Purchasing & Supplies");
        BsnsCourses.add("Certificate in Risk & Insurance");

        ArrayList<String> CompCourses = new ArrayList<String>();
        CompCourses.add("PhD in Information Technology");
        CompCourses.add("PhD in Computer Science");
        CompCourses.add("Masters in Information Technology");
        CompCourses.add("Masters in Computer Science");
        CompCourses.add("Masters in Information Systems");
        CompCourses.add("Bachelor of Science in Computer Science");
        CompCourses.add("Bachelor of Science in Information Technology");
        CompCourses.add("Bachelor of Information Systems & Knowledge Mgt");
        CompCourses.add("Diploma in Information Technology");
        CompCourses.add("Diploma in Technology in Computer Systems");
        CompCourses.add("Diploma in Software Development");
        CompCourses.add("Certificate in Information Technology");

        DBConUserMngt DB = new DBConUserMngt();

        //Create Lables
        selectlbl = new JLabel("SELECT");
        selectlbl.setBounds(70, 30, 100, 30);
        selectlbl.setFont(new Font("ARIAL ", Font.BOLD, 20));
        selectlbl.setForeground(Color.YELLOW);

        LvlOStudy = new JLabel("LEVEL OF STUDY");
        LvlOStudy.setBounds(50, 70, 130, 20);
        LvlOStudy.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        LvlOStudy.setForeground(Color.WHITE);

        YrOStdylbl = new JLabel("YEAR OF STUDY");
        YrOStdylbl.setBounds(50, 130, 130, 20);
        YrOStdylbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        YrOStdylbl.setForeground(Color.WHITE);

        schoollbl = new JLabel("SCHOOL/FACULTY");
        schoollbl.setBounds(50, 190, 130, 20);
        schoollbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        schoollbl.setForeground(Color.WHITE);

        Deptlbl = new JLabel("  DEPARTMENT");
        Deptlbl.setBounds(50, 250, 130, 20);
        Deptlbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        Deptlbl.setForeground(Color.WHITE);

        courselbl = new JLabel("COURSE NAME  ");
        courselbl.setBounds(50, 310, 130, 20);
        courselbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        courselbl.setForeground(Color.WHITE);

        unitlbl = new JLabel("      CODE");
        unitlbl.setBounds(50, 370, 100, 20);
        unitlbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        unitlbl.setForeground(Color.WHITE);

        periodlbl = new JLabel();
        periodlbl.setText("    CONTENT");
        periodlbl.setBounds(50, 430, 100, 20);
        periodlbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        periodlbl.setForeground(Color.WHITE);

        startlbl = new JLabel("Start");
        startlbl.setBounds(5, 455, 30, 30);//
        startlbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        startlbl.setForeground(Color.WHITE);

        SubmissionDatelbl = new JLabel("Choose Submission Date ");
        SubmissionDatelbl.setBounds(278, 90, 250, 30);
        SubmissionDatelbl.setFont(new Font("ARIAL ", Font.BOLD, 18));
        SubmissionDatelbl.setForeground(Color.WHITE);
        SubmissionDatelbl.setVisible(false);

        SelSchoollbl = new JLabel("Posting duration");
        SelSchoollbl.setBounds(240, 90, 300, 30);
        SelSchoollbl.setFont(new Font("ARIAL ", Font.PLAIN, 18));
        SelSchoollbl.setForeground(Color.WHITE);

        SearchSlctnlbl= new JLabel();
        SearchSlctnlbl.setBounds(475, 110, 200, 20);
        SearchSlctnlbl.setFont(new Font("ARIAL ", Font.PLAIN, 18));
        SearchSlctnlbl.setForeground(Color.WHITE);
        SearchSlctnlbl.setVisible(false);

        //Create Date Chooser
        SubmissionDate = new JDateChooser();
        SubmissionDate.setBounds(510, 93, 180, 25);//
        SubmissionDate.setFont(new Font("ARIAL ", Font.PLAIN, 12));
        SubmissionDate.setForeground(Color.WHITE);
        SubmissionDate.setVisible(false);

        SearchByDate = new JDateChooser();
        SearchByDate.setBounds(470, 145, 200, 25);//
        SearchByDate.setFont(new Font("ARIAL ", Font.PLAIN, 12));
        SearchByDate.setForeground(Color.WHITE);
        SearchByDate.setVisible(false);

        //create JComboBoxes
        Num_of_Days=new JComboBox<>(numbers);
        Num_of_Days.setBounds(500, 145, 80, 30);
        Num_of_Days.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        Num_of_Days.setEditable(true);
        Num_of_Days.setVisible(false);
        
        
        LvlOStdycmb = new JComboBox(Los);
        LvlOStdycmb.setBounds(20, 95, 180, 30);
        LvlOStdycmb.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        LvlOStdycmb.setEditable(false);

        YrOStdycmb = new JComboBox(Yos);
        YrOStdycmb.setBounds(20, 155, 180, 30);
        YrOStdycmb.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        YrOStdycmb.setEditable(false);

        schoolcmb = new JComboBox<>(School);
        schoolcmb.setBounds(20, 215, 180, 30);
        schoolcmb.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        schoolcmb.setEditable(false);
        schoolcmb.addActionListener((ActionEvent ea) -> {
            int sch = schoolcmb.getSelectedIndex();
            switch (sch) {
                case 1:
                    coursecmb.setVisible(false);
                    Stream<String> Strmbsns = BsnsCourses.stream().sorted().filter((n) -> n.contains("PhD"));
                    Course = Strmbsns.toArray(String[]::new);
                    coursecmb1 = new JComboBox<>();
                    coursecmb1.setBounds(20, 275, 180, 30);
                    coursecmb1.setFont(new Font("ARIAL ", Font.PLAIN, 14));
                    coursecmb1.setEditable(false);
                    panel.add(coursecmb1);

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    coursecmb1.setVisible(false);
                    coursecmb.setVisible(true);

            }

        });

        Deptcmb = new JComboBox<>();
        Deptcmb = new JComboBox<>();
        Deptcmb.setBounds(20, 275, 180, 30);
        Deptcmb.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        Deptcmb.setEditable(false);

        coursecmb = new JComboBox<>();
        coursecmb.setBounds(20, 335, 180, 30);
        coursecmb.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        coursecmb.setEditable(false);
        coursecmb.addActionListener((ActionEvent ea) -> {
            if (schoolcmb.getSelectedIndex() > 0) {
                SelSchooldisplbl.setText(schoolcmb.getSelectedItem().toString());
                SelSchooldisplbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
                SelSchooldisplbl.setForeground(Color.YELLOW);
            } else {
                SelSchooldisplbl.setText("");
                SelSchooldisplbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
                SelSchooldisplbl.setForeground(Color.YELLOW);
            }
            if (coursecmb.getSelectedIndex() > 0 && schoolcmb.getSelectedIndex() < 1) {
                SelCoursedisplbl.setText("");
                SelCoursedisplbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
                SelCoursedisplbl.setForeground(Color.YELLOW);
            } else if (coursecmb.getSelectedIndex() < 1) {
                SelCoursedisplbl.setText("");
                SelCoursedisplbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
                SelCoursedisplbl.setForeground(Color.YELLOW);
            } else {
                SelCoursedisplbl.setText(coursecmb.getSelectedItem().toString());
                SelCoursedisplbl.setFont(new Font("ARIAL ", Font.PLAIN, 14));
                SelCoursedisplbl.setForeground(Color.YELLOW);
            }
        });

        unitcmb = new JComboBox<>();
        unitcmb.setBounds(20, 395, 180, 30);
        unitcmb.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        unitcmb.setEditable(false);

        Contentcmb = new JComboBox<>(Content);
        Contentcmb.setBounds(20, 455, 180, 30);//
        Contentcmb.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        Contentcmb.setForeground(Color.WHITE);

        //Create JSeparator
        sep1 = new JSeparator(SwingConstants.VERTICAL);
        sep1.setBackground(Color.decode("#8A2BE2"));
        sep1.setLocation(220, 50);
        sep1.setSize(2, 450);

        sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setBackground(Color.decode("#8A2BE2"));
        sep2.setLocation(250, 80);
        sep2.setSize(480, 2);

        sep3 = new JSeparator(SwingConstants.HORIZONTAL);
        sep3.setBackground(Color.decode("#8A2BE2"));
        sep3.setLocation(250, 200);
        sep3.setSize(480, 2);
        sep3.setVisible(false);

        //Create JRadioButtons
        Download = new JRadioButton("Download Content");
        Download.setBounds(500, 30, 230, 50);
        Download.setFont(new Font("ARIAL ", Font.PLAIN, 20));
        Download.setForeground(Color.YELLOW);
        Download.setBackground(Color.decode("#4B0082"));
        Download.addActionListener((ActionEvent ae) -> {
            SubmissionDatelbl.setVisible(false);
            Upload.setForeground(Color.YELLOW);
            Download.setForeground(Color.GREEN);

            startlbl.setVisible(true);

            panel2.setVisible(true);
            Contentcmb.setVisible(true);
            Searchbtn.setText("Search");
            SubmissionDate.setVisible(false);
            periodlbl.setText("    CONTENT");
            startlbl.setVisible(false);
            Contentcmb.setVisible(true);
            sep3.setVisible(true);

        });

        Upload = new JRadioButton("Upload Content");
        Upload.setBounds(300, 30, 200, 50);
        Upload.setFont(new Font("ARIAL ", Font.PLAIN, 20));
        Upload.setForeground(Color.YELLOW);
        Upload.setBackground(Color.decode("#4B0082"));
        Upload.addActionListener((ActionEvent ae) -> {
            SubmissionDatelbl.setVisible(true);
            Download.setForeground(Color.YELLOW);
            Upload.setForeground(Color.GREEN);
            treepanel.setVisible(false);
            periodlbl.setText("    CONTENT");
            startlbl.setVisible(false);
            panel2.setVisible(false);
            Contentcmb.setVisible(true);
            Searchbtn.setText("UPLOAD");
            sep3.setVisible(false);
            SubmissionDate.setVisible(true);
            SearchByDate.setVisible(false);
            Num_of_Days.setVisible(false);
             SearchSlctnlbl.setVisible(false);
        });
        Date = new JRadioButton("By Date");
        Date.setBounds(10, 25, 100, 25);
        Date.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        Date.setForeground(Color.YELLOW);
        Date.setBackground(Color.decode("#4B0082"));
        Date.addActionListener((ActionEvent ae) -> {
            Date.setForeground(Color.GREEN);
            Number_of_Days.setForeground(Color.YELLOW);
            SearchByDate.setVisible(true);
            Num_of_Days.setVisible(false);
            SearchSlctnlbl.setVisible(true);
            SearchSlctnlbl.setText("Select Date");
        });

        Number_of_Days = new JRadioButton("By Number of Days");
        Number_of_Days.setBounds(10, 55, 250, 25);
        Number_of_Days.setFont(new Font("ARIAL ", Font.PLAIN, 14));
        Number_of_Days.setForeground(Color.YELLOW);
        Number_of_Days.setBackground(Color.decode("#4B0082"));
        Number_of_Days.addActionListener((ActionEvent ae) -> {
            Number_of_Days.setForeground(Color.GREEN);
            Date.setForeground(Color.YELLOW);
            Num_of_Days.setVisible(true);
            SearchByDate.setVisible(false);
            SearchSlctnlbl.setVisible(true);
            SearchSlctnlbl.setText("Select/Enter Number");
        });

        bg = new ButtonGroup();
        bg.add(Upload);
        bg.add(Download);

        bg2 = new ButtonGroup();
        bg2.add(Date);
        bg2.add(Number_of_Days);
        panel2.add(Date);
        panel2.add(Number_of_Days);

        panel.add(Upload);
        panel.add(Download);
        panel.add(panel2);

        //Create search Button
        Searchbtn = new JButton("Search");
        Searchbtn.setBounds(430, 470, 100, 30);
        Searchbtn.setForeground(Color.WHITE);
        Searchbtn.setBackground(Color.decode("#8A2BE2"));
        Searchbtn.addActionListener((ActionEvent ae) -> {
            if (Searchbtn.getText() == "Search") {
                treepanel.setVisible(true);
            } else {
                treepanel.setVisible(false);
            }
        });

        // Create top node of tree.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Assignments");
        // Create subtree of "A".
        DefaultMutableTreeNode a = new DefaultMutableTreeNode("Unit");
        top.add(a);
        DefaultMutableTreeNode a1 = new DefaultMutableTreeNode("A1");
        a.add(a1);
        DefaultMutableTreeNode a2 = new DefaultMutableTreeNode("A2");
        a.add(a2);
        DefaultMutableTreeNode a3 = new DefaultMutableTreeNode("A3");
        a.add(a3);
        DefaultMutableTreeNode a4 = new DefaultMutableTreeNode("A4");
        a.add(a4);
        // Create subtree of "B"
        DefaultMutableTreeNode b = new DefaultMutableTreeNode("B");
        top.add(b);
        DefaultMutableTreeNode b1 = new DefaultMutableTreeNode("B1");
        b.add(b1);
        DefaultMutableTreeNode b2 = new DefaultMutableTreeNode("B2");
        b.add(b2);
        DefaultMutableTreeNode b3 = new DefaultMutableTreeNode("B3");
        b.add(b3);
        DefaultMutableTreeNode b4 = new DefaultMutableTreeNode("B4");
        b.add(b4);
        DefaultMutableTreeNode b5 = new DefaultMutableTreeNode("B5");
        b.add(b5);
        DefaultMutableTreeNode b6 = new DefaultMutableTreeNode("B6");
        b.add(b6);
        DefaultMutableTreeNode b7 = new DefaultMutableTreeNode("B7");
        b.add(b7);
        DefaultMutableTreeNode b8 = new DefaultMutableTreeNode("B8");
        b.add(b8);
        DefaultMutableTreeNode b9 = new DefaultMutableTreeNode("B9");
        b.add(b9);

//create FileTree
        asignTree = new JTree(top);
        asignTree.setLocation(10, 5);
        asignTree.addTreeSelectionListener((TreeSelectionEvent tse) -> {

            if (JOptionPane.showConfirmDialog(null, tse.getPath(), "DownLoad File?",
                    JOptionPane.OK_CANCEL_OPTION)
                    == JOptionPane.OK_OPTION) {

            } else {
                System.gc();
            }

        });

        //create JScrollPane
        Box box = Box.createVerticalBox();
        box.setBackground(Color.decode("#8A2BE2"));
        box.setBounds(2, 0, 248, 240);
        box.add(new JScrollPane(asignTree));

        treepanel.add(box);

        panel.add(selectlbl);
        panel.add(unitlbl);
        panel.add(courselbl);
        panel.add(LvlOStdycmb);
        panel.add(YrOStdylbl);
        panel.add(coursecmb);
        panel.add(Deptlbl);
        panel.add(Deptcmb);
        panel.add(YrOStdycmb);
        panel.add(Contentcmb);
        panel.add(SubmissionDatelbl);
        panel.add(SubmissionDate);
        panel.add(SearchByDate);
        panel.add(Num_of_Days);
        panel.add(SearchSlctnlbl);
        panel.add(unitcmb);
        panel.add(periodlbl);
        panel.add(LvlOStudy);
        panel.add(Searchbtn);
        panel.add(schoolcmb);
        panel.add(schoollbl);
        panel.add(treepanel);
        panel.add(sep1);
        panel.add(sep2);
        panel.add(sep3);


        /*  add(panel);
        setBounds(0,0,800, 600);
        setVisible(true);*/
    }

    /*public static void main(String[] args) throws SQLException{
        Assignments ass=new Assignments();
        
    }*/
}
