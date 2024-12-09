/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order;
import entity.OrderItem;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TRANG
 */
public class OrderItemsDAO extends DBConnect {
    public void displayAllOrderItem(){
        String sql = "select * from order_items";
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                double list_price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                OrderItem o = new OrderItem(order_id, item_id, product_id, quantity, list_price, discount);
                System.out.println(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Vector<OrderItem> getOrderItem(String sql){
        Vector<OrderItem> vector = new Vector<OrderItem>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                double list_price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                OrderItem o = new OrderItem(order_id, item_id, product_id, quantity, item_id, item_id);
                vector.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int addOrderItem(OrderItem odi){
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id],[item_id],[product_id]\n"
                + "           ,[quantity],[list_price],[discount])\n"
                + "     VALUES\n"
                + "           ("+odi.getOrder_id()+","+odi.getItem_id()+","+odi.getProduct_id()+"\n"
                + "           ,"+odi.getQuantity()+","+odi.getList_price()+","+odi.getDiscount()+")";
        System.out.println(sql);
       
        try {
             Statement st = conn.createStatement();
             n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int insertOrder_item(OrderItem odi){
         int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id]\n"
                + "           ,[item_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[list_price]\n"
                + "           ,[discount])\n"
                + "     VALUES (?,?,?,?,?,?)";
        
       
        try {
             PreparedStatement pre = conn.prepareStatement(sql);
             pre.setInt(1, odi.getOrder_id());
             pre.setInt(2, odi.getItem_id());
             pre.setInt(3, odi.getProduct_id());
             pre.setInt(4, odi.getQuantity());
             pre.setDouble(5, odi.getList_price());
             pre.setDouble(6, odi.getQuantity());
             n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int removeOrderItem(int orderID, int itemID){
        int n = 0;
        String sql = "delete from order_items where order_id =" + orderID+" and item_id =" +itemID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderItemsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        OrderItemsDAO o = new OrderItemsDAO();
        OrderItem odi = new OrderItem(1616, 1, 5, 0, 45, 2);
        int n = o.removeOrderItem(1,1);
        if(n > 0){
            System.out.println("remove.");
        }else{
            System.out.println("no remove");
        }
        o.displayAllOrderItem();
    }
}
