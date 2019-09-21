/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Supun Randunu
 */
public class ConnectDb {
     public static Connection getConnection(){
        String DBDriver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/deaproject";
    String uid = "root";
    String pwd = "";
    Connection conn = null;
    ResultSet rs = null;
        try {
            Class.forName(DBDriver);
            conn = DriverManager.getConnection(url, uid, pwd);
            return (Connection) conn;
        }
        catch(ClassNotFoundException | SQLException e){
        }
        return null;
    }
}
