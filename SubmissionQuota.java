import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmissionQuota extends JFrame {
    private int submissionQuota = 5; // Assign a fixed submission quota for each student
    private int submissionsMade = 0;

    public SubmissionQuota() {
        setTitle("Submission Quota");
        setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton cmdCheckQuota = new JButton("Check Submission Quota");
        JButton cmdCloseQuotaMenu = new JButton("Close Submission Quota Menu");

        panel.add(cmdCheckQuota);
        panel.add(cmdCloseQuotaMenu);

        cmdCheckQuota.addActionListener(new CheckQuotaListener());
        cmdCloseQuotaMenu.addActionListener(new CloseQuotaMenuListener());

        add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class CheckQuotaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logic to check submission quota
            if (submissionsMade < submissionQuota) {
                JOptionPane.showMessageDialog(null, "You have " + (submissionQuota - submissionsMade) +
                        " submissions remaining out of " + submissionQuota);
            } else {
                JOptionPane.showMessageDialog(null, "You have reached your submission quota.");
            }
        }
    }

    private class CloseQuotaMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the submission quota menu
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SubmissionQuota();
            }
        });
    }
}
