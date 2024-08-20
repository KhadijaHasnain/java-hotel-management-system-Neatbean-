/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcgee.dcoms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class analytics {

    // Data structure to hold sales data
    private Map<String, Integer> salesData;
    private int totalSales;
    
    // GUI components
    private JFrame frame;
    private JTextArea reportArea;

    // Constructor to initialize the analytics and GUI
    public analytics() {
        salesData = new HashMap<>();
        totalSales = 0;

        // Setup GUI components
        frame = new JFrame("McGee FOS - Report Analytics");
        reportArea = new JTextArea(20, 40);
        JButton generateReportButton = new JButton("Generate Report");

        // Add action listener to the button
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String report = generateReport();
                reportArea.setText(report);
            }
        });

        // Add components to the panel
        JPanel panel = new JPanel();
        panel.add(generateReportButton);
        panel.add(new JScrollPane(reportArea));

        // Set up the frame
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Method to record a sale
    public void recordSale(String itemName, int quantity) {
        salesData.put(itemName, salesData.getOrDefault(itemName, 0) + quantity);
        totalSales += quantity;
    }

    // Method to generate the sales report
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Sales Report ===\n");
        report.append("Total Sales: ").append(totalSales).append("\n");
        report.append("Item-wise Sales:\n");
        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
            report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return report.toString();
    }

    // Main method to launch the GUI and simulate some sales data
    public static void main(String[] args) {
        analytics analytics = new analytics();

        // Simulate some sales data
        analytics.recordSale("Burger", 50);
        analytics.recordSale("Pizza", 30);
        analytics.recordSale("Soda", 20);

        // Run the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new analytics();
            }
        });
    }
}


//package mcgee.dcoms;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Analytics {
//
//    // Data structure to hold sales data
//    private Map<String, Integer> salesData;
//    private int totalSales;
//
//    // GUI components
//    private JFrame frame;
//
//    // Constructor to initialize the analytics and GUI
//    public Analytics() {
//        salesData = new HashMap<>();
//        totalSales = 0;
//
//        // Setup GUI components
//        frame = new JFrame("McGee FOS - Report Analytics");
//        JButton generateReportButton = new JButton("Generate Report");
//
//        // Add action listener to the button
//        generateReportButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String report = generateReport();
//                JOptionPane.showMessageDialog(frame, report, "Analytic Report of System", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });
//
//        // Add components to the panel
//        JPanel panel = new JPanel();
//        panel.add(generateReportButton);
//
//        // Set up the frame
//        frame.add(panel);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//
//    // Method to record a sale
//    public void recordSale(String itemName, int quantity) {
//        salesData.put(itemName, salesData.getOrDefault(itemName, 0) + quantity);
//        totalSales += quantity;
//    }
//
//    // Method to generate the sales report
//    public String generateReport() {
//        StringBuilder report = new StringBuilder();
//        report.append("=== Analytic Report of System ===\n");
//        report.append("Total Sales: ").append(totalSales).append("\n");
//        report.append("Item-wise Sales:\n");
//        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
//            report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
//        }
//        return report.toString();
//    }
//
//    // Main method to launch the GUI and simulate some sales data
//    public static void main(String[] args) {
//        Analytics analytics = new Analytics();
//
//        // Simulate some sales data
//        analytics.recordSale("Burger", 50);
//        analytics.recordSale("Pizza", 30);
//        analytics.recordSale("Soda", 20);
//
//        // Run the GUI
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Analytics();
//            }
//        });
//    }
//}
