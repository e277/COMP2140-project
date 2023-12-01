import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThesisSubmission extends JPanel {
    private JButton cmdClose;
    private JButton cmdSubmit;
    private JButton cmdUpdate;
    private JPanel panelCommand;
    private JPanel panelDisplay;
    private ThesisSubmission thesisSub;
    private JButton cmdReportMenu; // report menu button

    /**
     * The Application Constructor that will be used to create the home GUI
     */
    public ThesisSubmission() {

        panelCommand = new JPanel();
        panelDisplay = new JPanel();

        setBackground(Color.WHITE);

        // to display Application Header

        ImageIcon icon1 = new ImageIcon("uwicrest.jpg");
        Image logoImage = icon1.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(logoImage);
        JLabel logo = new JLabel(scaledLogo, SwingConstants.CENTER);

        ImageIcon icon2 = new ImageIcon("thesissubmission.jpeg");
        Image titleImage = icon2.getImage().getScaledInstance(500, 280, Image.SCALE_SMOOTH);
        ImageIcon scaledtitle = new ImageIcon(titleImage);
        JLabel title = new JLabel(scaledtitle, SwingConstants.CENTER);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel header = new JLabel("   THE UNIVERSITY OF THE WEST INDIES, MONA CAMPUS", SwingConstants.CENTER);
        header.setFont(new Font("Georgia", Font.BOLD, 36));
        header.setBackground(Color.RED);
        header.setForeground(Color.WHITE);

        // to group the crest and the school's name so it appears in one row
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalGlue());
        hBox.add(logo);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(header);
        hBox.add(Box.createHorizontalGlue());
        hBox.setBackground(Color.RED);
        hBox.setOpaque(true);
        panelDisplay.add(hBox);

        JLabel subHeader = new JLabel("THESIS FORM SUBMISSION PORTAL", SwingConstants.CENTER);
        subHeader.setFont(new Font("Georgia", Font.BOLD, 40));
        subHeader.setAlignmentX(CENTER_ALIGNMENT);

        panelCommand.setBackground(Color.WHITE);
        panelDisplay.setLayout(new GridLayout(1, 2));

        // command buttons
        cmdClose = new JButton("Close");
        cmdSubmit = new JButton("Submit");
        cmdUpdate = new JButton("Update");

        cmdReportMenu = new JButton("Report Menu"); // report menu button

        // colour of buttons
        cmdSubmit.setBackground(Color.CYAN);
        
        cmdClose.setBackground(Color.RED);
        cmdClose.setForeground(Color.WHITE);

        cmdUpdate.setBackground(Color.YELLOW);
        cmdReportMenu.setBackground(Color.GREEN); // report menu button
       

        panelCommand.add(cmdSubmit);
        panelCommand.add(cmdUpdate);
        panelCommand.add(cmdReportMenu); // report menu button
        panelCommand.add(cmdClose);
        panelCommand.setAlignmentX(CENTER_ALIGNMENT);

        cmdClose.addActionListener(new closeButtonListener());
        cmdSubmit.addActionListener(new submitButtonListener());
        cmdUpdate.addActionListener(new submitButtonListener());
        cmdReportMenu.addActionListener(new ReportMenuButtonListener()); // report menu button

        add(panelDisplay, BorderLayout.CENTER);

        // to group the second header, picture, and the command buttons so it aligns better on the
        // frame
        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalGlue());
        vBox.add(subHeader);
        vBox.add(Box.createVerticalGlue());
        vBox.add(title);
        vBox.add(Box.createVerticalGlue());
        vBox.add(panelCommand);
        vBox.setAlignmentX(CENTER_ALIGNMENT);

        setPreferredSize(new Dimension(1200, 450));

        add(vBox, BorderLayout.SOUTH);
        setVisible(true);

        thesisSub = this;
    }

    /**
     * The function creates and displays a JFrame for a thesis form submission system.
     */
    public static void showForm() {
        JFrame frame = new JFrame("Thesis Form Submission Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 1200;
        int height = 450;
        frame.setBounds(center.x - width / 2, center.y - height / 2, width, height);
        ThesisSubmission thesisSub = new ThesisSubmission();
        // app.setOpaque(true);
        frame.setContentPane(thesisSub);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * The main function uses SwingUtilities to run the showForm function on
     * the event dispatch
     * thread.
     */
    public static void main(String[] args) {    
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showForm();
            }
        });
    }

    /**
     * The closeButtonListener class is an ActionListener that exits the program
     * when the close button
     * is clicked.
     * 
     */
    private class closeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * The submitButtonListener class creates an instance of either the Submit or
     * Update class based on
     * the source of the ActionEvent.
     */
    private class submitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent g) {
            if (g.getSource() == cmdSubmit) {
                StudentPrompt user = new StudentPrompt(thesisSub);
            } else {
                JOptionPane.showMessageDialog(null, "Updated", "Thesis Status", JOptionPane.DEFAULT_OPTION);
            }
        }

    }

    /**
     * The ReportMenuButtonListener class creates an instance of the ReportMenu
     * class when the report menu button is clicked.
     */
    private class ReportMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open the Report Menu
            new ReportMenu(null, null);
        }
    }

}
