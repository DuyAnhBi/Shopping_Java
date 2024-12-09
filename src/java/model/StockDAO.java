/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import entity.Stock;
import entity.Store;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author TRANG
 */
public class StockDAO extends DBConnect {
    public void displayAllStock(){
        String sql = "select * from stocks";
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int store_id = rs.getInt(1);
                int product_id = rs.getInt(2);
                int quantity = rs.getInt(3);
                Stock s = new Stock(store_id, product_id, quantity);
                System.out.println(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vector<Stock> getStock(String sql){
        Vector<Stock> vector = new Vector<Stock>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int store_id = rs.getInt(1);
                int product_id = rs.getInt(2);
                int quantity = rs.getInt(3);
                Stock s = new Stock(store_id, product_id, quantity);
                vector.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    } 
    public int addStock(Stock s){
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id],[product_id],[quantity])\n"
                + "     VALUES\n"
                + "           ("+s.getStore_id()+","+s.getProduct_id()+","+s.getQuantity()+")";
        System.out.println(sql);
        try {
            Statement st = conn.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return n;
    }
    public int insertStock(Stock s){
        int n =0;
        String sql = "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id],[product_id],[quantity])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s.getStore_id());
            pre.setInt(2,s.getProduct_id());
            pre.setInt(3, s.getQuantity());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateStockqlty(int storeID, int productID, int qlty){
        int n = 0;
        String sql = "UPDATE [dbo].[stocks]\n"
                + "   SET [quantity] = ?\n"
                + " WHERE [store_id] = ? and [product_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, qlty);
            pre.setInt(2,storeID);
            pre.setInt(3, productID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        StockDAO s = new StockDAO();
//        Stock stock = new Stock(4, 202, 4);
//        int n = s.insertStock(stock);
//        if ( n > 0){
//            System.out.println("inserted.");
//        }
        s.updateStockqlty(4, 202, 8);
        s.displayAllStock();
    }
    
    
    public Store getStoreByID(int store_id){
        String sql = "select * from stores where store_id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, store_id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                store_id = rs.getInt(1);
                String store_name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip_code = rs.getString(8);
                Store s = new Store(store_id, store_name, phone, email, street, city, state, zip_code);
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Product getProductbyID(int product_id){
        String sql = "select * from products where product_id = ?";
         
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, product_id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                int model_year = rs.getInt(3);
                double list_price = rs.getDouble(4);
                String brand_name = rs.getString(5);
                String category_name = rs.getString(6);
                Product o = new Product(product_id, product_name, model_year, list_price, brand_name, category_name);
                return o;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
