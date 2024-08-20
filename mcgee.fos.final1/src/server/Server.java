package server;

import shared.CartManager;
import shared.OrderStatusRemote; // Add other interfaces here as needed
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import shared.PaymentService;
import mcgee.dcoms.PaymentServiceImpl;
import shared.MenuManagerInt;

public class Server {

    public static void main(String[] args) {
        try {
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Bind MenuManagerInt
            MenuManagerInt menuManager = new MenuManagerImpl();
            Naming.rebind("rmi://localhost/MenuManager", menuManager); // Corrected binding name
            System.out.println("MenuManager bound in registry");

            // Bind CartManager
            CartManager cartManager = new CartManagerImpl();
            Naming.rebind("CartManager", cartManager);
            System.out.println("CartManager bound in registry");

            // Bind OrderStatusRemote
            OrderStatusRemote orderStatusRemote = new OrderStatusImpl();
            Naming.rebind("OrderStatusRemote", orderStatusRemote);
            System.out.println("OrderStatusRemote bound in registry");

            PaymentService service = new PaymentServiceImpl();

            Naming.rebind("PaymentService", service);
            System.out.println("PaymentService bound in registry");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
