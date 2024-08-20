package server;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import shared.MenuManagerInt;

public class AdminServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Start RMI Registry
            MenuManagerInt menuManager = (MenuManagerInt) new MenuManagerImpl();
            Naming.rebind("MenuManager", menuManager);
            System.out.println("MenuManager bound in registry");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
