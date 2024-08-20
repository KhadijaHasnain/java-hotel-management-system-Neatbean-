package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MenuManagerInt extends Remote {

    void addMenuItem(MenuItem item) throws RemoteException;

    void deleteMenuItem(String name) throws RemoteException;

    List<MenuItem> getAllMenuItems() throws RemoteException;

    void updateMenuItem(MenuItem item) throws RemoteException;

    int placeOrder(String orderDetails, String userInfo) throws RemoteException;

    List<String> getOrderStatus(int orderID) throws RemoteException;

    int getNextOrderID() throws RemoteException;

    String getUserInfo(String userName) throws RemoteException;
}
