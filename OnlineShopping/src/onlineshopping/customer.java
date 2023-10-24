/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

/**
 *
 * @author sct_n
 */
public class customer extends customerTemplate{
    
//    private int customerID;
//    private String name, password;
//    private double credit, discount;
    public customer(){
        
    }
    public customer(String n,String p, int id){
        customerID = id;
        name = n;
        password = p;
        credit = 1000;
    }
    public customer(int id, String n, String p, double c){
        customerID = id;
        name = n;
        password = p;
        credit = c;
    }
}
