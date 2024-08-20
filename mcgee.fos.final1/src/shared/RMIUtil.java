package shared;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIUtil {

    public static MenuManagerInt getMenuManager() throws RemoteException {
        try {
            return (MenuManagerInt) Naming.lookup("rmi://localhost/MenuManager");
        } catch (Exception e) {
            throw new RemoteException("Error looking up MenuManager", e);
        }
    }

    public static CartManager getCartManager() throws RemoteException {
        try {
            return (CartManager) Naming.lookup("rmi://localhost/CartManager");
        } catch (Exception e) {
            throw new RemoteException("Error looking up CartManager", e);
        }
    }

    public static OrderStatusRemote getOrderStatusRemote() throws RemoteException {
        try {
            return (OrderStatusRemote) Naming.lookup("rmi://localhost/OrderStatusRemote");
        } catch (Exception e) {
            throw new RemoteException("Error looking up OrderStatusRemote", e);
        }
    }

    public static PaymentService getpaymentService() throws RemoteException {
        try {

            return (PaymentService) Naming.lookup("rmi://localhost/PaymentService");
        } catch (Exception e) {
            throw new RemoteException("Error looking up PaymentService", e);
        }
    }
}
//
//    public static OrderStatusNotifier getOrderStatusNotifier() throws RemoteException {
//        try {
//            return (OrderStatusNotifier) Naming.lookup("rmi://localhost/OrderStatusNotifier");
//        } catch (Exception e) {
//            throw new RemoteException("Error looking up OrderStatusNotifier", e);
//        }

