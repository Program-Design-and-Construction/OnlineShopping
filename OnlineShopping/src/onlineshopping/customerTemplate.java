/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

/**
 *
 * @author sct_n
 */
public abstract class customerTemplate implements customerFeature{
    protected int customerID;
    protected String name, password;
    protected double credit, discount=0.0;
    
    public void printInfo(){
        System.out.println(name);
        System.out.println(customerID);
        System.out.println(credit);
    }
    
    @Override
    public void purchase(double payment){
        payment=getDiscount(payment);
        System.out.println("Total: "+payment);
        this.credit-=payment;
    }
    @Override
    public int getcustomerID() {
        return customerID;
    }
    
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }    

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String p) {
        this.password = p;
    }
    
    public void setCredit(Double credit){
        this.credit = credit;
    }
    @Override
    public double getCredit() {
        return credit;
    }
    
    public boolean checkPayment(double am){
        return credit>(am*discount);
    }

    @Override
    public double getDiscount(double p) {
        if(discount > 0){
            System.out.println("Vip Discount: "+(discount*100)+"%");            
        }
        return p-(p*discount);
    }
}
