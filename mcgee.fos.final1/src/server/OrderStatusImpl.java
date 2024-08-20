package server;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import shared.Order;
import shared.OrderStatusRemote;

public class OrderStatusImpl extends UnicastRemoteObject implements OrderStatusRemote {

    private static final String FILE_PATH = "data/OrderStatus.txt"; // Path to your text file
    private List<Order> orders;
    private static final String FILE_PATH_Orders = "data/Orders.txt"; // Path to your text file

    protected OrderStatusImpl() throws RemoteException {
        super();
    }

    @Override
    public List<String[]> getOrderHistory(String username) throws RemoteException {
        List<String[]> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH_Orders))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    if (parts[2].equals(username)) {
                        // Add the line split into parts
                        orders.add(parts);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RemoteException("Error reading order file", e);
        }
        return orders;
    }

    @Override
    public List<String> getAllOrders() throws RemoteException {
        List<String> orders = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                orders.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading orders from file: " + e.getMessage());
            throw new RemoteException("Error reading orders from file", e);
        }

        return orders;
    }

    @Override
    public void updateOrderStatus(int orderId, String status) throws RemoteException {
        List<String> orders = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length >= 9 && Integer.parseInt(details[1].trim()) == orderId) {
                    // Update the status
                    details[8] = status;
                    line = String.join(",", details); // Reassemble the updated order
                    updated = true;
                }
                orders.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading orders from file: " + e.getMessage());
            throw new RemoteException("Error reading orders from file", e);
        }

        if (updated) {
            // Write the updated orders back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String order : orders) {
                    bw.write(order);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error writing orders to file: " + e.getMessage());
                throw new RemoteException("Error writing orders to file", e);
            }
        } else {
            System.err.println("Order ID: " + orderId + " not found.");
        }
    }
}
