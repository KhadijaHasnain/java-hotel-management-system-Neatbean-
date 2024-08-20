package mcgee.dcoms;

import shared.PaymentService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl extends UnicastRemoteObject implements PaymentService {
    private List<String[]> paymentDetails;

    public PaymentServiceImpl() throws RemoteException {
        paymentDetails = new ArrayList<>();
    }

    @Override
    public String processPayment(String name, String phoneNumber, String address, String paymentType) throws RemoteException {
        // Save the payment details
        String[] details = {name, phoneNumber, address, paymentType};
        paymentDetails.add(details);
        return "Payment processed successfully";
    }

    @Override
    public List<String[]> getPaymentDetails() throws RemoteException {
        return paymentDetails;
    }
}
