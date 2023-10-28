/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sct_n
 */
public class Controller implements ActionListener{
    
    public View view;
    public Model model;
    
    /*
        Step 4:
        Assign view and model to attribute in the constructor, and add ActionListener(this) to the instance of view
    */
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
        this.view.addActionListener(this); // Add Actionlistener to View
    }
    
    /*
        Step 5:.
        Define ActioEvent based on the text displayed on the each button
        Note: need to change the property of relating component to public for external access
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            //Login interface
            case "Log in":
                //login button
                String username = this.view.unInput.getText(); // Get username
                String password = this.view.pwInput.getText(); // Get password
                this.model.checkName(username, password);
                break;
            case "Register":
                String u = this.view.unInput.getText(); // Get username
                String p = this.view.pwInput.getText(); // Get password
                this.model.registerUser(u, p);
                break;
            
            //Info interface
            case "Info":
                //Info button
                this.model.info();
                break;
            
            //Store interface
            case "Store":
                //store button
                this.model.store();
                break;
            case "comboBoxChanged":
                String type = this.view.productTypeComboBox.getSelectedItem().toString();
                this.model.itemType(type);
                break;
            case "Next Item":
                this.model.addIndex(1);
                break;
            case "Previous Item":
                this.model.addIndex(-1);
                break;
            case "Add Item":
                this.model.addtocart();
                break;
            case "Remove Item":
                this.model.removeFromCart();
                break;
                
            //Checkout interface
            case "Checkout":
                this.model.checkout();
                break;
            case "Confirm":
                this.model.purchase();
                break;
            case "Cancel":
                this.model.resetCart();
                break;
            
            //Topup
            case "Topup":
                this.model.topupMenu();
                break;
            case "topupConfirmButton":
                String creditText = this.view.topupInput.getText(); 
                this.view.topupInput.setText("");
                this.model.topUp(creditText);              
                break;
                
            //logout
            case "Log out":
                this.model.logout();
                break;
            default:
                break;
        }
    }
    
}
