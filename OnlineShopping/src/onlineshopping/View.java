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
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author sct_n
 */
public class View extends JFrame implements Observer{
    
    //Homepage component
    private final JButton storeButton = new JButton("Store");    
    private final JButton logoutButton = new JButton("Log out");
    private final JButton infoButton = new JButton("Info");
    private final JButton topupButton = new JButton("Topup");
    
    //Login Page component
    private final JPanel loginPanel = new JPanel(new GridBagLayout()); 
    public JLabel message = new JLabel("Welcome to OnlineShopping!");
    private final JLabel uName = new JLabel("Username: ");
    public JTextField unInput = new JTextField(16);
    private final JLabel pWord = new JLabel("Password: ");
    public JPasswordField pwInput = new JPasswordField(16);
    public JLabel wrongName = new JLabel("");
    private final JButton loginButton = new JButton("Log in");
    private final JButton registerButton = new JButton("Register");
    
    //Store component
    public JComboBox productTypeComboBox = new JComboBox();
    public JButton nextButton = new JButton("Next Item");
    public JButton previousButton = new JButton("Previous Item");
    public JButton addButton = new JButton("Add Item");
    public JButton removeButton = new JButton("Remove Item");
    
    //Checkout component
    private final JButton checkoutButton = new JButton("Checkout");
    private final JButton confirmButton = new JButton("Confirm");
    private final JButton cancelButton = new JButton("Cancel");
    
    //Topup component
    public JTextField topupInput = new JTextField(16);
    private final JButton topupConfirmButton = new JButton("topupConfirmButton");
    
    /**
     * Step
     */
    public View(){
        login();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(600,600);
        this.setLocationRelativeTo(null); //Make the frame located at the absolute center of the screen.
        this.setVisible(true);
    }
    
    //login Panel
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
        
        gbc.gridy = 3;
        this.loginPanel.add(wrongName,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.loginPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        this.loginPanel.add(registerButton, gbc);
        this.getContentPane().removeAll();
        this.add(loginPanel);
        this.pack();
        this.revalidate();
        this.repaint();
    }
    
    //Register all button lisener
    public void addActionListener(ActionListener listener){
        
        //login page listener
        this.loginButton.addActionListener(listener);        
        this.registerButton.addActionListener(listener);
        
        //Menu listener
        this.infoButton.addActionListener(listener);
        this.storeButton.addActionListener(listener);
        this.checkoutButton.addActionListener(listener);
        this.topupButton.addActionListener(listener);
        this.logoutButton.addActionListener(listener);
         
        //Store page listener     
        this.productTypeComboBox.addActionListener(listener);
        this.nextButton.addActionListener(listener);
        this.previousButton.addActionListener(listener);
        this.addButton.addActionListener(listener);
        this.removeButton.addActionListener(listener);
        
        //Checkout page listener       
        this.confirmButton.addActionListener(listener);
        this.cancelButton.addActionListener(listener);       
        
        //Topup page listener      
        this.topupConfirmButton.addActionListener(listener);
    }
    
