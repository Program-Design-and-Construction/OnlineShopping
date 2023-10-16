/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sct_n
 */
public class View extends JFrame implements Observer{
    
    String state[] = {"login", "homepage"}; 
    private JPanel loginPanel = new JPanel(new GridBagLayout());
    private JPanel calcPanel = new JPanel();
    
    public JTextField unInput = new JTextField(10);
    public JTextField pwInput = new JTextField(10);
    private JLabel wrongName = new JLabel("wrong username or password: ");
    
    private JLabel firstNumber = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel additionLabel = new JLabel("+");
    private JButton nextButton = new JButton("Next");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");
    private JButton registerButton = new JButton("Register");
    
    public JLabel message = new JLabel("Welcome to OnlineShopping!");
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    
    
    public View(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null); //Make the frame located at the absolute center of the screen.
        this.setVisible(true);
    }
    
    public void login(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        
        //Add componet for login page
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        this.loginPanel.add(message, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.loginPanel.add(uName, gbc);
        gbc.gridx = 1;
        this.loginPanel.add(unInput, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.loginPanel.add(pWord, gbc);
        gbc.gridx = 1;
        this.loginPanel.add(pwInput, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.loginPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        this.loginPanel.add(registerButton, gbc);
        this.add(loginPanel);
        this.repaint();
    }
    
    public void addActionListener(ActionListener listener){
        this.loginButton.addActionListener(listener);
        this.registerButton.addActionListener(listener);
    }
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        View view = new View();
        view.login();
    }
    
}
