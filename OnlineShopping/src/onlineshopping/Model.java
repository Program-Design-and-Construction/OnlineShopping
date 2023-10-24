/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

import java.util.Observable;
import java.util.Random;

/**
 *
 * @author sct_n
 */
public class Model extends Observable{
    
    public DBManager db;
    public state state;

    public int answer = 0;
    public String username;
    
    public Model(){
        this.db = new DBManager();
    }
    
    public void checkName (String username, String password){
        System.out.println("Checking User");
        this.state = db.checkName(username, password);
        this.setChanged();
        this.notifyObservers(this.state);
    }
    
    public void info(){
        System.out.println("Info clicked");
        this.state.menuStatus = "Info";
        this.setChanged();
        this.notifyObservers(state);
    }
    
    public void store(){
        System.out.println("Store clicked");
        this.state.menuStatus = "Store";
        this.setChanged();
        this.notifyObservers(state);
    }
    public void logout(){
        System.out.println("Loging out...");
        this.state = new state();
        this.setChanged();
        this.notifyObservers(state);
    }
}
