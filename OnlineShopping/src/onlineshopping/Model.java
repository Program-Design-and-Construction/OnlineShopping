/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

import java.util.List;
import java.util.Observable;

/**
 *
 * @author sct_n
 */
public class Model extends Observable{
    
    public DBManager db;
    public state state;
    
    public Model(){
        this.db = new DBManager();
    }
    
    public void checkName (String username, String password){
        System.out.println("Checking User");
        this.state = db.checkName(username, password);
        this.setChanged();
        this.notifyObservers(this.state);
    }
    
    public void registerUser(String name, String pass){
        System.out.println("Registering User...");
        this.state = db.updateNewUser(name, pass);
        this.state = db.checkName(name, pass);
        this.setChanged();
        this.notifyObservers(this.state);
    }
    public void info(){
        System.out.println("Go to info");
        this.state.menuStatus = "Info";
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void store(){
        System.out.println("Go to store");
        this.state.menuStatus = "Store";
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void itemType(String type){
        this.state.productType = type;
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void addIndex(int n){
        List<product> prList = this.state.productList.get(this.state.productType);
        int index = this.state.productIndex;
        index += n;
        if(index > prList.size()-1){
            index = 0;
        }else if(index < 0){
            index = prList.size()-1;
        }
        this.state.productIndex = index;
        this.state.menuStatus = "Store";
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void addtocart(){
        this.state.addToCart();
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public  void removeFromCart(){
        this.state.removeFromCart();
        this.setChanged();
        this.notifyObservers(state);
    }
    public void checkout(){
        System.out.println("Go to checkout");
        this.state.menuStatus = "Checkout";
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void purchase(){
        System.out.println("Make a purchase");
        this.state.menuStatus = "Payment Fail";
        String p = "Payment Fail";
        boolean chkPayment = this.state.makePayment();
        if(chkPayment){
            p = "Payment Success";
            this.state.menuStatus = "Payment Success";           
        }
        System.out.println(p);
        String name = this.state.cust.getName();
        double credit = this.state.cust.getCredit();
        this.db.updateCustomer(name, credit);
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void resetCart(){
        this.state.resetCart();
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void topupMenu(){
        System.out.println("Go to Topup");
        this.state.menuStatus = "Topup";
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void topUp(String credit){
        System.out.println("Toped up");
        double c;
        try {
            c = Double.parseDouble(credit);
        } catch (NumberFormatException e) {
            c = 0;
        }
        this.state.cust.setCredit(c);
        this.db.updateCustomer(state.cust.getName(), state.cust.getCredit());
        this.state.menuStatus = "Topup";
        this.setChanged();
        this.notifyObservers(state);
    }
    public void logout(){
        System.out.println("Loging out...");
        this.state.loginFlag = false;
        this.setChanged();
        this.notifyObservers(state);
    }

}
