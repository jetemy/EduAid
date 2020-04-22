/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserMngtUI;

/**
 *
 * @author USER
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.Timer;

 public  class DigitalClock extends JPanel {

    public String stringTime;
    public int hour, minute, second;

    String correctionHour = "";
    String correctionMinute = "";
    String correctionSecond = "";

    public void setStringTime(String xyz) {
        this.stringTime = xyz;
    }

     public  DigitalClock() {

        Timer t1 = new Timer(1000, (ActionEvent e) -> {
            repaint();
        });
        t1.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Calendar now = Calendar.getInstance();
        hour = now.get(Calendar.HOUR_OF_DAY);
        minute = now.get(Calendar.MINUTE);
        second = now.get(Calendar.SECOND);

        if (hour < 10) {
            this.correctionHour = "0";
        }
        if (hour >= 10) {
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
        setStringTime(correctionHour + hour + ":" + correctionMinute + minute + ":" + correctionSecond + second);
            g.setColor(Color.BLACK);
            Font myFont = new Font("SansSerif", Font.PLAIN, 20);
            g.setFont(myFont);
            g.setColor(Color.decode("#191970"));
            g.drawString(stringTime,750,75);

        }

   }

