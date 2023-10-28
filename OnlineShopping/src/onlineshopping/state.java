/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sct_n
 */
public class state {
    boolean loginFlag;
    customer cust;
    String menuStatus;
    Map<String, List<product>> productList;
    String productType;
    int productIndex;
    Map<product, Integer> cart;
    double totalCost;
    
    public state(){
        loginFlag = false;
        menuStatus = "Info";
        cust = new customer();
        productList = new HashMap<>();
        cart = new HashMap<>();
        productIndex = 0;
        totalCost = 0;
    }
    
    public product getProduct(){
        product pr = null;
        if (productList != null && productType != null) {
            List<product> prList = productList.get(productType);
            if (prList != null && productIndex >= 0 && productIndex < prList.size()) {
                pr = prList.get(productIndex);
            }
        }
        System.out.println(productType);
        return pr;
    }
    
    //Get the count of item in the cart and 
    //if the item is not in the cart return 0 
    public int itemCount(){
        product p = getProduct();
        int count = 0;
        if(cart.get(p) != null){
            count = cart.get(p);
        }
        return count;
    }
    public void addToCart(){
        product pr = getProduct();      
        System.out.println("Add "+ pr.getName() + "to cart.");

        // Check if the item is already in the HashMap
        if (cart.containsKey(pr)) {
            // If it's already there, increment the count
            cart.put(pr, cart.get(pr) + 1);
        } else {
            // If it's not in the HashMap, add it with a count of 1
            cart.put(pr, 1);
        }
        totalCost += pr.getPrice();
    }
    
    public void removeFromCart(){
        product pr = getProduct();      
        System.out.println("Remove "+ pr.getName() + "From cart.");

        // Check if the item is already in the HashMap
        if (cart.containsKey(pr)) {
            // If it's already there, decrease the count
            if(cart.get(pr) > 1){
                cart.put(pr, cart.get(pr) - 1);
            }else{
                cart.remove(pr);
            }
            totalCost -= pr.getPrice();
        } 
    }
    
    //Get the list of item in the cart as a string
    public String printCart(){
        String text = "Your cart:\n";
        for (Map.Entry<product, Integer> entry : cart.entrySet()) {
            text += entry.getKey().getName() + "  X  " + entry.getValue() +"=  $" + (entry.getKey().getPrice()*entry.getValue())+"\n";
        }        
        text += "Total cost: $"+ totalCost;
        return text;
    }
    
    public boolean makePayment(){
        boolean success = false;
        if(cust.getCredit() >= totalCost){
            cust.purchase(totalCost);
            success = true;
        }
        cart = new HashMap<>();
        totalCost = 0;
        return success;
    }
    
    public void resetCart(){
        cart = new HashMap<>();
        totalCost = 0;
    }
}
