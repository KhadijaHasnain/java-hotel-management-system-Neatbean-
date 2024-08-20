package server;

import shared.CartManager;
import shared.MenuItem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManagerImpl extends UnicastRemoteObject implements CartManager {
    private static final long serialVersionUID = 1L;
    private Map<String, List<MenuItem>> clientCarts;

    protected CartManagerImpl() throws RemoteException {
        clientCarts = new HashMap<>();
    }

    @Override
    public void addItemToCart(MenuItem item, String clientId) throws RemoteException {
        clientCarts.putIfAbsent(clientId, new ArrayList<>());
        clientCarts.get(clientId).add(item);
    }

    @Override
    public void removeItemFromCart(String itemName, String clientId) throws RemoteException {
        List<MenuItem> cart = clientCarts.get(clientId);
        if (cart != null) {
            cart.removeIf(item -> item.getName().equals(itemName));
        }
    }

    @Override
    public void editItemInCart(MenuItem item, String clientId) throws RemoteException {
        List<MenuItem> cart = clientCarts.get(clientId);
        if (cart != null) {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getName().equals(item.getName())) {
                    cart.set(i, item);
                    break;
                }
            }
        }
    }

    @Override
    public List<MenuItem> viewCart(String clientId) throws RemoteException {
        return clientCarts.getOrDefault(clientId, new ArrayList<>());
    }
}
