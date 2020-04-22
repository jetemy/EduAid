package UserMngtUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class SwingWorkerProgress {

    public static void main(String[] args) {
        new SwingWorkerProgress();
    }

    public SwingWorkerProgress() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.setSize(150, 100);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JProgressBar pbProgress;
        private JButton start;

        public TestPane() {

            setBorder(new EmptyBorder(10, 10, 10, 10));
            pbProgress = new JProgressBar();
            pbProgress.setSize(120, 1);
            pbProgress.setBackground(Color.RED);
            setSize(150, 100);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(4, 4, 4, 4);
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(pbProgress, gbc);
            setVisible(true);
            

            start = new JButton("Start");
            gbc.gridy++;
            add(start, gbc);
            

            start.addActionListener((ActionEvent e) -> {
                start.setEnabled(false);
                ProgressWorker pw = new ProgressWorker();
                pw.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                    String name1 = evt.getPropertyName();
                    if (name1.equals("progress")) {
                        int progress = (int) evt.getNewValue();
                        pbProgress.setValue(progress);
                        repaint();
                    } else if (name1.equals("state")) {
                        SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                        switch (state) {
                            case DONE:
                                start.setEnabled(true);
                                break;
                        }
                    }
                });
                pw.execute();
            });

        }
    }

    public class ProgressWorker extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            int i = 0;
            int max = 2000;

            while (i < max) {
                i += 10;
                int progress = Math.round(((float) i / (float) max) * 100f);
                setProgress(progress);
                try {
                    Thread.sleep(25);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}
