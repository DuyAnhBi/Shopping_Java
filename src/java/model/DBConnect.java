package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class DBConnect {

    protected Connection conn = null;

    public DBConnect() {

        try {
            String userName = "sa";
            String password = "17022004";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=MBL5";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isConnected() {
        return conn != null;
    }
    public ResultSet getData(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;   
    }
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=MBL5";
        String userName = "sa";
        String password = "123456";

        DBConnect dbConnect = new DBConnect();
        if (dbConnect.isConnected()) {
            System.out.println("Connected to the database successfully!");
            // Proceed with database operations
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
