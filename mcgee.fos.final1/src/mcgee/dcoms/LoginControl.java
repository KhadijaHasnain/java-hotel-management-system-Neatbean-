/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcgee.dcoms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginControl {
    private Login view;
    private User model;

    public LoginControl(Login view, User model) {
        this.view = view;
        this.model = model;

        this.view.addLoginButtonListener(new LoginButtonListener());
        this.view.addRegisterButtonListener(new RegisterButtonListener());
    }

    class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();
            if (model.authenticate(username, password)) {
                JOptionPane.showMessageDialog(view, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(view, "Invalid username or password.");
            }
        }
    }

    class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new Register().setVisible(true);
            view.dispose();
        }
    }
}