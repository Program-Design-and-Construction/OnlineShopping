/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author sct_n
 */
public class View extends JFrame implements Observer{
    
    private JPanel loginPanel = new JPanel(new GridBagLayout());
    
    public JTextField unInput = new JTextField(10);
    public JTextField pwInput = new JTextField(10);
    private JLabel wrongName = new JLabel("wrong username or password: ");
    
    //Homepage
    private JButton storeButton = new JButton("Store");
    private JButton checkout = new JButton("Checkout");
    private JButton logoutButton = new JButton("Log out");
    private JButton infoButton = new JButton("info");
    
    //Login Page
    public JLabel message = new JLabel("Welcome to OnlineShopping!");
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    private JButton loginButton = new JButton("Log in");
    private JButton registerButton = new JButton("Register");
    
    //Store
    public JComboBox productTypeComboBox = new JComboBox();
    public JButton nextButton = new JButton("Next Item");
    public JButton previousButton = new JButton("Previous Item");
    public JButton addButton = new JButton("Add Item");
    
    public View(){
        login();
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
        this.getContentPane().removeAll();
        this.add(loginPanel);
        this.revalidate();
        this.repaint();
    }
    
    public void addActionListener(ActionListener listener){
        this.loginButton.addActionListener(listener);
        this.logoutButton.addActionListener(listener);
        this.infoButton.addActionListener(listener);
        this.storeButton.addActionListener(listener);
        this.productTypeComboBox.addActionListener(listener);
        this.registerButton.addActionListener(listener);
    }
    
    public void  shoppingPage(state state){
        //state state = state;
        
        JPanel shoppingPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome,"+state.cust.getName()+"!");
        shoppingPanel.add(welcomeLabel);
        
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        menu.add(storeButton);
        menu.add(checkout);
        menu.add(logoutButton);
        
        
        JPanel menuGUI = getmenuGUI(state);
        
        this.getContentPane().removeAll();
        this.add(shoppingPanel,BorderLayout.NORTH);
        this.add(menu,BorderLayout.WEST);
        this.add(menuGUI,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    
    public JLabel getImage(String img){
        JLabel imageLabel = new JLabel(img); 
        try {
            // Load the image from a file (change the path to your image)
            BufferedImage image = ImageIO.read(new File("resource/"+img+".jpg"));

            // Create a JLabel to display the image
            imageLabel = new JLabel(new ImageIcon(image));

           
        } catch (IOException e) {
            System.out.println("Image Error");
        }
        return imageLabel;
    }
    
    public JPanel getmenuGUI(state state){
       
        JPanel p = new JPanel();
        
        p.setBackground(Color.WHITE);
        switch(state.menuStatus){
            case "Info":
                p=info(state);
                break;
            case "Store":
                p = store(state);
                break;
            default:
                break;
        }
        return p;
    }
   
    public JPanel info(state state){
        JPanel p = new JPanel();
        JTextArea textA = new JTextArea();
        String text = "Your Information: \n";
        text += "Current credit: $ "+state.cust.getCredit();
        textA.setText(text);
        p.add(textA);
        return p;
    }
    
    public JPanel store(state state){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        JLabel currentPr = null;
       
        Map<String, List<product>> productList = state.productList;        
        //get the product type list and add type combobox
        for (Map.Entry<String, List<product>> entry : productList.entrySet()) {
            String type = entry.getKey();
//            if(productList.containsKey(type)){
//                productTypeComboBox.addItem(type);
//            }
            productTypeComboBox.addItem(type);
        }
        p.add(productTypeComboBox,BorderLayout.NORTH);
        
        String selectedType = productTypeComboBox.getSelectedItem().toString();
        List<product> prList = state.productList.get(selectedType);
        
        int index = 0;
        if (index >= 0 && index < prList.size()) {
            product pr = prList.get(index);
            currentPr = getImage(pr.getName());
        } else {
            System.out.println("Invalid index.");
        }
        p.add(currentPr,BorderLayout.CENTER);
        p.add(nextButton,BorderLayout.EAST);
        p.add(previousButton,BorderLayout.WEST);
        p.add(addButton,BorderLayout.SOUTH);

        return p;
    }
    
    public boolean containItem(Map<String, List<product>> productList, String type){
        for (Map.Entry<String, List<product>> entry : productList.entrySet()) {
            String type = entry.getKey();
            productTypeComboBox.addItem(type);
        }
    }
    //public List<product> getProd 
    @Override
    public void update(Observable o, Object arg) {
        state state = (state) arg;
        if(!state.loginFlag){
            this.unInput.setText("");
            this.pwInput.setText("");
            this.login();
        }else{
            this.shoppingPage(state);
        }
    }
    
    public static void main(String[] args) {
        View view = new View();
        state s = new state();
        view.shoppingPage(s);
    }
    
}
