
import javax.swing.*;
import javax.swing.text.Style;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class UserLogin extends JFrame {
    private String fname, lname, password;
    private int ID;
    private JTextField txtPassword, txtID, txtName;
    private JButton cmdCancel;
    private JButton cmdSubmit;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;
    private UserLogin thisForm;
    private ArrayList<Integer> idList;
    private ThesisSubmission submission;

    /**
     * The AdminLogin Constructor that creates a GUI for the admin login
     * 
     * @param app
     */
    public UserLogin(ThesisSubmission thesisSub) {
        submission =thesisSub;
        idList = loadIDs("ID.txt");
        thisForm = this;
        setTitle("ID Number");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 650;
        int height = 400;
        setBounds(center.x - width / 2, center.y - height / 2, width, height);

        /*
         * JLabel apHeader = new JLabel("User",
         * SwingConstants.CENTER);
         * apHeader.setFont(new Font("Georgia", Font.BOLD, 18));
         * apHeader.setAlignmentX(CENTER_ALIGNMENT);
         * apHeader.setPreferredSize(new Dimension(720, 20));
         * pnlDisplay.add(apHeader);
         */

        /*
         * pnlDisplay.add(new JLabel("Name:"));
         * txtName = new JTextField(36);
         * pnlDisplay.add(txtName);
         */

        pnlDisplay.add(new JLabel("Enter your ID number: "));
        txtID = new JTextField(12);
        pnlDisplay.add(txtID);

        /*
         * pnlDisplay.add(new JLabel("Password:"));
         * txtPassword = new JTextField(10);
         * pnlDisplay.add(txtPassword);
         */

        cmdCancel = new JButton("Cancel");
        cmdSubmit = new JButton("Submit");

        // set colour for buttons
        cmdSubmit.setBackground(Color.GREEN);
        cmdSubmit.setForeground(Color.WHITE);

        cmdCancel.setBackground(Color.RED);
        cmdCancel.setForeground(Color.WHITE);

        pnlCommand.add(cmdCancel);
        pnlCommand.add(cmdSubmit);

        setPreferredSize(new Dimension(700, 400));
        pnlDisplay.setLayout(new GridLayout(8, 1));

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());
    }

    /**
     * The loadPasswords Method
     * 
     * @param pfile
     * @return
     */
    /*
     * private ArrayList<Admin> loadPasswords(String pfile) {
     * Scanner scan;
     * ArrayList<Admin> admns = new ArrayList<Admin>();
     * try {
     * scan = new Scanner(new File(pfile));
     * while (scan.hasNext()) {
     * String[] pw = scan.nextLine().split(" ");
     * String fname = pw[0];
     * String lname = pw[1];
     * int ID = Integer.parseInt(pw[2]);
     * String pword = pw[3];
     * Admin admin = new Admin(fname, lname, ID, pword);
     * admns.add(admin);
     * }
     * } catch (FileNotFoundException fe) {
     * System.out.println("Password file not found");
     * }
     * return admns;
     * }
     */

    private ArrayList<Integer> loadIDs(String idFile) {
        Scanner scan;
        ArrayList<Integer> idList = new ArrayList<Integer>();
        try {
            scan = new Scanner(new File(idFile));
            while (scan.hasNext()) {
                int id = Integer.parseInt(scan.nextLine());
                System.out.println(id);
                idList.add(id);
            }
            return idList;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return idList;
    }

    /**
     * The function checks if an admin is admitted by comparing their first name,
     * last name, ID, and
     * password to those of existing admins.
     * 
     * @param admin The parameter "admin" is an object of the class "Admin", which
     *              contains information
     *              about an administrator such as their first name, last name, ID,
     *              and password. The method
     *              "isAdmitted" checks if this admin is present in a list of admins
     *              and returns a boolean value
     *              indicating whether the
     * @return A boolean value is being returned, which indicates whether the given
     *         Admin object is
     *         admitted or not based on whether it matches with any of the Admin
     *         objects in the admins list.
     */
    public void AddIds(ArrayList<Integer> idList) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("ID.txt"));
            for (Integer r : idList) {
                out.println(r);
            }
            out.close();
        } catch (FileNotFoundException fe) {
            System.out.print(fe.getMessage());
        }
    }

    /**
     * The SubmitButtonListener class checks if an admin is admitted and displays a
     * ResidentListing if
     * they are, otherwise it shows an error message.
     */
    private class SubmitButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent actev) {
            try {
                ID = Integer.parseInt(txtID.getText());
            } catch (NumberFormatException numformerror) {
                System.out.println(numformerror.getMessage());
            } finally {
                idList.add(ID);
                AddIds(idList);
                Submit submit = new Submit(submission, thisForm);
            }
            thisForm.setVisible(false);
        }
    }

    /**
     * The CancelButtonListener class is an ActionListener that hides the current
     * GUI window when the
     * cancel button is clicked.
     */
    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);

        }
    }
}