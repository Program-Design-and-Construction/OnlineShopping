/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

/**
 *
 * @author sct_n
 */
public class product implements productFeature{
    
    private String name;
    private String productTypes;
    private double price;
    
    public product(String proTypes, String name, double price){
        this.name = name;
        this.productTypes = proTypes;
        this.price = price;
    }
            
    @Override
    public void printInfo() {
        System.out.println("Product Name:"+ name);
        System.out.println("Price: "+ price);
    }   

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    public String getProductID(){
        return productTypes;
    }
    @Override
    public String getproductTypes(){
        return this.productTypes;
    }
    
    @Override
    public void setPrice(double p) {
        this.price = p;
    }

    @Override
    public void setName(String n) {
        this.name = n;
    }

    @Override
    public void setProductTypes(String i) {
        this.productTypes = i;
    }
}
