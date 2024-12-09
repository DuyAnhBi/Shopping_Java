/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Staff;
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
public class StoreDAO extends DBConnect{
    public void displayAllStore(){
        String sql = "select * from stores";
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int store_id = rs.getInt(1);
                String store_name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip_code = rs.getString(8);
                Store s = new Store(store_id, store_name, phone, email, street, city, state, zip_code);
                System.out.println(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Vector<Store> getStore(String sql){
        Vector<Store> vector = new Vector<Store>();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int store_id = rs.getInt(1);
                String store_name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip_code = rs.getString(8);
                Store s = new Store(store_id, store_name, phone, email, street, city, state, zip_code);
                vector.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int addStore(Store s){
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id],[store_name],[phone],[email]\n"
                + "           ,[street],[city],[state],[zip_code])\n"
                + "     VALUES\n"
                + "           ("+s.getStore_id()+",'"+s.getStore_name()+"','"+s.getPhone()+"','"+s.getEmail()+"'\n"
                + "           ,'"+s.getStreet()+"','"+s.getCity()+"','"+s.getState()+"','"+s.getZip_code()+"')";
        System.out.println(sql);
        Statement st;
        try {
            st = conn.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int insertStore(Store s){
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id],[store_name],[phone],[email]\n"
                + "           ,[street],[city],[state],[zip_code])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, s.getStore_id());
            pre.setString(2, s.getStore_name());
            pre.setString(3, s.getPhone());
            pre.setString(4, s.getEmail());
            pre.setString(5, s.getStreet());
            pre.setString(6, s.getCity());
            pre.setString(7, s.getState());
            pre.setString(8, s.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateStore(Store s){
        int n = 0;
        String sql = "UPDATE [dbo].[stores]\n"
                + "   SET [store_name] = ?,[phone] = ?,[email] = ?\n"
                + "      ,[street] = ?,[city] = ?,[state] = ?,[zip_code] = ?\n"
                + " WHERE [store_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, s.getStore_name());
            pre.setString(2, s.getPhone());
            pre.setString(3, s.getEmail());
            pre.setString(4, s.getStreet());
            pre.setString(5, s.getCity());
            pre.setString(6, s.getState());
            pre.setString(7, s.getZip_code());
            pre.setInt(8, s.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int removeStore(int storeID){
        int n = 0;
        ResultSet rsOrder = this.getData("select * from orders where store_id="+storeID);
        try {
            if(rsOrder.next()){
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        /////////////////////////
        ResultSet rsStock = this.getData("select * from stocks where store_id="+storeID);
        try {
            if(rsStock.next()){
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////
        ResultSet rsStaff = this.getData("select * from staffs where store_id="+storeID);
        try {
            if(rsStaff.next()){
                return n;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "delete from stores where store_id="+storeID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
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
    public static void main(String[] args) {
        StoreDAO s = new StoreDAO();
        Store store = new Store(6, "Newname", "123456", "mail", "hhh", "hhhh", "sss", "1233");
        
        int n = s.removeStore(6);
        if(n!=0){
            System.out.println("remove.");
        }else{
            System.out.println("no remove");
        }
        s.displayAllStore();
        System.out.println(s.getStoreByID(1).getStore_name());
        
    }
}
