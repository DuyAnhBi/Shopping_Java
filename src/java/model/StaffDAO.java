/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Staff;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TRANG
 */
public class StaffDAO extends DBConnect{
    public void displayAllStaff(){
        String sql = "select * from staffs";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int staff_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);
                Staff o = new Staff(staff_id, first_name, last_name, email, phone, active, store_id, manager_id);
                System.out.println(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<Staff> getStaff(String sql){
        Vector<Staff> vector = new Vector<Staff>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int staff_id = rs.getInt(1);
                String first_name = rs.getString(2);
                String last_name = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);
                Staff o = new Staff(staff_id, first_name, last_name, email, phone, active, store_id, manager_id);
                vector.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int addStaff(Staff s){
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id],[first_name],[last_name],[email],\n"
                + "		   [phone],[active],[store_id],[manager_id])\n"
                + "     VALUES\n"
                + "           ("+s.getStaff_id()+",'"+s.getFirst_name()+"','"+s.getLast_name()+"','"+s.getEmail()+"',\n"
                + "		   '"+s.getPhone()+"',"+s.getActive()+","+s.getStore_id()+","+s.getManager_id()+")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int insertStaff(Staff s) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs] "
                + "([staff_id], [first_name], [last_name], [email], "
                + "[phone], [active], [store_id], [manager_id]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s.getStaff_id());
            pre.setString(2, s.getFirst_name());
            pre.setString(3, s.getLast_name());
            pre.setString(4, s.getEmail());
            pre.setString(5, s.getPhone());
            pre.setInt(6, s.getActive());
            pre.setInt(7, s.getStore_id());
            pre.setInt(8, s.getManager_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return n;
    }

    public int updateStaff(Staff s){
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [first_name] = ?,[last_name] = ?,[email] = ?,[phone] = ?\n"
                + "      ,[active] = ?,[store_id] = ?,[manager_id] = ?\n"
                + " WHERE [staff_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, s.getFirst_name());
            pre.setString(2, s.getLast_name());
            pre.setString(3, s.getEmail());
            pre.setString(4, s.getPhone());
            pre.setInt(5, s.getActive());
            pre.setInt(6, s.getStore_id());
            pre.setInt(7, s.getManager_id());
            pre.setInt(8, s.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return n;
    }
    public int updatePhone(int StaffId, String Phone){
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [phone] = ?\n"      
                + " WHERE [staff_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1 ,Phone);
            pre.setInt(2, StaffId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return n;
    }
    
    public int updateActive(int StaffId, int active){
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [active] = ?\n"
                + " WHERE [staff_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1 ,active);
            pre.setInt(2, StaffId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return n;
    }
    
    public int removeStaff(int StaffID){
        int n = 0;
        // check foreikey key order
        ResultSet rsOrder = this.getData("select * from orders where staff_id="+StaffID);
        
        try {
            if (rsOrder.next()){
                updateActive(StaffID,0);
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        // check manager
        ResultSet rsManager = this.getData("select * from staffs where "+StaffID +" in (select distinct manager_id from staffs)");
        try {
            if (rsManager.next()){
                updateActive(StaffID,0);
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "delete from staffs where staff_id="+StaffID ;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
            //change active = 0..............
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public static void main(String[] args) {
        StaffDAO s = new StaffDAO();
        Staff staff = new Staff(2, "newAbc", "xyz", "email", "new123456", 1, 1, 3);
        int n = s.removeStaff(2);
        if (n > 0){
            System.out.println("delete.");
        }else{
            System.out.println("no delete.");
        }
        Vector<Staff> vector = s.getStaff("select * from staffs");
        for( Staff o : vector){
            System.out.println(o);
        }
    }
    
}
