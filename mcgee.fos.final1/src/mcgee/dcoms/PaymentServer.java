package mcgee.dcoms;

import shared.PaymentService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PaymentServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the PaymentServiceImpl
            PaymentService service = new PaymentServiceImpl();

            // Start the RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the service to the RMI registry
            registry.rebind("PaymentService", service);

            System.out.println("PaymentService is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
