
package ControlerBean;

import Conn.ConnectDb;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Supun Randunu
 */
public class AddToCartBean {
    public void addToCart(String email,String name,String pic,String price,String id){
       int quntity=1;
         try{
            Connection conn=ConnectDb.getConnection();
            Statement stmt = conn.createStatement();
            Date date= new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(date);
            String sql = "INSERT INTO `cart`(`email`, `name`, `pic`, `date`, `quntity`, `num`, `price`) VALUES('"+email+"','"+name+"','"+pic+"','"+currentTime+"','"+quntity+"','"+id+"','"+price+"')";
      
            stmt.executeUpdate(sql);
        
        }catch(SQLException e){
           e.printStackTrace();
        }
    }
}
