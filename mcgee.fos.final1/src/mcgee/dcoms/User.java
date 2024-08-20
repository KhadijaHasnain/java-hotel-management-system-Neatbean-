/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcgee.dcoms;

/**
 *
 * @author User
 */
public class User {
    private String username; 
    private String password;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public boolean authenticate (String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }
   
}
    

