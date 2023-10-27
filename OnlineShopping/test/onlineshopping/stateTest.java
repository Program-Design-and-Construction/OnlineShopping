/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package onlineshopping;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sct_n
 */
public class stateTest {
    private DBManager db;
    private state state;
    
    @Before
    public void setUp() {
        state = new state();
        db = new DBManager();
        state.productList = db.getProductList();
    }

    /**
     * Test of getProduct method, of class state.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");

        product expResult = new product("Computer", "Laptop", 300.0);
        state.productType = "Computer";
        product result = state.getProduct();
        assertEquals(expResult.getName(), result.getName());
    }

    /**
     * Test of itemCount method, of class state.
     */
    @Test
    public void testItemCount() {
        System.out.println("itemCount");
        int expResult = 0;
        int result = state.itemCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of addToCart method, of class state.
     */
    @Test
    public void testAddToCart() {
        System.out.println("addToCart");
       
        state.productType = "Computer";
        state.addToCart();
        int expResult = 1;
        int result = state.itemCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeFromCart method, of class state.
     */
    @Test
    public void testRemoveFromCart() {
        System.out.println("removeFromCart");

        state.productType = "Computer";
        state.removeFromCart();
        int expResult = 0;
        int result = state.itemCount();       
        assertEquals(expResult, result);
    }

    /**
     * Test of makePayment method, of class state.
     */
    @Test
    public void testMakePayment() {
        System.out.println("makePayment");
        state instance = new state();
        instance.cust = new customer();
        instance.cust.setCredit(100.0);
        instance.totalCost = 50;
        boolean expResult = true;
        boolean result = instance.makePayment();
        assertEquals(expResult, result);
    }    
}
