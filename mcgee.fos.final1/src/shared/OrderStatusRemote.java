/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OrderStatusRemote extends Remote {

    List<String> getAllOrders() throws RemoteException;

    void updateOrderStatus(int orderId, String status) throws RemoteException;

    List<String[]> getOrderHistory(String username) throws RemoteException;
}
