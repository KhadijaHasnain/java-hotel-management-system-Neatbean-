package mcgee.dcoms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import shared.OrderStatusRemote;
import shared.MenuManagerInt;

public class OrderStatus extends javax.swing.JFrame {

    private MenuManagerInt menuManager;
    private OrderStatusRemote orderStatusRemote; // Add this field

    private String orderDetails;
    private double totalAmount;
    private int orderID;
    private String UserName;

    public OrderStatus(String orderDetails, double totalAmount, String userName, int orderID) {
        this.orderDetails = orderDetails;
        this.totalAmount = totalAmount;
        this.orderID = orderID;
        this.UserName= userName;
        
        // Print constructor parameters for debugging
        System.out.println("OrderStatus Constructor:");
        System.out.println("Order Details: " + orderDetails);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Order ID: " + orderID);

        initComponents();
        initializeRMI();
        startPolling();
        updateOrderStatus();
    }

    private void initializeRMI() {
        try {
            // Connect to the RMI registry and lookup the remote interfaces
            menuManager = (MenuManagerInt) Naming.lookup("rmi://localhost/MenuManager");
            orderStatusRemote = (OrderStatusRemote) Naming.lookup("rmi://localhost/OrderStatusRemote");
            System.out.println("Connected to RMI server successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the server: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateOrderStatus() {
        try {
            // Print orderDetails and orderID for debugging
            System.out.println("Updating Order Status:");
            System.out.println("Order Details: " + orderDetails);
            System.out.println("Order ID: " + orderID);
            jLabel4.setText(String.valueOf(orderID));

            // Use the orderID to fetch the order status
            List<String> statuses = menuManager.getOrderStatus(orderID);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing rows

            String statusToDisplay = ""; // Initialize a variable to store status to be displayed

            for (String status : statuses) {
                String[] details = status.split(",");
                System.out.println("Status Details: " + String.join(", ", details)); // Print each status detail

                // Check if the details array has the expected number of elements
                if (details.length >= 7) {
                    model.addRow(new Object[]{
                        details[4], // Name (index 4)
                        details[5], // Quantity (index 5)
                        details[7], // Price (index 6)
                        details[8] // Status (index 8)
                    });

                    // Update the statusToDisplay with the last entry status
                    statusToDisplay = details[8];
                } else {
                    System.out.println("Skipping entry due to insufficient details: " + status);
                }
            }

            // Print totalAmount for debugging
            System.out.println("Total Amount Displayed: " + statusToDisplay);

        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching order status", "Fetch Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startPolling() {
        // Create a timer that triggers every 3 seconds (3000 milliseconds)
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshOrderStatus();
            }
        });
        timer.start(); // Start the timer
    }

    private void refreshOrderStatus() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Fetch the latest order statuses from the remote server
                List<String> orders = menuManager.getOrderStatus(orderID);
                // Update the GUI table with the fetched orders
                updateTable(orders);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateTable(List<String> orders) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows
        String StatusUpadte = null;
        for (String order : orders) {
            String[] details = order.split(",");
            if (details.length >= 4) {
                model.addRow(new Object[]{
                    details[4], // Name
                    details[5], // Quantity
                    details[7], // Price
                    details[8] // Status
                });
            }
            StatusUpadte = details[8];
        }
        jLabel2.setText(StatusUpadte);

    }

    // Getter methods for components to be used in OrderStatusListenerImpl
    public int getOrderID() {
        return orderID;
    }

    public javax.swing.JTable getTable() {
        return jTable1;
    }

    public javax.swing.JLabel getStatusLabel() {
        return jLabel2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Qty", "Price", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Order ID:");

        jButton2.setBackground(new java.awt.Color(102, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Home");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jButton2)))
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new mcgee.dcoms.HomePage(orderDetails,totalAmount,UserName, orderID).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new OrderStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
