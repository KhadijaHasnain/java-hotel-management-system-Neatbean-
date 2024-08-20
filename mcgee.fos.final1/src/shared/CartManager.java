package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CartManager extends Remote {
    void addItemToCart(MenuItem item, String clientId) throws RemoteException;
    void removeItemFromCart(String itemName, String clientId) throws RemoteException;
    void editItemInCart(MenuItem item, String clientId) throws RemoteException;
    List<MenuItem> viewCart(String clientId) throws RemoteException;
}
