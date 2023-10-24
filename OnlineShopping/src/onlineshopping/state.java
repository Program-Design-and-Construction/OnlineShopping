/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

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
    String menuStatus = "Info";
    Map<String, List<product>> productList;
    int currentItem = 0;
    
    public state(){
        loginFlag = false;
        cust = new customer();
        productList = new HashMap<>();
    }
}