    //Draw panel according to each menu state
    public void  shoppingPage(state state){
        //Draw menu button on shopping page
        JPanel shoppingPanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome,"+state.cust.getName()+"!");
        shoppingPanel.add(welcomeLabel);        
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        menu.add(infoButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(topupButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(storeButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(checkoutButton);
        menu.add(Box.createVerticalStrut(10));
        menu.add(logoutButton);
        
        //Get panel according to each state.menuStatus
        JPanel menuGUI = getmenuGUI(state);
        
        this.getContentPane().removeAll();
        this.add(shoppingPanel,BorderLayout.NORTH);     
        this.add(menuGUI,BorderLayout.CENTER);
        this.add(menu,BorderLayout.WEST);
        this.revalidate();
        this.pack();
        this.repaint();
    }
    
    //Get image
    public JLabel getImage(String img){
        JLabel imageLabel = new JLabel("Image");
        System.out.println(img);
        try {
            // Load the image from a folder call resource
            BufferedImage image = ImageIO.read(new File("resource/"+img+".jpg"));

            // Create a JLabel to display the image
            imageLabel = new JLabel(new ImageIcon(image));

           
        } catch (IOException e) {
            System.out.println("Image Error");
        }
        return imageLabel;
    }
    
    //Draw shopping pannel using state.menuStatus
    public JPanel getmenuGUI(state state){
       
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        switch(state.menuStatus){
            case "Info":
                p = info(state);
                break;
            case "Store":
                p = store(state);
                break;
            case "Checkout":
                p = checkout(state);
                break;
            case "Topup":
                p = topUP(state);
                break;
            case "Payment Success":
                p = paymentPanel("Success");
                break;
            case "Payment Fail":
                p = paymentPanel("Fail");
                break;
            default:
                break;
        }
        p.revalidate();
        return p;
    }
    
    //Get Topup panel
    public JPanel topUP(state state){
        JPanel p = new JPanel();
        p.add(topupInput);
        p.add(topupConfirmButton);
        return p;
    }
    
    //Get inormation panel
    public JPanel info(state state){
        JPanel p = new JPanel();
        JTextArea textA = new JTextArea();
        String text = "Your Information: \n";
        text += "Current credit: $ "+state.cust.getCredit();
        textA.setText(text);
        p.add(textA);
        return p;
    }
    
    //Get product type combobox from state.productList
    public void getProductTypeComboBox(state s){
        Map<String, List<product>> productList = s.productList;        
        //get the product type list and add type combobox
        if(productTypeComboBox.getItemCount() == 0){
            for (Map.Entry<String, List<product>> entry : productList.entrySet()) {
                String type = entry.getKey();           
                productTypeComboBox.addItem(type);
            }
        }
    }
    
    //Get store panel
    public JPanel store(state state){
        JPanel p = new JPanel(new BorderLayout()); 
        
        //North componet
        JPanel Northp = new JPanel();
        Northp.add(previousButton);
        Northp.add(productTypeComboBox);
        Northp.add(nextButton);
        
        //Center component
        product pr = state.getProduct();
        JPanel Centerp = new JPanel();
        JLabel prImg = getImage(pr.getName());
        JTextArea textA = new JTextArea();
        String text = "Item Information: ";
        text += "\n\nItem:  "+ state.getProduct().getName();
        text += "\n\nPrice: $"+state.getProduct().getPrice();
        textA.setText(text);
        Centerp.add(prImg);
        Centerp.add(textA);
        
        //South component
        JPanel Southp = new JPanel();
        JLabel itemCount = new JLabel(""+state.itemCount());
        Southp.add(removeButton);
        Southp.add(itemCount);
        Southp.add(addButton);
        
        //Put all store componenet inside store panel
        p.add(Northp,BorderLayout.NORTH);
        p.add(Centerp,BorderLayout.CENTER);
        p.add(Southp,BorderLayout.SOUTH);      
        
        return p;
    }
    
    //Get checkout panel
    public JPanel checkout(state state){
        JPanel p = new JPanel(new BorderLayout());
        JTextArea textA = new JTextArea();
        String text = state.printCart();
        textA.setText(text);
        p.add(textA);
        
        //South panel
        JPanel spanel = new JPanel();
        spanel.add(confirmButton);
        spanel.add(cancelButton);
        p.add(spanel,BorderLayout.SOUTH);
        p.revalidate();
        return p;
    }
    
    //Get payment panel
    public JPanel paymentPanel(String pmt){
        JPanel p = new JPanel();
        JLabel img = getImage(pmt);
        JLabel text = new JLabel(pmt);
        p.add(img);
        p.add(text);       
        p.revalidate();
        return p;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        state state = (state) arg;
        if(!state.loginFlag){
            this.unInput.setText("");
            this.pwInput.setText("");
            this.wrongName.setText("Wrong User or Pass word");
            this.wrongName.setForeground(Color.RED);
            this.login();
        }else{
            this.wrongName.setText("");
            this.shoppingPage(state);
            getProductTypeComboBox(state);
        }
    }    
}
