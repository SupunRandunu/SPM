
package ControlerBean;

import Conn.ConnectDb;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Supun Randunu
 */
public class RegisterBean {
    public void registerUser(String admin,String name,String num,String email,String country,String postalcode,String pass){
        Connection conn=ConnectDb.getConnection();
        try{
         Statement stmt = conn.createStatement();
      
      String sql = "INSERT INTO `register`(`admin`, `name`, `number`, `email`, `country`, `postalcode`, `pass`) VALUES  ('"+admin+"','"+name+"','"+num+"','"+email+"','"+country+"','"+postalcode+"','"+pass+"')";
      
      stmt.executeUpdate(sql);
    }
    catch(SQLException e){        
    }
    }
    
}
