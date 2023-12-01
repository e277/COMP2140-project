import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Submit extends JFrame {
    private JFileChooser fc;
    private File fileSent;
    private JButton cmdClose;
    private JButton cmdSubmit;
    private JButton cmdUpload;
    private JButton cmdUpdate;
    private JButton cmdQuotaInfo;
    private JButton cmdReportMenu; // report menu button
    private JPanel panelCommand;
    private JPanel panelUpload;
    private JPanel panelDisplay;
    private JPanel panelSubDisplay;
    private JLabel subHeader;
    private JLabel upload;
    private JLabel txtName;
    private Submit submit;
    private ThesisSubmission submission;
    private StudentPrompt prompts;
    private ArrayList<FileInfo> files;
    private FileInfo info;
    private Path path;

    /**
     * The Application Constructor that will be used to create the home GUI
     */
    public Submit(ThesisSubmission thesisSub, StudentPrompt thisForm) {
        submit = this;
        submission = thesisSub;
        prompts = thisForm;
       
        files = loadFileInfo("FileInfo.txt");
        fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        panelCommand = new JPanel();
        panelUpload = new JPanel();
        panelDisplay = new JPanel();
        panelSubDisplay = new JPanel();
        panelSubDisplay.setLayout(new GridLayout(2, 2));

        setBackground(Color.WHITE);

        setTitle("Submission Portal");

        // to display Application Header

        ImageIcon icon1 = new ImageIcon("uwicrest.jpg");
        Image logoImage = icon1.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(logoImage);
        JLabel logo = new JLabel(scaledLogo, SwingConstants.CENTER);

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

        subHeader = new JLabel("SUBMISSION PORTAL", SwingConstants.CENTER);
        subHeader.setFont(new Font("Georgia", Font.BOLD, 46));
        subHeader.setAlignmentX(CENTER_ALIGNMENT);

        ImageIcon icon2 = new ImageIcon("submit.png");
        Image submitImage = icon2.getImage().getScaledInstance(200, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledSubmit = new ImageIcon(submitImage);
        JLabel submitImg = new JLabel(scaledSubmit, SwingConstants.CENTER);
        submitImg.setAlignmentX(CENTER_ALIGNMENT);

        upload = new JLabel("Please upload your thesis using the button below:", SwingConstants.CENTER);
        upload.setFont(new Font("Georgia", Font.PLAIN, 18));
        upload.setAlignmentX(CENTER_ALIGNMENT);

        txtName = new JLabel();
        txtName.setFont(new Font("Georgia", Font.PLAIN, 14));
        txtName.setVisible(false);

        GridLayout layout = new GridLayout(3, 2);
        panelDisplay.setLayout(layout);

        // command buttons
        cmdClose = new JButton("Close Portal");
        cmdSubmit = new JButton("Submit Thesis");
        cmdUpload = new JButton("Upload File");
        cmdUpdate = new JButton("Update Thesis");
        cmdReportMenu = new JButton("Report Menu"); // report menu button
        cmdQuotaInfo = new JButton("Quota Information");

        cmdSubmit.setEnabled(false);

        // colour of buttons
        cmdSubmit.setBackground(Color.CYAN);

        cmdClose.setBackground(Color.RED);
        cmdClose.setForeground(Color.WHITE);

        cmdUpload.setBackground(Color.YELLOW);
        cmdUpdate.setBackground(Color.LIGHT_GRAY);
        cmdUpdate.setForeground(Color.WHITE);
        cmdReportMenu.setBackground(Color.GREEN); // report menu button
        cmdQuotaInfo.setBackground(Color.ORANGE);

        panelCommand.add(cmdSubmit);
        panelCommand.add(cmdUpdate);
        panelCommand.add(cmdQuotaInfo);
        panelCommand.add(cmdReportMenu); // report menu button
        panelCommand.add(cmdClose);
        panelCommand.setAlignmentX(CENTER_ALIGNMENT);

        cmdClose.addActionListener(new closeButtonListener());
        cmdSubmit.addActionListener(new submitButtonListener());
        cmdUpload.addActionListener(new submitButtonListener());
        cmdUpdate.addActionListener(new submitButtonListener());
        cmdReportMenu.addActionListener(new ReportMenuButtonListener()); // report menu button
        cmdQuotaInfo.addActionListener(new QuotaInfoButtonListener()); // Add listener for Quota Information button

        panelUpload.add(txtName);
        panelUpload.add(cmdUpload);

        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalGlue());
        vBox.add(subHeader);
        vBox.add(Box.createVerticalGlue());
        vBox.add(submitImg);
        vBox.add(Box.createVerticalGlue());
        vBox.setAlignmentX(CENTER_ALIGNMENT);

        panelSubDisplay.add(upload);
        panelSubDisplay.add(panelUpload);

        panelDisplay.add(hBox, BorderLayout.NORTH);
        panelDisplay.add(vBox, BorderLayout.CENTER);
        panelDisplay.add(panelSubDisplay, BorderLayout.SOUTH);

        add(panelDisplay, BorderLayout.CENTER);
        add(panelCommand, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(1400, 700));

        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 1400;
        int height = 700;
        setBounds(center.x - width / 2, center.y - height / 2, width, height);

        pack();
        setVisible(true);
    }

    /**
     * The closeButtonListener class is an ActionListener that exits the program
     * when the close button
     * is clicked.
     */
    private class closeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            submission.setVisible(true);
        }
    }

    public void AddFileInfo(ArrayList<FileInfo> fileList) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("FileInfo.txt"));
            for (FileInfo r : fileList) {
                out.println(r);

            }
            out.close();
        } catch (FileNotFoundException fe) {
            System.out.print(fe.getMessage());
        }
    }

    private ArrayList<FileInfo> loadFileInfo(String idFile) {
        Scanner scan;
        ArrayList<FileInfo> files = new ArrayList<FileInfo>();
        try {
            scan = new Scanner(new File(idFile));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                int id = Integer.parseInt(line.split(" ")[0]);
                Path path = new File(line.split(" ")[1]).toPath();
                FileInfo fInfo = new FileInfo(id, path);
                files.add(fInfo);
            }
            return files;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return files;
    }

    public boolean fileExists(FileInfo file, ArrayList<FileInfo> files) {
        for (FileInfo fl : files) {
            if (fl.getID() == file.getID()) {
                if (fl.getPath().equals(file.getPath())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getFileExtension(File fl) {
        String fileNameWithExtension = fileSent.toString();
        int lastDotIndex = fileNameWithExtension.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fileNameWithExtension.substring(lastDotIndex);
        } else {
            return "";
        }
    }

    private void Quota() {
        String idString = String.valueOf(prompts.getID());

        try {
            int id = Integer.parseInt(idString);

            // Logic to check submission quota for the given ID
            int remainingSubmissions = 5 - getSubmissionsForID(id);
            if (remainingSubmissions > 0) {
                JOptionPane.showMessageDialog(null, "You have " + remainingSubmissions +
                        " submissions remaining out of " + 5);
            } else {
                JOptionPane.showMessageDialog(null, "You have reached your submission quota.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid number.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            cmdUpload.setEnabled(false);
            cmdSubmit.setEnabled(false);
            submit.setVisible(false);
        }
    }

    private int getSubmissionsForID(int id) {
        // Count the number of submissions made for the given ID
        int count = 0;
        for (FileInfo fileInfo : files) {
            if (fileInfo.getID() == id) {
                String name = fileInfo.getPath().toString().split("_")[1];
                count = Integer.parseInt(name.substring(1));
            }
        }
        return count;
    }

    private class QuotaInfoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SubmissionQuota(prompts);
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
    /**
     * The submitButtonListener class creates an instance of either the Submit or
     * Update class based on
     * the source of the ActionEvent.
     */
    private class submitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent g) {
            if (g.getSource() == cmdSubmit) {
                String sourcePath = fileSent.getAbsolutePath();
                if (sourcePath.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a file to upload.", "Thesis Status",
                            JOptionPane.DEFAULT_OPTION);
                } else {

                    File destinationFolder = new File("Submissions");
                    String fileName = new File(sourcePath).getName();

                    Path source = new File(sourcePath).toPath();
                    Path destination = new File(destinationFolder, fileName).toPath();

                    try {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                        
                        
                        info = new FileInfo(prompts.getID(), destination);
                        boolean oldFile = fileExists(info, files);
                        if (!oldFile) {
                            files.add(info);
                            AddFileInfo(files);

                        }
                        Version version = new Version(prompts.getID(), destination);
                        // Add the version
                        version.addVersion();
                        files = loadFileInfo("FileInfo.txt");

                        JOptionPane.showMessageDialog(null, "Your Thesis has been submitted", "Submitted",
                                JOptionPane.DEFAULT_OPTION);
                        Quota();
                        
                        SendEmail sendEmail = new SendEmail(prompts);
                        txtName.setText("");
                        txtName.setVisible(false);
                        cmdSubmit.setEnabled(false);                 
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (g.getSource() == cmdUpdate) {
                for (FileInfo fl: files){
                    if (prompts.getID() == fl.getID()){
                        path = fl.getPath();
                    }
                }
                Version version = new Version(prompts.getID(), path);
                version.updateVersion();
                files = loadFileInfo("FileInfo.txt");

                JOptionPane.showMessageDialog(null, "Your Thesis has been updated", "Updated",
                        JOptionPane.DEFAULT_OPTION);
                Quota();
                txtName.setText("");
                txtName.setVisible(false);
                cmdSubmit.setEnabled(false);
                submit.setVisible(false);
            } else {
                int n = fc.showOpenDialog(null);
                if (n == JFileChooser.APPROVE_OPTION) {
                    fileSent = new File(fc.getSelectedFile().getAbsolutePath());
                    String ext = getFileExtension(fileSent);
                    if (ext.equals(".doc") || ext.equals(".docx") || ext.equals(".pdf")) {
                        txtName.setText(fc.getSelectedFile().getName());
                        txtName.setVisible(true);
                        cmdSubmit.setVisible(true);
                        cmdSubmit.setEnabled(true);
                        for(FileInfo fi : files){
                            if(fi.getID() == prompts.getID()){
                                cmdSubmit.setEnabled(false);
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Only file types of .doc, .docx, or .pdf are supported",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }

    }
}
