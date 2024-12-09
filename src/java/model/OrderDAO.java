/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order;
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
public class OrderDAO extends DBConnect {
    public void displayAllOrder(){
        String sql = " select * from orders";
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int customer_id = rs.getInt(2);
                int order_status = rs.getInt(3);
                String order_date = rs.getString(4);
                String required_date = rs.getString(5);
                String shipped_date = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);
                Order o = new Order(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id);
                System.out.println(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Vector<Order> getOrder(String sql){
        Vector<Order> vector = new Vector<Order>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int customer_id = rs.getInt(2);
                int order_status = rs.getInt(3);
                String order_date = rs.getString(4);
                String required_date = rs.getString(5);
                String shipped_date = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);
                Order o = new Order(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id);
                vector.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int addOrder(Order o){
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "           ([order_id],[customer_id],[order_status],[order_date]\n"
                + "           ,[required_date],[shipped_date],[store_id],[staff_id])\n"
                + "     VALUES\n"
                + "           ("+o.getOrder_id()+","+o.getCustomer_id()+","+o.getOrder_status()+" ,'"+o.getOrder_date()+"'\n"
                + "           ,'"+o.getRequired_date()+"','"+o.getShipped_date()+"',"+o.getStore_id()+" ,"+o.getStaff_id()+")";
        System.out.println(sql);
        try {
            Statement st = conn.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int insertOder(Order o){
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "           ([order_id],[customer_id],[order_status],[order_date]\n"
                + "           ,[required_date],[shipped_date],[store_id],[staff_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,? ,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, o.getOrder_id());
            pre.setInt(2, o.getCustomer_id());
            pre.setInt(3, o.getOrder_status());
            pre.setString(4, o.getOrder_date());
            pre.setString(5, o.getRequired_date());
            pre.setString(6, o.getShipped_date());
            pre.setInt(7, o.getStore_id());
            pre.setInt(8, o.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateOrder(Order o){
        int n = 0;
        String sql = "UPDATE [dbo].[orders]\n"
                + "   SET [customer_id] = ?,[order_status] = ?,[order_date] = ?,[required_date] = ?\n"
                + "      ,[shipped_date] = ?,[store_id] = ? ,[staff_id] = ?\n"
                + " WHERE [order_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, o.getCustomer_id());
            pre.setInt(2, o.getOrder_status());
            pre.setString(3, o.getOrder_date());
            pre.setString(4, o.getRequired_date());
            pre.setString(5, o.getShipped_date());
            pre.setInt(6, o.getStore_id());
            pre.setInt(7, o.getStaff_id());
            pre.setInt(8, o.getOrder_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        Order od = new Order(1618, 65, 1, "2017-09-18", "2017-09-20", "", 1, 4);
        int n = o.updateOrder(od);
        if(n > 0){
            System.out.println("inserted.");
        }
        o.displayAllOrder();
    }
}
