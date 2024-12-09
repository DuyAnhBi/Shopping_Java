/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import entity.Staff;
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
public class CustomerDAO extends DBConnect {
    public void displayCustomer(){
        try { 
            String sql = "select * from customers";
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int customer_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String zip_code = rs.getString(9);
                Customer c = new Customer(customer_id, first_name, last_name, phone, email, street, city, state, zip_code);
                System.out.println(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vector<Customer> getCustomer(String sql){
        Vector<Customer> vector = new Vector<Customer>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int customer_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String zip_code = rs.getString(9);
                Customer c = new Customer(customer_id, first_name, last_name, phone, email, street, city, state, zip_code);
                vector.add(c);
                //System.out.println(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int addCustomer(Customer c) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers] " +
                     "([customer_id], [first_name], [last_name], [phone], [email], [street], [city], [state], [zip_code]) " +
                     "VALUES " +
                     "(" + c.getCustomer_id() + ", '" + c.getFirst_name() + "', '" + c.getLast_name() + "', '" + c.getPhone() + "', '" +
                     c.getEmail() + "', '" + c.getStreet() + "', '" + c.getCity() + "', '" + c.getState() + "', '" + c.getZip_code() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int insertCustome(Customer c){
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers] " +
                     "([customer_id], [first_name], [last_name], [phone], [email], [street], [city], [state], [zip_code]) " +
                     "VALUES " +
                     "(?, ?, ?, ?, ?, ?,?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, c.getCustomer_id());
            pre.setString(2, c.getFirst_name());
            pre.setString(3, c.getLast_name());
            pre.setString(4, c.getPhone());
            pre.setString(5, c.getEmail());
            pre.setString(6, c.getStreet());
            pre.setString(7, c.getCity());
            pre.setString(8, c.getState());
            pre.setString(9, c.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int updateCustomer(Customer c){
        int n = 0;
        String sql = "UPDATE [dbo].[customers]\n"
                + "   SET [first_name] = ?,[last_name] = ?,[phone] = ?,[email] = ?\n"
                + "      ,[street] = ? ,[city] = ?,[state] = ?,[zip_code] = ?\n"
                + " WHERE [customer_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, c.getFirst_name());
            pre.setString(2, c.getLast_name());
            pre.setString(3, c.getPhone());
            pre.setString(4, c.getEmail());
            pre.setString(5, c.getStreet());
            pre.setString(6, c.getCity());
            pre.setString(7, c.getState());
            pre.setString(8, c.getZip_code());
            pre.setInt(9, c.getCustomer_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updatePhoneCustomer(int CustomerId, String Phone){
        int n = 0;
        String sql = "UPDATE [dbo].[customers]\n"
                + "   SET [phone] = ?"
                + " WHERE [customer_id] = ?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, Phone);
            pre.setInt(2, CustomerId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return n;
    }
    
    public int removeCustomer(int CustomerID){
        int n = 0;
        ResultSet rsOrder = this.getData("select * from orders where customer_id="+CustomerID);
        try {
            if (rsOrder.next()){
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "delete from customers where customer_id="+CustomerID ;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public static void main(String[] args) {
        CustomerDAO c = new CustomerDAO();
        Customer cus = new Customer(502, "NEWtrang", "tran", "New123456", 
                "emaildemooo", "hoala", "hanoi", "bac", "12345");
        int n = c.removeCustomer(500);
        if (n > 0){
            System.out.println("remove.");
        }else{
            System.out.println("no remove");
        }
        Vector<Customer> vector = c.getCustomer("select * from customers");
        for( Customer o : vector){
            System.out.println(o);
        }
    }
}
