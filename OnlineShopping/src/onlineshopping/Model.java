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
    
    public Database db;
    public Data data;
    public int answer = 0;
    public String username;
    
    public Model(){
        this.db = new Database();
        this.db.dbsetup();
    }
    
    public void checkName (String username, String password){
        this.username = username;
        this.data = this.db.checkName(username, password);
        if(data.loginFlag){
            this.newQuestion();
        }
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void newQuestion(){
        this.data.num1 = getNumber();
        this.data.num2 = getNumber();
        this.answer = this.data.num1 + this.data.num2;
    }
    
    public int getNumber(){
        Random generator = new Random();
        int i = generator.nextInt(100);
        return i;
    }
    
    public void checkAnswer(String answer){
        data.currentScore = (answer.equals(this.answer + "") ? data.currentScore+10 : data.currentScore-10);
        this.newQuestion();
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void quitGame(){
        this.db.quitGame(this.data.currentScore, this.username);
        this.data.quitFlag = true;
        this.setChanged();
        this.notifyObservers(this.data);
    }
}
