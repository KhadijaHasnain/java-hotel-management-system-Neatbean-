package mcgee.dcoms;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class testanalytics {

    // Data structures to hold sales and client data
    private Map<String, Integer> salesData;
    private int totalSales;
    private int itemsLeft;
    private double totalRevenue;

    // Constructor to initialize the analytics
    public testanalytics() {
        salesData = new HashMap<>();
        totalSales = 0;
        itemsLeft = 0;
        totalRevenue = 0.0;

        // Simulate some sales and client data
        resetData();
        
        // Display the report directly when the program starts
        String report = generateReport();
        JOptionPane.showMessageDialog(null, report, "Analytic Report of System", JOptionPane.INFORMATION_MESSAGE);
        
        // Exit the program after displaying the report
        System.exit(0);
    }

    // Method to reset the data
    private void resetData() {
        // Reset sales data
        salesData.clear();
        totalSales = 0;
        itemsLeft = 225; // Assuming 100 items in stock at the start
        
        // Simulate adding clients and recording sales
        addClient("John Doe", 80.0);
        addClient("Jane Doe", 50.0);
        recordSale("Burger", 50, 5.0);
        recordSale("Pizza", 30, 8.0);
        recordSale("Soda", 20, 2.5);
    }

    // Method to add a client
    public void addClient(String clientName, double orderValue) {
        totalRevenue += orderValue;
    }

    // Method to record a sale
    public void recordSale(String itemName, int quantity, double pricePerItem) {
        salesData.put(itemName, salesData.getOrDefault(itemName, 0) + quantity);
        totalSales += quantity;
        itemsLeft -= quantity;
        totalRevenue += quantity * pricePerItem;
    }

    // Method to generate the sales report
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Analytic Report of System ===\n");
        report.append("Items Left: ").append(itemsLeft).append("\n");
        report.append("Total Sales: ").append(totalSales).append("\n");
        report.append("Total Revenue: $").append(totalRevenue).append("\n");
        report.append("Item-wise Sales:\n");
        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
            report.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return report.toString();
    }

    // Main method to launch the GUI and display the report
    public static void main(String[] args) {
        new testanalytics();
    }
}
