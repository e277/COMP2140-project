import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportMenu extends JFrame {
    private JButton cmdGenerateReport;
    private JButton cmdCloseReport;
    
    public ReportMenu() {
        setTitle("Report Menu");
        setSize(1200, 450);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        cmdGenerateReport = new JButton("Generate Report");
        cmdCloseReport = new JButton("Close Report Menu");

        panel.add(cmdGenerateReport);
        panel.add(cmdCloseReport);

        cmdGenerateReport.addActionListener(new GenerateReportListener());
        cmdCloseReport.addActionListener(new CloseReportMenuListener());

        add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class GenerateReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logic to generate the report
            JOptionPane.showMessageDialog(null, "Report generats successfully");
        }
    }

    private class CloseReportMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the report menu
        }
    }
}
