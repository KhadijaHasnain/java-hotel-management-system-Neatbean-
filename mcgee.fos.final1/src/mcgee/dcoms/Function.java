/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcgee.dcoms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;

public class Function {
 private static final String FILE_PATH = "record.txt";
    public static String IdGenerate(String filename) {
        String id = UUID.randomUUID().toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(id)) {
                    id = UUID.randomUUID().toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
    public static boolean login(String userName, String passWord) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            String user = data[3]; // Assuming username is at index 3
            String encryptedPassword = data[4]; // Assuming password is at index 4
            
            System.out.println(user);
            System.out.println(encryptedPassword);

            if (userName.equals(user)) {
                // Decrypt the stored password
                String decryptedPassword = new String(Base64.getDecoder().decode(encryptedPassword));

                // Compare the decrypted password with the one entered by the user
                if (passWord.equals(decryptedPassword)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    // Open the main application window after login
                    
//                    HomePage hp = new HomePage();
//                    hp.setVisible(true);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Password, Try Again!");
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Invalid Credentials, Try Again!");
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

    
    public static String getCount(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int count = model.getRowCount();
        return String.valueOf(count);
    }
}

