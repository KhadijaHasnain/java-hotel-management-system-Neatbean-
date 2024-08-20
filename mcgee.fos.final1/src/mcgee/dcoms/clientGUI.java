/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mcgee.dcoms;

import shared.MenuItem;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.lang.String;
import shared.MenuManagerInt;

/**
 *
 * @author ninet
 */
public class clientGUI extends javax.swing.JFrame {

    private MenuManagerInt menuManager;
    private DefaultTableModel cartModel;
    private String LoggedInUsername;
    private String phoneNumber;
    private int orderID;
    private double TotalAmount;
    private String OrderDetails;

    /**
     * Creates new form clientGUI
     */
    public clientGUI(String orderDetails, double totalAmount, String loggedInUsername, int OrderID) {
        this.LoggedInUsername = loggedInUsername; // Store the username
        System.out.println(loggedInUsername);
        this.orderID = OrderID;
        this.OrderDetails = orderDetails;
        this.TotalAmount = totalAmount;

        initComponents();
        try {
            jLabel4.setText(loggedInUsername);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load usernmae " + e.getMessage(), "Order Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        initializeRMI();
        fetchAndDisplayMenuItems();
        setupCartTable();
        setTitle("Welcome, " + loggedInUsername + "!");
    }

    public clientGUI() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Description", "Price", "Category"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Add to cart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Go back");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Qty", "Name", "Price"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Total:");

        jButton4.setText("Proceed to Payment");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setText("Username:");

        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jButton1)
                .addGap(50, 50, 50)
                .addComponent(jButton2)
                .addGap(233, 233, 233)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel menuModel = (DefaultTableModel) jTable1.getModel();
            DefaultTableModel cartModel = (DefaultTableModel) jTable3.getModel();

            String name = (String) menuModel.getValueAt(selectedRow, 0);
            String description = (String) menuModel.getValueAt(selectedRow, 1);
            Double price = (Double) menuModel.getValueAt(selectedRow, 2);

            // Check if the item is already in the cart
            boolean itemExists = false;
            for (int i = 0; i < cartModel.getRowCount(); i++) {
                if (cartModel.getValueAt(i, 1).equals(name)) {
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                cartModel.addRow(new Object[]{1, name, price});
                updateTotalAmount(); // Update total amount when an item is added
            } else {
                JOptionPane.showMessageDialog(this, "Item already in the cart.", "Duplicate Item", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item from the menu.", "No Item Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel cartModel = (DefaultTableModel) jTable3.getModel();
            cartModel.removeRow(selectedRow);
            updateTotalAmount();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton3ActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            // Retrieve cart details
            DefaultTableModel cartModel = (DefaultTableModel) jTable3.getModel();
            List<String> orderDetailsList = new ArrayList<>();
            double totalAmount = 0;

            for (int i = 0; i < cartModel.getRowCount(); i++) {
                int qty = (Integer) cartModel.getValueAt(i, 0);
                String name = (String) cartModel.getValueAt(i, 1);
                double price = (Double) cartModel.getValueAt(i, 2);
                double amount = qty * price;
                totalAmount += amount;

                // Format each item correctly
                String itemDetails = String.format("%s,%d,%.2f,%.2f", name, qty, price, amount);
                orderDetailsList.add(itemDetails);
            }

            // Combine all items into a single string separated by new lines or another separator
            String orderDetails = String.join("\n", orderDetailsList);

            // Simulate user information
            String userName = LoggedInUsername;

            String userNumber = menuManager.getUserInfo(LoggedInUsername);

            String userInfo = userName + "," + userNumber;

            // Send order details and user information to the server and get the orderID
            int orderID = menuManager.placeOrder(orderDetails, userInfo);
            System.out.println("Order ID in ClientGUI: " + orderID);

            // Close current GUI
            this.dispose();

//            // Open new GUI to show ordered items
//            new OrderStatus(orderDetails, totalAmount, userName, orderID).setVisible(true);
            new mcgee.dcoms.Paymentpage(orderDetails, totalAmount, userName, orderID).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to place order: " + e.getMessage(), "Order Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        new mcgee.dcoms.HomePage(OrderDetails, TotalAmount, LoggedInUsername, orderID).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(clientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clientGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String loggedInUsername = null;
                new clientGUI().setVisible(true);
            }
        });
    }

    private void initializeRMI() {
        try {
            // Connect to the RMI registry and lookup the MenuManagerInt
            menuManager = (MenuManagerInt) Naming.lookup("rmi://localhost/MenuManager");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the server: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void fetchAndDisplayMenuItems() {
        try {
            List<MenuItem> menuItems = menuManager.getAllMenuItems();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing rows

            for (MenuItem item : menuItems) {
                Object[] row = new Object[]{
                    item.getName(),
                    item.getDescription(),
                    item.getPrice(),
                    item.getCategory()
                };
                model.addRow(row);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setupCartTable() {
        // Initialize the SpinnerEditor
        SpinnerEditor spinnerEditor = new SpinnerEditor();

        // Set the SpinnerEditor for the Qty column
        javax.swing.table.TableColumn qtyColumn = jTable3.getColumnModel().getColumn(0);
        qtyColumn.setCellEditor(spinnerEditor);
        qtyColumn.setCellRenderer(spinnerEditor);

        // Add TableModelListener to handle row removal if the qty is 0 or negative
        ((DefaultTableModel) jTable3.getModel()).addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 0) { // "Qty" column
                    int qty = (Integer) jTable3.getValueAt(row, column);
                    if (qty <= 0) {
                        // Ensure minimum qty is 1, prevent setting to 0 or negative values
                        jTable3.setValueAt(1, row, column);
                        qty = 1; // Update qty variable after correction
                    }
                    updateTotalAmount();
                }
            } else if (e.getType() == TableModelEvent.DELETE) {
                updateTotalAmount(); // Update total amount after row deletion
            }
        });
    }

    private void updateTotalAmount() {
        DefaultTableModel cartModel = (DefaultTableModel) jTable3.getModel();
        double totalAmount = 0.0;

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            int qty = (Integer) cartModel.getValueAt(i, 0);
            double price = (Double) cartModel.getValueAt(i, 2);
            totalAmount += qty * price;
        }

        jLabel2.setText(String.format("RM %.2f", totalAmount));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
