package shared;

import java.io.Serializable;

public class Order implements Serializable {
    private String dateTime;
    private String orderId;
    private String name;
    private int qty;
    private double price;

    public Order(String dateTime, String orderId, String name, int qty, double price) {
        this.dateTime = dateTime;
        this.orderId = orderId;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    // Getters and setters
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
