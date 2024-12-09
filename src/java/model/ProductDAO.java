/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Product;
import entity.Staff;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author TRANG
 */
public class ProductDAO extends DBConnect {
    public void displayAllProduct(){
        String sql = "select * from products";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                int model_year = rs.getInt(3);
                double list_price = rs.getDouble(4);
                String brand_name = rs.getString(5);
                String category_name = rs.getString(6);
                Product o = new Product(product_id, product_name, model_year, list_price, brand_name, category_name);
                System.out.println(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vector<Product> getProduct(String sql){
        Vector<Product> vector = new Vector<Product>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                int model_year = rs.getInt(3);
                double list_price = rs.getDouble(4);
                String brand_name = rs.getString(5);
                String category_name = rs.getString(6);
                Product o = new Product(product_id, product_name, model_year, list_price, brand_name, category_name);
                vector.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int addProduct(Product p ){
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id],[product_name],[model_year]\n"
                + "           ,[list_price],[brand_name],[category_name])\n"
                + "     VALUES\n"
                + "           ("+p.getProduct_id()+",'"+p.getProduct_name()+"',"+p.getModel_year()+"\n"
                + "           ,"+p.getList_price()+",'"+p.getBrand_name()+"','"+p.getCategory_name()+"')";
         System.out.println(sql);
        try {
            Statement st = conn.createStatement();
            n=st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int insertProduct(Product p){
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id],[product_name],[model_year]\n"
                + "           ,[list_price],[brand_name],[category_name])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, p.getProduct_id());
            pre.setString(2, p.getProduct_name());
            pre.setInt(3, p.getModel_year());
            pre.setDouble(4, p.getList_price());
            pre.setString(5, p.getBrand_name());
            pre.setString(6, p.getCategory_name());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int updateProduct(Product p){
        int n = 0;
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [product_name] = ?,[model_year] = ? ,[list_price] = ?\n"
                + "      ,[brand_name] = ?,[category_name] = ?\n"
                + " WHERE [product_id] = ?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, p.getProduct_name());
            pre.setInt(2, p.getModel_year());
            pre.setDouble(3, p.getList_price());
            pre.setString(4, p.getBrand_name());
            pre.setString(5, p.getCategory_name());
            pre.setInt(6, p.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    
    public int removeProduct(int productID){
        int n = 0;
        ResultSet rsOrder_item = this.getData("select * from order_items where product_id="+productID);
        try {
            if(rsOrder_item.next()){
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////////////////////////////////////////
        ResultSet rsStock = this.getData("select * from stocks where product_id="+productID);
        try {
            if(rsStock.next()){
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "delete from products where product_id="+productID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
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
    
    public static void main(String[] args) {
        ProductDAO p = new ProductDAO();
        Product pro = new Product(203, "Newname", 2022, 22.1, "Bname", "Cname");
        
        int n = p.removeProduct(202);
        if (n != 0){
            System.out.println("remove.");
        }else{
            System.out.println("no remove.");
        }
        p.displayAllProduct();
        System.out.println(p.getProductbyID(1).getProduct_name());
    }
}
