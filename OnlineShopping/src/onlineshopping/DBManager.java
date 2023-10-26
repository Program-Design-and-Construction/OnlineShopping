/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopping;

/**
 *
 * @author sct_n
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DBManager {

    private static final String USER_NAME = "shoppingOnline"; //your DB username
    private static final String PASSWORD = "shoppingOnline"; //your DB password
    private static final String URL = "jdbc:derby:shoppingOnline; create=true";  //url of the DB host
    
    private final String tableUser = "UserInfo";
    private final String tableProduct = "ProductInfo";

    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.getConnection());
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                Statement statement = conn.createStatement();

                if(!checkTableExisting(tableUser)){
                    statement.executeUpdate("CREATE TABLE " + tableUser + " (name VARCHAR(12), password VARCHAR(12), credit DOUBLE)");
                }
                if(!checkTableExisting(tableProduct)){
                    statement.executeUpdate("CREATE TABLE " + tableProduct + " (name VARCHAR(20), type VARCHAR(20), price DOUBLE)");
                }
            } catch (SQLException ex) {
                System.out.println("Connection Fail");
            }
        }
    }
    
    private boolean checkTableExisting(String newTableName){
        boolean flag = false;
        try {
            System.out.println("check existing tables...");
            DatabaseMetaData dbmd;
            dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            
            while(rsDBMeta.next()){
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if(tableName.compareToIgnoreCase(newTableName) == 0){
                    System.out.println(tableName + " is there");
                    flag = true;
                }
            }
            if(rsDBMeta != null){
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;        
    }
    
    public state checkName(String username, String password){
        state state = new state();
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, password, credit FROM UserInfo WHERE name = '" + username + "'");
            
            if(rs.next()){
                String pass = rs.getString("password");
                System.out.println("***"+pass);
                System.out.println("found user");
                if(password.compareTo(pass) == 0){
                    state.cust.setName(rs.getString("name"));
                    state.cust.setPassword(pass);
                    state.cust.setCredit(rs.getDouble("credit"));
                    System.out.println("Logged In");
                    state.loginFlag = true;
                } else{
                    System.out.println("wrong password");
                }
            }
            state.productList = getProductList();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
        return state;
    }
    
    public state updateNewUser(String name, String pass){
        state state = new state();
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, password, credit FROM UserInfo WHERE name = '" + name + "'");
            
            if(!rs.next()){
                statement.executeUpdate("INSERT INTO UserInfo " + "VALUES('" + name + "', '" + pass + "', 1000)");
                state.loginFlag = true;
            }else{
                System.out.println("Fail to update new user.");
            }          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
        return state;
    }
    public void addProduct(String name, String type, double price) {
        try {
            Statement statement = conn.createStatement();
            String product = "INSERT INTO ProductInfo (name, type, price) VALUES ('" + name + "', '" + type + "', " + price + ")";
            statement.executeUpdate(product);
            System.out.println("Product added to ProductInfo table.");
        } catch (SQLException ex) {
            System.out.println("Error adding the product: " + ex.getMessage());
        }
    }
    
    public Map<String,List<product>> getProductList(){
        Map<String, List<product>> prList = new HashMap<>();
        
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, type, price FROM ProductInfo");
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                double price = resultSet.getDouble("price");
                product prd = new product(type,name,price);
                prList.computeIfAbsent(type, k -> new ArrayList<>()).add(prd);
            }
        } catch (SQLException ex) {
            System.out.println("Fail to add product"+ ex.getMessage());
        }
        return prList;
    }
    
    public void updateCustomer(String name, double credit){
        try {
            Statement statement = conn.createStatement();
            String update = "UPDATE " + tableUser
                    + " SET credit = " + credit
                    + " WHERE name = '" + name + "'";
            statement.executeUpdate(update);
            System.out.println("Customer credit updated");
        } catch (SQLException ex) {
            System.out.println("Error update customer: " + ex.getMessage());
        }
    }
}
