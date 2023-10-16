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
            case "Log in":
                //login button
                String username = this.view.unInput.getText(); // Get username
                String password = this.view.pwInput.getText(); // Get password
                this.model.checkName(username, password);
                break;
            case "Next":
                //Next button
                this.model.checkAnswer(this.view.calcSolution.getText());
                break;
            case "Quit":
                this.model.quitGame();
                break;
            default:
                break;
        }
    }
    
}
