import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReportMenu extends JFrame {
    private JButton cmdGenerateReport;
    private JButton cmdCloseReport;
    private JTable dataTable1, dataTable2;
    
    public ReportMenu() {}
    
    public ReportMenu(Student student, FileInfo fileInfo) {
        setTitle("Report Menu");
        setSize(900, 900);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        cmdGenerateReport = new JButton("Generate Report");
        cmdCloseReport = new JButton("Close Report Menu");
        
        panel.add(cmdGenerateReport);
        panel.add(cmdCloseReport);
        
        cmdGenerateReport.addActionListener(new GenerateReportListener());
        cmdCloseReport.addActionListener(new CloseReportMenuListener());

        add(panel, BorderLayout.NORTH);

        DefaultTableModel model1 = new DefaultTableModel();
        dataTable1 = new JTable(model1);

        DefaultTableModel model2 = new DefaultTableModel();
        dataTable2 = new JTable(model2);

        JScrollPane scrollPane1 = new JScrollPane(dataTable1);
        JScrollPane scrollPane2 = new JScrollPane(dataTable2);

        // Add an empty border with a margin at the top
        int topMargin = 100; // Adjust the margin as needed
        scrollPane1.setBorder(BorderFactory.createEmptyBorder(topMargin, 0, 0, 0));
        scrollPane2.setBorder(BorderFactory.createEmptyBorder(topMargin, 0, 0, 0));
        
        add(scrollPane1, BorderLayout.CENTER);
        add(scrollPane2, BorderLayout.SOUTH);

        // add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private class GenerateReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logic to generate the report
            // JOptionPane.showMessageDialog(null, "Report generats successfully");
            loadAndDisplayData();
        }
    }

    private class CloseReportMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the report menu
        }
    }

    private void loadAndDisplayData() {
        try {
            DefaultTableModel tableModel1 = new DefaultTableModel();
            DefaultTableModel tableModel2 = new DefaultTableModel();
    
            // Set column identifiers for both files
            tableModel1.setColumnIdentifiers(new String[] { "ID Number", "File Name" });
            tableModel2.setColumnIdentifiers(new String[] { "First Name", "Last Name", "ID Number" });
    
            // Set the model for the dataTable
            dataTable1.setModel(tableModel1);
            dataTable2.setModel(tableModel2);
    
            // Load data from FileInfo.txt
            loadFileData("FileInfo.txt", tableModel1);
    
            // Load data from Students.txt
            loadFileData("Students.txt", tableModel2);
    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data from files: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
 
    private void loadFileData(String fileName, DefaultTableModel tableModel) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Clear existing columns if any
        
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split("\\s+");
                tableModel.addRow(data);
                line = reader.readLine();
            }
        
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from " + fileName + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
