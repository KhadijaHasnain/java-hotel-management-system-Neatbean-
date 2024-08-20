/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package shared;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author junta
 */
public interface PaymentService extends Remote{
    String processPayment(String name, String phoneNumber, String address, String paymentType) throws RemoteException;
    
    // New method to get payment details
    List<String[]> getPaymentDetails() throws RemoteException;
}

