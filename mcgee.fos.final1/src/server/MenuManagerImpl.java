package server;

import shared.MenuItem;

import java.io.*;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import shared.MenuManagerInt;

public class MenuManagerImpl extends UnicastRemoteObject implements MenuManagerInt {

    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "data/menu.txt";
    private static final String FILE_PATH_ORDERS = "data/Orders.txt";
    private static final String FILE_PATH_STATUS = "data/OrderStatus.txt";
    private static final String FILE_PATH_USER = "record.txt";

    protected MenuManagerImpl() throws RemoteException {
        super();
    }

    @Override
    public void addMenuItem(MenuItem item) throws RemoteException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(item.toString() + System.lineSeparator());
        } catch (IOException e) {
            throw new RemoteException("Error writing to file", e);
        }
    }

    @Override
    public void deleteMenuItem(String name) throws RemoteException {
        File file = new File(FILE_PATH);
        File tempFile = new File("data/tempMenu.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(name + ",")) {
                    writer.write(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RemoteException("Error reading/writing to file", e);
        }

        // Replace old file with new file
        if (!file.delete()) {
            throw new RemoteException("Failed to delete old menu file");
        }
        if (!tempFile.renameTo(file)) {
            throw new RemoteException("Failed to rename temp file");
        }
    }

    public List<MenuItem> getAllMenuItems() throws RemoteException {
        List<MenuItem> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    MenuItem item = new MenuItem(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
                    items.add(item);
                    System.out.println("Reading item: " + line);
                }
            }
        } catch (IOException e) {
            throw new RemoteException("Error reading from file", e);
        }
        return items;
    }

    public void updateMenuItem(MenuItem item) throws RemoteException {
        File file = new File(FILE_PATH);
        File tempFile = new File("data/tempMenu.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(item.getName())) {
                    // Update the existing item with new values
                    writer.write(item.toString() + System.lineSeparator());
                } else {
                    writer.write(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RemoteException("Error reading/writing to file", e);
        }

        // Replace old file with new file
        if (!file.delete()) {
            throw new RemoteException("Failed to delete old menu file");
        }
        if (!tempFile.renameTo(file)) {
            throw new RemoteException("Failed to rename temp file");
        }
    }



    
    public String getUserInfo(String userName) throws RemoteException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_USER))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            
            // Ensure there are enough elements in the data array
            if (data.length > 3) {
                String user = data[3]; // Assuming username is at index 3
                if (userName.equals(user)) {
                    // Return the phone number directly
                    return data[2]; // Assuming phone number is at index 2
                }
            }
        }
    } catch (IOException ex) {
//        Logger.getLogger(MenuManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null; // Return null if no matching user is found
}
//
//    public static void main(String[] args) {
//        String userNameToFind = "exampleUser"; // Replace with actual username to search for
//        UserInfo userInfo = getUserInfo(userNameToFind);
//        if (userInfo != null) {
//            System.out.println("Name: " + userInfo.getName());
//            System.out.println("Phone Number: " + userInfo.getPhoneNumber());
//        } else {
//            System.out.println("User not found.");
//        }
//    }

    
    
    
    
    
    
    
    
    

@Override
public int placeOrder(String orderDetails, String userInfo) throws RemoteException {
        int orderID = getNextOrderID();
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir(); // Ensure the "data" directory exists
        }

        // Save order details
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Orders.txt", true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Split order details into individual items
            String[] items = orderDetails.split("\n");
            for (String item : items) {
                String line = timestamp + "," + orderID + "," + userInfo + "," + item;
                writer.write(line);
                writer.newLine();  // Add a new line after each order entry
            }
        } catch (IOException e) {
            throw new RemoteException("Error saving order details", e);
        }

        // Save order status
        try (BufferedWriter statusWriter = new BufferedWriter(new FileWriter("data/OrderStatus.txt", true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Split order details into individual items for status
            String[] items = orderDetails.split("\n");
            for (String item : items) {
                String statusLine = timestamp + "," + orderID + "," + userInfo + "," + item + ",Processing";
                statusWriter.write(statusLine);
                statusWriter.newLine();  // Add a new line after each status entry
            }
        } catch (IOException e) {
            throw new RemoteException("Error saving order status", e);
        }

        return orderID; // Return the orderID
    }

    @Override
public List<String> getOrderStatus(int orderID) throws RemoteException {
        List<String> orderStatuses = new ArrayList<>();

        File file = new File(FILE_PATH_STATUS);
        if (!file.exists()) {
            throw new RemoteException("Order status file not found");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                // Adjust the index based on where orderID is located in your file
                String[] parts = line.split(",");
                if (parts.length > 1 && Integer.parseInt(parts[1]) == orderID) {
                    orderStatuses.add(line);
                }
            }
        } catch (IOException e) {
            throw new RemoteException("Error reading order status", e);
        }

        return orderStatuses;
    }

    @Override
public int getNextOrderID() throws RemoteException {
        int orderID = 1;  // Default OrderID if the file is empty or does not exist

        File file = new File("data/OrderStatus.txt");
        if (!file.exists()) {
            return orderID;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String lastLine = "";

            // Read until the last line
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            if (!lastLine.isEmpty()) {
                // Extract the OrderID from the correct position in the last line
                String[] details = lastLine.split(",");
                if (details.length > 1) { // Check if there are enough elements
                    try {
                        // Assuming the ID is in the second position and might be followed by other text
                        String idString = details[1].trim();
                        orderID = Integer.parseInt(idString) + 1; // Increment the last OrderID
                    } catch (NumberFormatException e) {
                        throw new RemoteException("Invalid OrderID format in OrderStatus file", e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RemoteException("Error reading the OrderStatus file", e);
        }

        return orderID;
    }
}
