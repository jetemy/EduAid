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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
 
public class SplitPaneExp extends JFrame {
     
    public SplitPaneExp() {
         
        setTitle("Example of Split Pane");
        setSize(150, 150);
         
        JPanel jsp1 = new JPanel();
        JPanel jsp2 = new JPanel();
        JLabel j1 = new JLabel("Area 1");
        JLabel j2 = new JLabel("Area 2");
         
        jsp1.add(j1);
        jsp2.add(j2);
         
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
                true, jsp1, jsp2);
         
        splitPane.setOneTouchExpandable(true);
        getContentPane().add(splitPane);
         
    }
    public static void main(String[] args) {
         
        SplitPaneExp sp = new SplitPaneExp();
        sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sp.setVisible(true);
         
    }
}