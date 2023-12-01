import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Path;



public class SubmissionQuota extends JFrame {
    private int submissionQuota = 5; // Assign a fixed submission quota for each student
    private int submissionsMade = 0;
    private ArrayList<FileInfo> submittedFiles; // To store the names of submitted files

    public SubmissionQuota() {
        setTitle("Submission Quota");
        setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton cmdCheckQuota = new JButton("Check Submission Quota");
        JButton cmdViewFiles = new JButton("View Submitted Files"); // New button for viewing submitted files
        JButton cmdCloseQuotaMenu = new JButton("Close Submission Quota Menu");

        panel.add(cmdCheckQuota);
        panel.add(cmdViewFiles);
        panel.add(cmdCloseQuotaMenu);

        cmdCheckQuota.addActionListener(new CheckQuotaListener());
        cmdViewFiles.addActionListener(new ViewFilesListener());
        cmdCloseQuotaMenu.addActionListener(new CloseQuotaMenuListener());

        add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        // Load submitted files information from the FileInfo.txt
        submittedFiles = loadFileInfo("FileInfo.txt");
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

    private class ViewFilesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder filesInfo = new StringBuilder("Submitted Files:\n");
            for (FileInfo fileInfo : submittedFiles) {
                filesInfo.append(fileInfo.getPath().getFileName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, filesInfo.toString(), "Submitted Files", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class CloseQuotaMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the submission quota menu
        }
    }

    private ArrayList<FileInfo> loadFileInfo(String idFile) {
        ArrayList<FileInfo> files = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(idFile));
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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SubmissionQuota();
            }
        });
    }
}
