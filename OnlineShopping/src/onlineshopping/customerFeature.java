/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onlineshopping;

/**
 *
 * @author sct_n
 */
public interface customerFeature {
    
    public void purchase(double payment);
    public int getcustomerID();
    public String getName();
    public String getPassword();
    public void setPassword(String p);
    public double getCredit();
    public double getDiscount(double p);
}
